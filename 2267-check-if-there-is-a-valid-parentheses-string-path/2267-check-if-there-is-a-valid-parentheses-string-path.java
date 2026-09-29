class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if ((m + n) % 2 == 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        
        boolean[][][] vis = new boolean[m][n][(m + n) / 2 + 1];
        return dfs(grid, 0, 0, 0, vis);
    }

    private boolean dfs(char[][] g, int r, int c, int bal, boolean[][][] vis) {
        bal += g[r][c] == '(' ? 1 : -1;
        if (bal < 0 || bal > (g.length + g[0].length) / 2) return false;
        if (r == g.length - 1 && c == g[0].length - 1) return bal == 0;
        
        if (vis[r][c][bal]) return false;
        vis[r][c][bal] = true;

        return (r + 1 < g.length && dfs(g, r + 1, c, bal, vis)) || 
               (c + 1 < g[0].length && dfs(g, r, c + 1, bal, vis));
    }
}