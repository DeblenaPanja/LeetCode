class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        boolean[][][] visited = new boolean[m][n][(m + n + 1) / 2];
        return dfs(grid, 0, 0, 0, visited);
    }

    private boolean dfs(char[][] g, int r, int c, int val, boolean[][][] vis) {
        val += (g[r][c] == '(' ? 1 : -1);
        if (val < 0 || val > (g.length + g[0].length) / 2) return false;
        if (r == g.length - 1 && c == g[0].length - 1) return val == 0;
        if (vis[r][c][val]) return false;
        vis[r][c][val] = true;

        return (r + 1 < g.length && dfs(g, r + 1, c, val, vis)) ||
               (c + 1 < g[0].length && dfs(g, r, c + 1, val, vis));
    }
}