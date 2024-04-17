//This question is modified version of Count number of ways the subset sum is equal to target. How?

//Just think we will create two subsets -> s1 that contains + and s2 that contains -. ANd we will have to find ways in which s1-s2=target
// Now total sum of the array is s1+s2 =totSum
//replacing that in s1-s2=target => totSum-s2-s2=target => s2=(totSum - target)/2. We know all the values in RHS.
// Therefore, in short we need to count the number of ways in which the subset sum is equal to (totSum - target)/2

//which we have done below



//Recursion
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int totSum=0;
        for(int i=0; i<nums.length; i++){
            totSum+=nums[i];
        }
        if((totSum - target)%2!=0 || (totSum - target <0))return 0;
        return countSubsetSumWays(nums, (totSum-target)/2);
    }

    public int countSubsetSumWays(int[] nums, int target) {
        int n=nums.length;
        return countSubsetSumWaysUtil(nums, target, n-1);
    }

    public int countSubsetSumWaysUtil(int[] nums, int target, int i) {
        //VI base case as in the constraints it is mentioned that nums[i] can be 0 as well
        //if unable to figure out checkout striver's explanation in count partitions with given sum start of the video
        if(i==0){
            if(target ==0 && nums[0]==0)return 2;//because we have two options-> either we can take it or not take it
            if(target ==0 || nums[0]==target)return 1;//
            return 0;
        }
        int notTake=countSubsetSumWaysUtil(nums, target, i-1);
        int take=0;
        if(nums[i] <=target){
            take = countSubsetSumWaysUtil(nums, target-nums[i], i-1);
        }
        return notTake + take;

    }
}

//Memoization
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int totSum=0;
        for(int i=0; i<nums.length; i++){
            totSum+=nums[i];
        }
        if((totSum - target)%2!=0 || (totSum - target <0))return 0;
        return countSubsetSumWays(nums, (totSum-target)/2);
    }

    public int countSubsetSumWays(int[] nums, int target) {
        int n=nums.length;
        int[][] dp=new int[n][target+1];
        for(int[] rows:dp)Arrays.fill(rows, -1);
        return countSubsetSumWaysUtil(nums, target, n-1, dp);
    }

    public int countSubsetSumWaysUtil(int[] nums, int target, int i, int[][] dp) {
        //VI base case as in the constraints it is mentioned that nums[i] can be 0 as well
        //if unable to figure out checkout striver's explanation in count partitions with given sum start of the video
        if(i==0){
            if(target ==0 && nums[0]==0)return 2;//because we have two options-> either we can take it or not take it
            if(target ==0 || nums[0]==target)return 1;//
            return 0;
        }
        if(dp[i][target]!=-1)return dp[i][target];
        int notTake=countSubsetSumWaysUtil(nums, target, i-1, dp);
        int take=0;
        if(nums[i] <=target){
            take = countSubsetSumWaysUtil(nums, target-nums[i], i-1, dp);
        }
        return dp[i][target]=notTake + take;

    }
}
//TC: O(n* target)
//SC: O(n*target) + O(n+target)