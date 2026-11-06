public class Problem09 {
    public int maxSubArray(int[] nums) {

        int currentMax = nums[0];
        int maxSoFar = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);

            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar;
    }
}