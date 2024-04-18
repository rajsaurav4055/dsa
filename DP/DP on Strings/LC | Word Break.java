//Recursion
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n= s.length();
        return wordBreakUtil(s, wordDict, n-1);
    }

    public boolean wordBreakUtil(String s, List<String> wordDict, int n){
        if(n < 0)return true;

        for(String word: wordDict){
            if(n - word.length()+1 < 0)continue;

            if(s.substring(n - word.length()+1, n+1).equals(word) && wordBreakUtil(s, wordDict, n-word.length())){
                return true;
            }
        }

        return false;
    }
}
//O(exponential)

//Memoization
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n= s.length();
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        return wordBreakUtil(s, wordDict, n-1, dp)==0?false:true;
    }

    public int wordBreakUtil(String s, List<String> wordDict, int n, int[] dp){
        if(n < 0)return 1;
        if(dp[n]!=-1)return dp[n];
        for(String word: wordDict){
            if(n - word.length()+1 < 0)continue;

            if(s.substring(n - word.length()+1, n+1).equals(word) && wordBreakUtil(s, wordDict, n-word.length(), dp)==1){
                return dp[n]=1;
            }
        }

        return dp[n]=0;
    }
}

//TC: O(n*m*k)
//SC: O(n) + O(m)