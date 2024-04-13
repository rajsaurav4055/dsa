
import java.util.*;

//Memoization
//this is basically count so we return 1 or 0 in the base case and return take+notTake
//wahi minimum hota to return krte minimum of(take, notTake)
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


//tabulation
class Solution {
    public int coinChange(int[] coins, int amount) {

        int n=coins.length;
        int[][] dp=new int[coins.length][amount+1];;
        for(int i=0; i<=amount; i++){
            if(i % coins[0]==0){
                dp[0][i]=i/coins[0];
            }else{
                dp[0][i]=(int)Math.pow(10,8);
            }
        }

        for(int index=1; index<n; index++){
            for(int target=0; target<=amount; target ++){
                int notTake= dp[index - 1] [target];
                int take=(int)Math.pow(10,8);
                if(target >= coins[index]){
                    take= 1 + dp[index][target- coins[index]];
                }
                dp[index][target]= Math.min(notTake,take);
            }
            
        }

        int res= dp[n-1][amount];
        return (res== (int)Math.pow(10,8))?-1:res;
    }
}

//TC: O(n* amount)
//SC: O(n * amount )

//Space optimized
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] prev=new int[amount+1];
        int[] curr=new int[amount+1];

        int n=coins.length;
        // int[][] dp=new int[n][amount+1];;
        for(int i=0; i<=amount; i++){
            if(i % coins[0]==0){
                prev[i]=i/coins[0];
            }else{
                prev[i]=(int)Math.pow(10,8);
            }
        }

        for(int index=1; index<n; index++){
            for(int target=0; target<=amount; target ++){
                int notTake= prev [target];
                int take=(int)Math.pow(10,8);
                if(target >= coins[index]){
                    take= 1 + curr[target- coins[index]];
                }
                curr[target]= Math.min(notTake,take);
            }
            prev=curr;
            
        }

        int res= prev[amount];
        return (res== (int)Math.pow(10,8))?-1:res;
    }
}

//TC: O(N*amount)
//SC: O(2* amount)