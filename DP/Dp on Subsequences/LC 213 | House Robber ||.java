package 1-D DP;


//Beats 100 percent users in Java
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1)return nums[0];
        int max1= robUtil(nums, 0, nums.length-2);
        int max2= robUtil(nums, 1, nums.length-1);

        return Math.max(max1,max2);
         
    }

    public int robUtil(int[] nums, int start, int end){
        int rob1=0;
        int rob2=0;//If you are at house 4, rob1 is the max robbed till house 2 and rob2 is the max robbed till house 3.

        for(int i=start; i<=end; i++){
            int temp=Math.max(nums[i]+rob1, rob2);
            rob1=rob2;
            rob2=temp;
        }
        return rob2;

    }
}

//TC: O(N)
//SC: O(1)

//Trick is to apply the solution to House Robber 1 two times
//1. To start from the first house and leave the last house and apply House robber soulution on that
//2. To start from the second house and take the last house and apply House robber solution on that
// Return maximum among both the steps.
