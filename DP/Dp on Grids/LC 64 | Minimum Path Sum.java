package 2D DP;

class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][] dp=new int[m][n];
        for(int[] rows: dp)Arrays.fill(rows,-1);
        return minPathSumUtil(grid, m-1, n-1, dp);
    }
    public int minPathSumUtil(int[][] grid,int i, int j, int[][] dp) {
        if(i==0 && j==0)return dp[i][j]=grid[i][j];
        if(i < 0 || j<0)return (int) Math.pow(10, 9);//take not here as we added some large value so that this path is not included in the minimum path
        //I was returning 0 and therefore got error
        if(dp[i][j] != -1)return dp[i][j];
        int up=grid[i][j] + minPathSumUtil(grid, i-1, j, dp);
        int left=grid[i][j] + minPathSumUtil(grid, i, j-1, dp);
        return dp[i][j]=Math.min(up,left);
    }
}
//TC: O(M*N)
//SC: O(M*N) + O((M-1)+(N-1))
