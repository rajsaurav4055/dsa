package DP;


//TLE -> recursion
// class Solution {

//     public int minCostUtil(int i, int[] cost, int n){
//         if(i == n - 1){
//             return cost[n-1];
//         }
//         if(i == n){
//             return 0;
//         }
//         return  cost[i]+ Math.min(minCostUtil(i+1, cost, n), minCostUtil(i+2,cost, n));
//     }

//     public int minCostClimbingStairs(int[] cost) {
//         int nSteps=cost.length;
//         return Math.min(minCostUtil(0,cost, nSteps), minCostUtil(1,cost, nSteps));
//     }
// }

//using memoization
// class Solution {

//     public int minCostUtil(int i, int[] cost, int n, int[] memo){
//         if(i == n - 1){
//             return cost[n-1];
//         }
//         if(i == n){
//             return 0;
//         }
//         if(memo[i] > 0){
//             return memo[i];
//         }
//         memo[i]=cost[i]+ Math.min(minCostUtil(i+1, cost, n, memo), minCostUtil(i+2,cost, n, memo));
//         return memo[i];
//     }

//     public int minCostClimbingStairs(int[] cost) {

//         int n=cost.length;
//         int[] memo=new int[n+1];
//         return Math.min(minCostUtil(0,cost, n,memo), minCostUtil(1,cost, n, memo));
//     }

//     //TC: O(N)
//     //SC: O(N)
// }

//using dp array bottom up
// class Solution {

//     public int minCostClimbingStairs(int[] cost) {
        
//         int n=cost.length;

//         // The array's length should be 1 longer than the length of cost
//         // This is because we can treat the "top floor" as a step to reach
//         int[] dp=new int[n+1];//this is to calculate minimum cost to reach ith index

//         // Start iteration from step 2, since the minimum cost of reaching
//         // step 0 and step 1 is 0
//         for(int i=2; i< dp.length; i++){
//             int takeOneStep=dp[i-1] + cost[i-1];
//             int takeTwoSteps= dp[i-2] + cost[i-2];
//             dp[i]= Math.min(takeOneStep, takeTwoSteps);
//         }

//         //final element in dp refers to the top floor
//         return dp[dp.length -1];
//     }

//     //TC: O(n)
//     //SC: O(n)
// }

//With constant space
class minCostClimbingStairsSolution {

    public int minCostClimbingStairs(int[] cost) {
        
        int n=cost.length;
        int downOne = 0;
        int downTwo = 0;
        for(int i=2; i< cost.length + 1; i++){
            int temp=downOne;
            downOne=Math.min(downOne + cost[i-1], downTwo + cost[i-2]);//MInimum cost to react the current step
            downTwo= temp;
        }
        return downOne;
    }

    //TC: O(n)
    //SC: O(1)
}