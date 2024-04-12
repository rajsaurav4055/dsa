package DP;

//Watch takeuforward dp series fibonacci and climbing stairs video if you aren't able to solve.. that is the simplest and best explanation for this pattern
//Neetcode will just confuse you without any pattern

//TLE recursion-> top down from n to 0
// class Solution {
//     public int climbStairs(int n) {
//         if(n==0 || n==1)return 1;
//         int l=climbStairs(n-1);
//         int r=climbStairs(n-2);
//         return l + r;
//     }
// }
//TC: O(2^n)
//It is same as fibonacci series question
//Refer takeuforward recursion playlist lecture 7 to understand this clearly--> it is a pattern very simple

//Memoization
// class Solution {
//     public int climbStairs(int n) {
//         int[] dp=new int[n+1];
//         Arrays.fill(dp,-1);
//         return climbStairsUtil(n,dp);
//     }

//     public int climbStairsUtil(int n, int[] dp){
//         if(n==0 || n==1)return 1;
//         if(dp[n]!=-1)return dp[n];
//         return dp[n]=climbStairsUtil(n - 1, dp) +climbStairsUtil(n - 2, dp);
//     }
// }
//TC: O(N)
//SC: O(N) for dp array + O(N) for recursion stack

//Tabulation.. i.e. bottom up.. i.e. base case se add kro and final answer tk phoncho
// class Solution {
//     public int climbStairs(int n) {
//         int[] dp=new int[n+1];
//         dp[0]=1;
//         dp[1]=1;
//         for(int i=2; i<=n; i++){
//             dp[i]=dp[i-1]+dp[i-2];
//         }
//         return dp[n];
//     }
// }
//TC: O(n)
//SC: O(n)


//Optimizing for space using variables
class Solution {
    public int climbStairs(int n) {
        int prev1=1;
        int prev2=1;
        int curr;
        for(int i=2; i<=n; i++){
            curr=prev1 + prev2;
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }
}
//TC: O(n)
//SC: O(1)





<-------- Discard this area.. takeuforward and explanation and solution is better and easy than below (neetcode) as it followed a simple pattern----->

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
// class ClimbingStairsSolution {
//     public int climbStairs(int n) {
//         if(n==1)return 1;
//         int first=1;
//         int second=2;
//         for(int i=3; i<=n; i++){
//             int temp= first + second;
//             first=second;
//             second=temp;
//         }
//         return second;
//     }

    
//     //TC: O(n) -> because we calculate each subproblem only once
//     //SC: O(1)

// }
