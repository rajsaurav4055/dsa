package 2D DP;

class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[][] dp= new int[n][n];
        for(int[] rows:dp)Arrays.fill(rows,-1);
        return minimumTotalUtil(triangle, 0, 0, n, dp);
    }

    public int minimumTotalUtil(List<List<Integer>> triangle, int i, int j, int n, int[][] dp) {
        if(i == n-1)return dp[i][j]=triangle.get(i).get(j);
        if(dp[i][j]!=-1)return dp[i][j];
        return dp[i][j]=Math.min(triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1,j, n, dp), triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1,j+1, n, dp));
    }
}
//TC: O(M*N)
//SC: O(M*N) + O((M-1)+(N-1))
