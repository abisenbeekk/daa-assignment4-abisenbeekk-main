import java.util.Arrays;

public class Problem04 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);

            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = num;

            if (i == len) {
                len++;
            }
        }
        return len;
    }
}