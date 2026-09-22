class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int[][] tree = new int[2 * n][k];
        int[] prod = new int[2 * n];

        for (int i = 0; i < n; i++) {
            int v = nums[i] % k;
            tree[n + i][v] = 1;
            prod[n + i] = v;
        }

        for (int i = n - 1; i > 0; i--) merge(tree, prod, i, k);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int p = n + queries[i][0], v = queries[i][1] % k;
            tree[p] = new int[k];
            tree[p][v] = 1;
            prod[p] = v;
            for (p >>= 1; p > 0; p >>= 1) merge(tree, prod, p, k);

            int L = queries[i][2], R = n - 1, x = queries[i][3];
            
            // Collect segment tree nodes for interval [L, R]
            int[] leftNodes = new int[32], rightNodes = new int[32];
            int lCount = 0, rCount = 0;
            
            for (L += n, R += n + 1; L < R; L >>= 1, R >>= 1) {
                if ((L & 1) == 1) leftNodes[lCount++] = L++;
                if ((R & 1) == 1) rightNodes[rCount++] = --R;
            }

            int[] res = new int[k];
            int curP = 1;

            // Process nodes strictly left-to-right
            for (int j = 0; j < lCount; j++) {
                int node = leftNodes[j];
                for (int m = 0; m < k; m++) res[(m * curP) % k] += tree[node][m];
                curP = (curP * prod[node]) % k;
            }
            for (int j = rCount - 1; j >= 0; j--) {
                int node = rightNodes[j];
                for (int m = 0; m < k; m++) res[(m * curP) % k] += tree[node][m];
                curP = (curP * prod[node]) % k;
            }

            ans[i] = res[x];
        }
        return ans;
    }

    private void merge(int[][] tree, int[] prod, int i, int k) {
        int l = 2 * i, r = 2 * i + 1;
        prod[i] = (prod[l] * prod[r]) % k;
        tree[i] = tree[l].clone();
        for (int j = 0; j < k; j++) tree[i][(j * prod[l]) % k] += tree[r][j];
    }
}