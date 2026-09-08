import java.util.Arrays;

class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] avgs = new int[n];
        Arrays.fill(avgs, -1);
        long windowSize = 2L * k + 1;
        if (windowSize > n) {
            return avgs;
        }
        long windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += nums[i];
        }
        avgs[k] = (int) (windowSum / windowSize);
        for (int i = k + 1; i < n - k; i++) {
            windowSum += nums[i + k] - nums[i - k - 1];
            avgs[i] = (int) (windowSum / windowSize);
        }
        return avgs;
    }
}