//Rules:
//1. Express in terms of (index, target)
//2. Find all possibility (take and not take)
//3. Find the min number of coins
//4. Base case
//basically we are counting toh uska pattern is 
//f(i,target){
    //base case
//     take and nottake
//     return min(take , nottake)
// }
class Solution {
    public int coinChange(int[] coins, int amount) {
        //this change made to accomodate the case where if there is no way the we need to return -1
        int res= coinChangeUtil(coins.length -1, amount, coins);
        return (res== (int)Math.pow(10,8))?-1:res;
    }

    public int coinChangeUtil(int index, int target, int[] coins){
        if(index==0){
            if(target%coins[0]==0)return target/coins[0];
            else return (int)Math.pow(10,8);
        }
        int notTake= coinChangeUtil(index - 1, target, coins);
        int take=(int)Math.pow(10,8);
        if(target >= coins[index]){
            take= 1 + coinChangeUtil(index, target - coins[index], coins);
        }

        return  Math.min(notTake,take);
    }

    public static void main(String args[]){
        int[] coins={1,2,5};
        int amount=11;
        coinChange(coins, amount);
    }
}
//TC: Exponential
//SC:  O(N) 

//Memoization
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp=new int[coins.length][amount+1];
        for(int[] rows:dp)Arrays.fill(rows,-1);


        int res= coinChangeUtil(coins.length -1, amount, coins, dp);
        return (res== (int)Math.pow(10,8))?-1:res;
    }

    public int coinChangeUtil(int index, int target, int[] coins,int[][] dp){
        if(index==0){
            if(target%coins[0]==0)return dp[index][target]=target/coins[0];
            else return (int)Math.pow(10,8);
        }
        if(dp[index][target]!=-1)return dp[index][target];
        int notTake= coinChangeUtil(index - 1, target, coins,dp);
        int take=(int)Math.pow(10,8);
        if(target >= coins[index]){
            take= 1 + coinChangeUtil(index, target - coins[index], coins,dp);
        }

        return dp[index][target]= Math.min(notTake,take);
    }
}
//TC: O(N * target)
//SC: O(N * target) + O(N+target)

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
