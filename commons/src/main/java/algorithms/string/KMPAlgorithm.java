package algorithms.string;

/**
 * KMP 算法实现
 * Created by why on 5/11/2017.
 */
public class KMPAlgorithm {
    public static final String PREFIX = "X";

    public static boolean matchString(String target, String mode) {
        //为了和算法保持一致，使 index 从 1 开始,增加一个前缀

        String newTarget = PREFIX + target;
        String newMod = PREFIX + mode;

        int[] k = calculateK(mode);

        int i = 1;
        int j = 1;
        while (i <= target.length() && j <= mode.length()) {
            if (j == 0 || newTarget.charAt(i) == newMod.charAt(j)) {
                i++;
                j++;
            } else
                j = k[j];
        }

        if (j > mode.length())
            return true;
        return false;
    }

    protected static int[] calculateK(String mode) {
//        为了和算法保持一致，使 index 从 1 开始,增加一个前缀
        String newMode = PREFIX + mode;
        int[] k = new int[newMode.length()];

        int i = 1;
        k[1] = 0;
        int j = 0;

        while (i < mode.length()) {
            if (j == 0 || newMode.charAt(i) == newMode.charAt(j)) {
                i++;
                j++;
                k[i] = j;
            } else
                j = k[j];
        }
        return k;
    }
}
