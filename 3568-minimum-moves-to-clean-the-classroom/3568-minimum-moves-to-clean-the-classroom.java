class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length(), sr = 0, sc = 0, lCount = 0;
        int[][] lId = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char c = classroom[i].charAt(j);
                if(c == 'S'){ sr = i; sc = j; }
                else if(c == 'L') lId[i][j] = 1 << (lCount++);
            }
        }
        if(lCount == 0) return 0;
        int target = (1 << lCount) - 1;
        int[][][] maxE = new int[m][n][1 << lCount];
        for(int i=0; i<m; i++) for(int j=0; j<n; j++) java.util.Arrays.fill(maxE[i][j], -1);
        java.util.Queue<int[]> q = new java.util.ArrayDeque<>();
        q.add(new int[]{sr, sc, energy, 0, 0});
        maxE[sr][sc][0] = energy;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], e = cur[2], mask = cur[3], moves = cur[4];
            if(mask == target) return moves;
            if(e == 0) continue;
            for(int[] d : dirs){
                int nr = r + d[0], nc = c + d[1];
                if(nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X'){
                    char ch = classroom[nr].charAt(nc);
                    int ne = (ch == 'R') ? energy : e - 1;
                    int nmask = (ch == 'L') ? (mask | lId[nr][nc]) : mask;
                    if(ne > maxE[nr][nc][nmask]){
                        maxE[nr][nc][nmask] = ne;
                        q.add(new int[]{nr, nc, ne, nmask, moves + 1});
                    }
                }
            }
        }
        return -1;
    }
}