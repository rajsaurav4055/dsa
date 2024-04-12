package 1-D DP;

import java.util.* ;
import java.io.*; 


//Question link:https://www.naukri.com/code360/problems/subset-sum-equal-to-k_1550954?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM 
//Memoization
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int[][] dp=new int[n][k+1];
        for(int[] rows:dp){
            Arrays.fill(rows, -1);
        }
        return subsetSumToKUtil(n-1,k,arr,dp);
    }
    public static boolean subsetSumToKUtil(int n, int k, int arr[], int[][] dp){
        // Write your code here.
        if(k == 0 )return true;
        if(n==0)return (arr[0]==k);
        if(dp[n][k]!=-1)return (dp[n][k]==0)?false:true;
        boolean notTake=subsetSumToKUtil(n-1,k,arr,dp);
        boolean take=false;
        if(arr[n]<=k)take=subsetSumToKUtil(n-1,k-arr[n],arr, dp);
        dp[n][k]=(take || notTake) == false?0:1;
        return (take || notTake);

    }
}
//TC: O(n*k)
//SC: O(n*k)+O(n)


//Tabulation
public class Solution {
    public static boolean subsetSumToK(int n, int target, int arr[]){
        
        //instead of int we need to take boolean for tabulation
        boolean[][] dp=new boolean[n][target+1];
        for(boolean[] rows:dp){
            Arrays.fill(rows, false);
        }
        for(int i=0; i<n;i++){
            dp[i][0]=true;
        }
        if(arr[0]<=target){
            dp[0][arr[0]]=true;
        }
        
        for(int i=1; i<n; i++){
            for(int j=1; j<=target; j++){
                boolean notTake=dp[i-1][j];
                 boolean take=false;
                 if(arr[i]<=j){
                     take=dp[i-1][j-arr[i]];
                 }
                 dp[i][j]=take||notTake;
            }
        }
        return dp[n-1][target];
    }
}

//TC: O(N*k)
//SC: O(N*K)


//Space optimized approach
public class Solution {
    public static boolean subsetSumToK(int n, int target, int arr[]){
        boolean[] prev=new boolean[target + 1];
        prev[0]=true;//critical
    
        if(arr[0]<=target){
            prev[arr[0]]=true;
        }
        
        for(int i=1; i<n; i++){
            boolean[] current=new boolean[target+1];
            current[0]=true; //critical
            for(int j=1; j<=target; j++){
                boolean notTake=prev[j];
                 boolean take=false;
                 if(arr[i]<=j){
                     take=prev[j-arr[i]];
                 }
                 current[j]=take||notTake;
            }
            prev=current;
        }
        return prev[target];
    }
}
//TC:O(N*k)
//SC: O(K+1)