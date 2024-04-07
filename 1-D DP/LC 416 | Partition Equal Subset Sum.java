package 1-D DP;


// Basically the logic is exactly same as Subset Sum equal target question which I solved.
//Here just that here the target will be the half of the sum of total given array.
//If total sum is odd this means that the array can't be partitioned.
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
        return subsetSumToK(nums.length, target, nums );
    }
}

//TC: O(N*K)
//SC: O(N)
