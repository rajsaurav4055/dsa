
//Memoization
class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int[][] dp=new int[n][2];
        for(int[] rows: dp)Arrays.fill(rows,-1);
        return maxProfitUtil(prices, 0, 0,dp);
    }

    public int maxProfitUtil(int[] prices, int buy, int i,int[][] dp) {
        if(i>prices.length-1)return 0;
        if(dp[i][buy]!=-1)return dp[i][buy];
        int profit=0;
        if(buy==0){//buy=0 means you are allowed to buy now
            profit= Math.max(maxProfitUtil(prices, 0, i+1, dp), -prices[i] + maxProfitUtil(prices, 1, i+1, dp));
        }else{
            profit=Math.max(maxProfitUtil(prices,1,i+1, dp), prices[i] + maxProfitUtil(prices, 0, i+2, dp));
        }
        return dp[i][buy]=profit;
    }
}
//TC: O(n*2)
//SC: O(n*2) + O(n+2)
