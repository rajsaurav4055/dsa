//Memoization
class Solution {
    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[][] dp=new int[n][m];
        for(int[] rows: dp)Arrays.fill(rows,-1);
        return minDistanceUtil(word1, word2, n-1, m-1, dp);
    }

    public int minDistanceUtil(String word1, String word2, int i, int j, int[][] dp) {
        if(j<0)return i+1;
        if(i<0)return j+1;
        if(dp[i][j]!=-1)return dp[i][j];
        if(word1.charAt(i)==word2.charAt(j)){
            return dp[i][j]= minDistanceUtil(word1, word2, i-1, j-1, dp);
        }else{
            return dp[i][j]= Math.min(1+minDistanceUtil(word1, word2, i-1, j, dp),Math.min(1+minDistanceUtil(word1, word2,i-1,j-1, dp), 1+minDistanceUtil(word1, word2, i, j-1, dp)));
        }
    }

    public static void main(String args[]){
        String s1="nfdvbjdfknv";
        String s2="cnsjdcjds";
        System.out.println(minDistance(s1,s2));
    }
}
//TC: O(n*m)
//SC: O(n*m) + O(n+m)