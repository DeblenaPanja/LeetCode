import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{intervals.get(i).get(0), intervals.get(i).get(1), intervals.get(i).get(2), i};
        }
        Arrays.sort(arr, (a, b) -> a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(a[3], b[3]));

        long[][] dp = new long[n + 1][5];
        List<Integer>[][] ids = new ArrayList[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) ids[i][k] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            int l = arr[i - 1][0], w = arr[i - 1][2], idx = arr[i - 1][3];
            
            int lo = 0, hi = i - 2, prev = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (arr[mid][1] < l) { prev = mid; lo = mid + 1; }
                else hi = mid - 1;
            }

            for (int k = 1; k <= 4; k++) {
                dp[i][k] = dp[i - 1][k];
                ids[i][k] = ids[i - 1][k];

                long takeWeight = dp[prev + 1][k - 1] + w;
                List<Integer> takeIds = new ArrayList<>(ids[prev + 1][k - 1]);
                takeIds.add(idx);
                Collections.sort(takeIds);

                if (takeWeight > dp[i][k] || (takeWeight == dp[i][k] && isSmaller(takeIds, ids[i][k]))) {
                    dp[i][k] = takeWeight;
                    ids[i][k] = takeIds;
                }
            }
        }

        List<Integer> best = new ArrayList<>();
        long maxW = -1;
        for (int k = 1; k <= 4; k++) {
            if (dp[n][k] > maxW || (dp[n][k] == maxW && isSmaller(ids[n][k], best))) {
                maxW = dp[n][k];
                best = ids[n][k];
            }
        }

        return best.stream().mapToInt(i -> i).toArray();
    }

    private boolean isSmaller(List<Integer> a, List<Integer> b) {
        if (b.isEmpty()) return true;
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) return a.get(i) < b.get(i);
        }
        return a.size() < b.size();
    }
}