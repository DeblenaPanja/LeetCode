class Solution {
    public java.util.List<String> generateParenthesis(int n) {
        java.util.List<String> res = new java.util.ArrayList<>();
        dfs(res, "", 0, 0, n);
        return res;
    }

    private void dfs(java.util.List<String> res, String s, int open, int close, int n) {
        if (s.length() == 2 * n) {
            res.add(s);
            return;
        }
        if (open < n) dfs(res, s + "(", open + 1, close, n);
        if (close < open) dfs(res, s + ")", open, close + 1, n);
    }
}