class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int N = n + k - 1;
        int K = 2 * k;

        long[][] dp = new long[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= Math.min(i, K); j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % MOD;
            }
        }

        return (int) dp[N][K];
    }
}