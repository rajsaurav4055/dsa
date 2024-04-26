
//Memoization
class Solution {

    private static final int[][] dirs={{0,1},{1,0},{-1,0},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0)return 0;
        int n= matrix.length;
        int m=matrix[0].length;
        int[][] dp=new int[n][m];
        for(int[] rows:dp)Arrays.fill(rows, -1);
        int ans=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                ans=Math.max(ans, longestIncreasingPathUtil(matrix, i, j, n, m, dp));
            }
        }
        return ans;
    }

    public int longestIncreasingPathUtil(int[][] matrix, int i, int j, int n, int m, int[][] dp){
        
        int maxLength=0;
        if(dp[i][j]!=-1)return dp[i][j];
        for(int[] dir:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x>=0 && x<n && y>=0 && y<m && matrix[x][y] > matrix[i][j]){
                maxLength=Math.max(maxLength, longestIncreasingPathUtil(matrix, x, y, n, m, dp));
            }
        }
        maxLength++;
        return dp[i][j] = maxLength;
    }

}

//TC: O(n*m)
//SC: O(n*m)
