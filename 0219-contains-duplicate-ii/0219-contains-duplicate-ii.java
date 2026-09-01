class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        for(int i=0; i<nums.length; i++){
            if(!set.add(nums[i])) return true;
            if(set.size() > k) set.remove(nums[i-k]);
        }
        return false;
    }
}