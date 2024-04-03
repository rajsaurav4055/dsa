package 2D DP;
//See takeuforward playlist for best pattern following approach
public class Unique Paths {
    
//Memoization
// class Solution {
//     public int uniquePaths(int m, int n) {
//         int[][] dp=new int[m][n];
//         for(int[] rows: dp){
//             Arrays.fill(rows,-1);
//         }
//         return uniquePathsUtil(m-1,n-1,dp);
//     }

//     public int uniquePathsUtil(int m, int n,int[][] dp){
//         if(m==0 && n==0)return 1;
//         if(m < 0 || n < 0 )return 0;
//         if(dp[m][n] != -1 )return dp[m][n];
//         return dp[m][n] = uniquePathsUtil(m-1,n,dp) + uniquePathsUtil(m, n-1,dp);
//     }
// }
//TC: O(N*M)
//SC: O(N*M) + O((M-1) + (N-1))

//Tabulation
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        for(int[] rows: dp){
            Arrays.fill(rows,-1);
        }
        
        return uniquePathsUtil(m,n,dp);
    }

    public int uniquePathsUtil(int m, int n,int[][] dp){
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i == 0 && j == 0){
                    dp[i][j]=1;
                    continue;
                }
                int up=0;
                int left=0;
                if(i > 0)up=dp[i-1][j];
                if(j > 0)left= dp[i][j-1]; 

                dp[i][j]=up + left;
            }
        }
        return dp[m-1][n-1];
    }
//SC: O(M*N)
//TC: O(M*N)
}
