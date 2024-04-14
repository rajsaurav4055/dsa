//This is not really a DP problem, more of a sliding window problem

class Solution {
    public int maxProfit(int[] prices) {
        int mini=prices[0];
        int profit=0;
        for(int i=1; i<prices.length; i++){
            profit= Math.max(prices[i] - mini, profit);
            mini= Math.min(mini, prices[i]);
        }
        return profit;
    }

    public static void main(String args[]){
        int[] prices={7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
//TC: O(n)
//SC: O(1)