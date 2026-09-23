class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        int target = sum - x;
        if (target < 0) return -1;
        if (target == 0) return nums.length;

        int maxLen = -1, curSum = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            curSum += nums[right];
            while (curSum > target) {
                curSum -= nums[left++];
            }
            if (curSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}