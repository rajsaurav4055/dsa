class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        int[][] dp=new int[m][n];
        for(int[] rows:dp)Arrays.fill(rows, -1);
        return longestCommonSubsequenceUtil(text1, text2, m-1, n-1,dp);
    }

    public int longestCommonSubsequenceUtil(String text1, String text2, int ind1, int ind2, int[][] dp) {
        if(ind1 < 0 || ind2 < 0)return 0;
        if(dp[ind1][ind2]!=-1)return dp[ind1][ind2];
        if(text1.charAt(ind1) == text2.charAt(ind2)){
            return dp[ind1][ind2]=1 + longestCommonSubsequenceUtil(text1, text2, ind1-1, ind2-1,dp);
        }
        return dp[ind1][ind2]=Math.max(longestCommonSubsequenceUtil(text1, text2, ind1, ind2-1, dp), longestCommonSubsequenceUtil(text1, text2, ind1-1, ind2, dp));
    }

    public static void main(String args[])[
        String s1="njdsbvkfdmvkf";
        String s2="ndsvbfvnvkndfvfd";
        longestCommonSubsequence(s1,s2);
    ]
}

//TC: O(m*n)
//SC: O(m*n) + O(N+M)

//Above solution to tabulation me convert krna is slightly complex because base case me it is like if ind1 < 0 which we can't do dp[-1][n]
//that is why we shift the index inorder for us to be easily able to convert to tabulation
//before->after, -1 -> 0, n-1 -> n, 0 -> 1
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        int[][] dp=new int[m+1][n+1];
        for(int[] rows:dp)Arrays.fill(rows, -1);
        return longestCommonSubsequenceUtil(text1, text2, m, n,dp);
    }

    public int longestCommonSubsequenceUtil(String text1, String text2, int i, int j, int[][] dp) {
        if(i == 0 || j == 0)return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        if(text1.charAt(i-1) == text2.charAt(j-1)){
            return dp[i][j]=1 + longestCommonSubsequenceUtil(text1, text2, i-1, j-1,dp);
        }
        return dp[i][j]=Math.max(longestCommonSubsequenceUtil(text1, text2, i, j-1, dp), longestCommonSubsequenceUtil(text1, text2, i-1, j, dp));
    }
}

//TC: O(m*n)
//SC: O(m*n) + O(N+M)

//Tabulation
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        int[][] dp=new int[m+1][n+1];
        for(int[] rows:dp)Arrays.fill(rows, -1);
        //Base case
        for(int i=0; i<=n; i++)dp[0][i]=0;
        for(int j=0; j<=m; j++)dp[j][0]=0;
        
        for(int i=1; i<=m ; i++){
            for(int j=1; j<=n; j++){
                //match
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j]=1 + dp[i-1][j-1];
                }else{
                    //not match
                    dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);
                }
                
            }
        }

        return dp[m][n];
    }
}
//SC: O(M*N)
//TC: O(M*N)

//Further space optimization
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        // int[][] dp=new int[m+1][n+1];
        int[] prev=new int[n+1];
        int[] current=new int[n+1];

        // for(int[] rows:dp)Arrays.fill(rows, -1);
        //Base case
        for(int i=0; i<=n; i++)prev[i]=0;
        
        for(int i=1; i<=m ; i++){
            for(int j=1; j<=n; j++){
                //match
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    current[j]=1 + prev[j-1];
                }else{
                    //not match
                    current[j]=Math.max(current[j-1], prev[j]);
                }
                
            }
            //Important way how to assign this in Java
            prev=(int[])(current.clone());
        }

        return prev[n];
    }
}

//TC: O(M*N)
//SC: O(M)
