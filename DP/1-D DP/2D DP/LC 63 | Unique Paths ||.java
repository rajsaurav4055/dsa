package 2D DP;

//Memoization
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int[][] dp=new int[m][n];
        for(int[] rows:dp)Arrays.fill(rows,-1);
        
        return uniquePathsWithObstaclesUtil(obstacleGrid,m-1,n-1,dp);
    }

    public int uniquePathsWithObstaclesUtil(int[][] obstacleGrid, int i, int j, int[][] dp) {
        if(i < 0 || j < 0) return 0;
        if(obstacleGrid[i][j] == 1)return 0;
        if(i==0 && j==0)return 1;
        if(dp[i][j]!=-1)return dp[i][j];
        return dp[i][j]=uniquePathsWithObstaclesUtil(obstacleGrid,i-1,j,dp)+uniquePathsWithObstaclesUtil(obstacleGrid,i,j-1,dp);
    }
}
//TC: O(m*n)
//SC: O(m*n) + O((m-1) + (n-1))
