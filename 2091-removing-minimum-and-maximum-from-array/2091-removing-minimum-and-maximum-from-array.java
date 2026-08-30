class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length, min = 0, max = 0;
        for(int i=1; i<n; i++){
            if(nums[i] < nums[min]) min = i;
            if(nums[i] > nums[max]) max = i;
        }
        int i = Math.min(min, max), j = Math.max(min, max);
        return Math.min(Math.min(j+1, n-i), i+1+n-j);
    }
}