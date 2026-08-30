class Solution {
    public int findKthLargest(int[] nums, int k) {
        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>();
        for(int n : nums){
            pq.add(n);
            if(pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}