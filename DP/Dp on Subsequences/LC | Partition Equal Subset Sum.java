//Memoization
class Solution {
    public static int subsetSumToK(int n, int target, int arr[], int[][] dp){
        if(n==0){
            if(target==arr[0])return dp[n][target]=1;
            else return dp[n][target]=0;
        }
        if(dp[n][target]!=-1)return dp[n][target];

        int notTake= subsetSumToK(n-1, target, arr, dp);
        int take=0;
        if(target >= arr[n]){
            take= subsetSumToK(n-1, target- arr[n], arr, dp);
        }
        return dp[n][target]= take | notTake;
    }

    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int i=0; i<nums.length; i++){
            sum+=nums[i];
        }
        if(sum%2!=0)return false;
        int target=sum/2;
        int[][] dp=new int[nums.length][target + 1];
        for(int[] rows: dp)Arrays.fill(rows, -1);
        return subsetSumToK(nums.length - 1, target, nums, dp)==0?false:true;
    }
}

//TC: O(N*K)
//SC: O(N)



//Array optimized approach
class Solution {
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

    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int i=0; i<nums.length; i++){
            sum+=nums[i];
        }
        if(sum%2!=0)return false;
        int target=sum/2;
        return subsetSumToK(nums.length, target, nums);
    }
}

//TC: O(N*K)
//SC: O(N)
