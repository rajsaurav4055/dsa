package DP;


//using recursion which is brute force and top town
//TLE for n=44 as TC is O(2^n)
// class Solution {
//     public int climbStairs(int n) {
//         return climbStairsUtil(0,n);
//     }

//     public int climbStairsUtil(int i, int n){
//         if(i==n)return 1;
//         if(i>n)return 0;
//         return climbStairsUtil(i+1,n) + climbStairsUtil(i+2,n);
//     }
// }


//Convert recusrsion into dp using Memoization -> Top down
// class Solution {
//     public int climbStairs(int n) {
//         int[] memo=new int[n+1];
//         return climbStairsUtil(0,n,memo);
//     }

//     public int climbStairsUtil(int i, int n, int[] memo){
//         if(i==n)return 1;
//         if(i>n)return 0;
//         if(memo[i] > 0)return memo[i];
//         memo[i]= climbStairsUtil(i+1,n,memo) + climbStairsUtil(i+2,n,memo);
//         return memo[i];
//     }
//     //TC: O(n) -> because we calculate each subproblem only once (This should work in an interview)
//     //SC: O(n) -> for the memo array

// }

//Using DP -> Bottom up
// class Solution {
//     public int climbStairs(int n) {
//         if(n==1)return 1;
//         int[] dp=new int[n+1];
//         dp[1]=1;
//         dp[2]=2;
//         for(int i=3; i<=n; i++){
//             dp[i]=dp[i-1] + dp[i-2];
//         }
//         return dp[n];
//     }

    
//     //TC: O(n) -> because we calculate each subproblem only once
//     //SC: O(n) -> for the dp array

// }

//Using variables instead of arrays to reduce space complexity
class ClimbingStairsSolution {
    public int climbStairs(int n) {
        if(n==1)return 1;
        int first=1;
        int second=2;
        for(int i=3; i<=n; i++){
            int temp= first + second;
            first=second;
            second=temp;
        }
        return second;
    }

    
    //TC: O(n) -> because we calculate each subproblem only once
    //SC: O(1)

}
