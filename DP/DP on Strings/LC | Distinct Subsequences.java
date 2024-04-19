
//Recursive
class Solution {
    public int numDistinct(String s, String t) {
        int m=s.length();
        int n=t.length();
        return f( s,  t,  m-1,  n-1);
    }

    public int f(String s, String t, int i, int j) {
        if(j < 0)return 1;
        if(i < 0)return 0;

        if(s.charAt(i)==t.charAt(j)){
            return f(s, t, i-1, j-1) + f(s, t, i - 1, j);
        }
        return f(s, t, i-1, j);
    }
}

//Memoization
class Solution {
    public int numDistinct(String s, String t) {
        int m=s.length();
        int n=t.length();
        int[][] dp=new int[m][n];
        for(int[] rows:dp)Arrays.fill(rows, -1);
        return f( s,  t,  m-1,  n-1, dp);
    }

    public int f(String s, String t, int i, int j, int[][] dp) {
        if(j < 0)return 1;
        if(i < 0)return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        if(s.charAt(i)==t.charAt(j)){
            return dp[i][j]=f(s, t, i-1, j-1, dp) + f(s, t, i - 1, j, dp);
        }
        return dp[i][j]= f(s, t, i-1, j, dp);
    }
}
//TC: O(m*n)
//SC: O(m*n) + O(m+n)