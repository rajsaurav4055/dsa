
//gfg solution
//I was trying to do backwards but I was facing some issue
//When done from the forward, it tends to work so I used gfg solution to understand
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n=s1.length();
        int m=s2.length();
        int l=s3.length();
        if(l!=(n+m))return false;
        int[][] dp=new int[n+1][m+1];
        for(int[] rows: dp){
            Arrays.fill(rows, -1);
        }
        return dfs( 0, 0, s1, s2, s3, n, m, dp)==0?false:true;
    }

    public int dfs( int i, int j, String A, String B, String C, int n, int m, int[][] dp){
        // If path has already been calculated from this
        // index then return calculated value.
        if (dp[i][j] != -1)
            return dp[i][j];
        // If we reach the destination return 1
        if (i == n && j == m)
            return 1;

        // If C[i+j] matches with both A[i] and B[j]
        // we explore both the paths

        //i+j is the technique to get the index of the bigger array (unique)
        if (i < n && A.charAt(i) == C.charAt(i + j) && j < m
            && B.charAt(j) == C.charAt(i + j)) {
            // go down and store the calculated value in x
            // and go right and store the calculated value
            // in y.
            int x = dfs(i + 1, j, A, B, C, n, m, dp);
            int y = dfs(i, j + 1, A, B, C, n, m, dp);

            // return the best of both.
            return dp[i][j] = x | y;
        }

        // If C[i+j] matches with A[i].
        if (i < n && A.charAt(i) == C.charAt(i + j)) {
            // go down
            int x = dfs(i + 1, j, A, B, C, n, m, dp);

            // Return the calculated value.
            return dp[i][j] = x;
        }

        // If C[i+j] matches with B[j].
        if (j < m && B.charAt(j) == C.charAt(i + j)) {
            int y = dfs(i, j + 1, A, B, C, n, m, dp);

            // Return the calculated value.
            return dp[i][j] = y;
        }

        // if nothing matches we return 0
        return dp[i][j] = 0;

    }
}
//TC: O(m*n)
//SC: O(m*n)