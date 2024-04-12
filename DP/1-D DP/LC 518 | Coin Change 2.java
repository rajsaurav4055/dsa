
import java.util.*;

//Memoization
class Solution {
    public int change(int amount, int[] coins) {
        int n=coins.length;
        int[][] dp=new int[amount+1][n];
        for(int[] rows:dp){
            Arrays.fill(rows,-1);
        }
        return changeUtil(amount, coins, n-1,dp);
    }

    public int changeUtil(int target, int[] coins, int index, int[][] dp){
        if(index == 0){
            if(target % coins[0] == 0)return 1;
            else return 0;
        }
        if(dp[target][index]!=-1)return dp[target][index];
        int notTake=changeUtil(target, coins, index-1,dp);
        int take=0;
        if(target >= coins[index]){
            //As it is infinite supply we don't need to subtract the index
            //VI we didn't need to reduce the index and also no need to add anything to the function
            take= changeUtil(target - coins[index], coins, index,dp);
        }
        return dp[target][index]=take + notTake;
    }

    public static void main(String args[]){
        int coins[]={1,2,5};
        int amount=5;
        System.out.println("Total number of ways "+ change(amount, coins));

    }
}

//TC: O(N*target)
//SC: O(N*target) + O(target)
