package interview.xiaomi;

/**
 * 大整型封裝。
 * 目前只支持10进制。
 */
public class BigInt {

    //数组 mag 中每一个下标保存的数字位数，为了防止溢出，最大是9位。
    public static final int NUMBER_HOLD = 9;
    // 向高位借数是的数值。
    public static final int BORROW_VALUE = 1000000;
    //符号 1 0 -1
    int signum;

    //用来获取无符号整型
    // value & LONG_MASK，value转换为long类型
    private final static long LONG_MASK = 0xffffffffL;

    // 保存数字数组，把大整数分段拆分保存在数组里。
    int[] mag;

    public BigInt(String val) {
        int len = val.length();
        int cursor = 0;
        signum = 1;

        //符号判断
        int index1 = val.lastIndexOf('-');
        int index2 = val.lastIndexOf('+');
        if (val.length() == 0)
            signum = 0;
        if (index1 == 0) {
            signum = -1;
            cursor = 1;
        }
        if (index2 == 0) {
            cursor = 1;
        }
        //index 异常判断

        if (cursor == len) {
            signum = 0;
            mag = new int[0];
            return;
        }

//        计算数组长度
        int numDigits = len - cursor;
        int magnum = (numDigits + NUMBER_HOLD - 1) / NUMBER_HOLD;
        mag = new int[magnum];

        // 从结尾开始去分段整数
        int lastIndex = len;
        for (int i = magnum - 1; i >= 0; --i) {
//            String group = val.substring(cursor, (cursor += NUMBER_HOLD) > len ? len : cursor);
            String group = val.substring((lastIndex - NUMBER_HOLD) < cursor ? cursor : (lastIndex - NUMBER_HOLD), lastIndex);
            int groupVal = Integer.parseInt(group);
            mag[i] = groupVal;
            lastIndex -= NUMBER_HOLD;
        }
    }

    BigInt(int[] val, int signum) {
        this.mag = val;
        this.signum = signum;
    }

    public BigInt add(BigInt val) {
//        如果传入参数为0，返回当前对象
        if (val.signum == 0)
            return this;
//        如果当前对象为 0 ,返回传入参数
        if (signum == 0)
            return val;

        //相同符号,mag相加
        if (val.signum == signum)
            return new BigInt(add(mag, val.mag), signum);

//        比较当前值与参数大小，决定mag 减数与被减数。
        int cmp = compareMagnitude(val);
        int[] resultMag = (cmp > 0 ? subtract(mag, val.mag)
                : subtract(val.mag, mag));

//        创建新对象并返回*/
        return new BigInt(resultMag, cmp == signum ? 1 : -1);
    }

    public BigInt subtract(BigInt val) {
        if (val.signum == 0)
            return this;
        if (signum == 0)
            return val.negate();
        if (val.signum != signum)
            return new BigInt(add(mag, val.mag), signum);

        int cmp = compareMagnitude(val);
        if (cmp == 0)
            return new BigInt("0");
        int[] resultMag = (cmp > 0 ? subtract(mag, val.mag)
                : subtract(val.mag, mag));

        return new BigInt(resultMag, cmp == signum ? 1 : -1);
    }

    public BigInt negate() {
        return new BigInt(this.mag, -this.signum);
    }

    public int[] add(int[] val1, int[] val2) {
//        首先判断长度，长度长的作为val1
        if (val1.length < val2.length) {
            int[] tmp = val1;
            val1 = val2;
            val2 = tmp;
        }

        int val1Index = val1.length;
        int val2Index = val2.length;
        int result[] = new int[val1Index];
        long sum = 0;// sum 为long 类型是为了保存 int 相加溢出的部分

        //按 val2的长度，循环相加。
        while (val2Index > 0) {
            sum = (val1[--val1Index] & LONG_MASK) + (val2[--val2Index] & LONG_MASK) + (sum >>> 32);
            result[val1Index] = (int) sum;
        }
        // 把 val1 比 val2 长的部分追加到数组上，追加溢出部分
        while (val1Index > 0) {
            sum = (val1[--val1Index] & LONG_MASK) + (sum >>> 32);
            result[val1Index] = (int) sum;
        }

//        有溢出，则创建一个长度+1的数组，复制元素，并设置溢出值
        boolean carry = (sum >>> 32) != 0;
        if (carry) {
            int bigger[] = new int[result.length + 1];
            System.arraycopy(result, 0, bigger, 1, result.length);
            bigger[0] = (int) sum;
            return bigger;
        }

        return result;
    }

    public int[] subtract(int[] big, int[] little) {
        int bigIndex = big.length;
        int result[] = new int[bigIndex];
        int littleIndex = little.length;
        long difference = 0;

        // 每一个分段相减，记录到结果数组中
        while (littleIndex > 0) {
            difference = (big[--bigIndex] & LONG_MASK) - (little[--littleIndex] & LONG_MASK);
            result[bigIndex] = (int) difference;
        }

        // 复制较长数组中剩余的分段数据
        while (bigIndex > 0)
            result[--bigIndex] = big[bigIndex];

        // 从低位向高位遍历，如果有哪一段数是负数，则向高位借 1
        // 因为每一段的位数是9，所以向高位借1相当于1e9
        for (int i = big.length - 1; i > 0; --i) {
            if (result[i] < 0) {
                result[i - 1]--;
                result[i] = BORROW_VALUE + result[i];
            }
        }

        return result;
    }

    private int compareMagnitude(BigInt val) {
        int[] m1 = mag;
        int[] m2 = val.mag;

        if (mag.length > val.mag.length)
            return 1;
        if (val.mag.length > mag.length)
            return -1;
        for (int i = 0; i < mag.length; i++) {
            int a = m1[i];
            int b = m2[i];
            if (a != b)
                return ((a) < (b)) ? -1 : 1;
        }
        return 0;
    }

    private static final String PLACE_HOLDER_ZERO = "000000000";

    @Override
    public String toString() {
        if (signum == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        if (signum == -1)
            sb.append("-");

        sb.append(mag[0]);
        for (int i = 1; i < mag.length; i++) {
            int length = String.valueOf(mag[i]).length();
            sb.append(PLACE_HOLDER_ZERO.substring(0, NUMBER_HOLD - length)).append(mag[i]);
        }
        return sb.toString();
    }
}