class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++){
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        java.util.Arrays.sort(arr, (a,b) -> Integer.compare(a[0], b[0]));
        int[] ans = new int[n];
        int i = 0;
        while(i < n){
            int j = i + 1;
            while(j < n && arr[j][0] - arr[j-1][0] <= limit) j++;
            int[] idx = new int[j-i];
            for(int k=i; k<j; k++) idx[k-i] = arr[k][1];
            java.util.Arrays.sort(idx);
            for(int k=0; k<idx.length; k++) ans[idx[k]] = arr[i+k][0];
            i = j;
        }
        return ans;
    }
}