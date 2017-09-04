package interview.xiaomi;

/**
 * Created by why on 2017/5/18.
 */
public class Test {
  private final static long LONG_MASK = 0xffffffffL;

  public static void main(String[] args) {
    long l = (Integer.MAX_VALUE & LONG_MASK) + 1;
    System.out.println(Integer.MAX_VALUE);
    System.out.println(l);
  }

  public static int func(int s) {
    int sum = 0;
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(s));
    for (int i = 0; i < stringBuffer.length(); i++) {
      sum = sum + Integer.parseInt(stringBuffer.substring(i, i + 1).toString());
    }
    return sum;
  }
}
