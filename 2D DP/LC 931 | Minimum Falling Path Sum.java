//Memoization but still TLE
// class Solution {
//     public int minFallingPathSum(int[][] matrix) {
//         int nrow = matrix.length;
//         int ncolumn = matrix[0].length;
//         int min= Integer.MAX_VALUE;
//         int[][] dp=new int[nrow][ncolumn];
//         for(int[] rows : dp)Arrays.fill(rows, -1);
//         for(int i=0; i<ncolumn; i++){
//             min= Math.min(min, minFallingPathSumUtil(matrix, nrow - 1, i,dp));
//         }
//         return min;
//     }

//     public int minFallingPathSumUtil(int[][] matrix, int i, int j, int[][] dp) {
//         if(j < 0 || j > matrix[0].length - 1)return (int)Math.pow(10,9);
//         if(i==0)return dp[i][j]=matrix[0][j];
//         if(dp[i][j]!= -1)return dp[i][j]; 
//         int up= matrix[i][j]+ minFallingPathSumUtil(matrix, i - 1, j, dp);
//         int leftd= matrix[i][j]+ minFallingPathSumUtil(matrix, i - 1, j - 1, dp);
//         int rightd= matrix[i][j]+ minFallingPathSumUtil(matrix, i - 1, j + 1, dp);
//         return dp[i][j]=Math.min(up, Math.min(leftd, rightd ));
//     }
// }

//Tabulation
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int nrow = matrix.length;
        int ncolumn = matrix[0].length;

        int[][] dp=new int[nrow][ncolumn];
        for(int j=0; j<ncolumn; j++){
            dp[0][j]=matrix[0][j];
        }
        for(int i=1; i<nrow; i++){
            for(int j=0; j<ncolumn; j++){
                int leftd=Integer.MAX_VALUE;int rightd=Integer.MAX_VALUE;
                int up= matrix[i][j]+ dp[i-1][j];
                if(j>0){
                    leftd= matrix[i][j]+ dp[i-1][j-1];
                }
                if(j< ncolumn - 1){
                    rightd= matrix[i][j]+ dp[i-1][j+1];
                }
                dp[i][j]=Math.min(up, Math.min(leftd, rightd ));
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=0; i<ncolumn; i++){
            min=Math.min(min, dp[nrow-1][i]);
        }
        return min;
    }
    
}

//TC: O(M*N)
//SC: O(M*N)
