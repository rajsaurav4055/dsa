package 1-D DP;
//Recursion with Memoization
// class Solution {
//     public int rob(int[] nums) {
//         int n=nums.length;
//         int[] memo=new int[n+2];
//         Arrays.fill(memo, -1);
//         return robUtil(nums,0,n,memo);
//     }
//     public int robUtil(int[] nums, int i, int n,int[] memo){
//         if(i == n-1){
//             return nums[n-1];
//         }
//         if(i == n || i==n+1){
//             return 0;
//         }
//         if(memo[i] > -1){
//             return memo[i];
//         }
//         //either ye waale ghar me chori karo and then alternate me move karo ya phr iss ghar to skip kro and next wale me move kro
//         memo[i]=Math.max(nums[i] + robUtil(nums,i+2,n,memo),robUtil(nums,i+1,n,memo));

//         return memo[i];
//     }
// }

//DP- Bottom up
class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        int[] memo=new int[n+2];
        Arrays.fill(memo, -1);
        return robUtil(nums,0,n,memo);
    }
    public int robUtil(int[] nums, int i, int n,int[] memo){
        if(i == n-1){
            return nums[n-1];
        }
        if(i == n || i==n+1){
            return 0;
        }
        if(memo[i] > -1){
            return memo[i];
        }
        //either ye waale ghar me chori karo and then alternate me move karo ya phr iss ghar to skip kro and next wale me move kro
        memo[i]=Math.max(nums[i] + robUtil(nums,i+2,n,memo),robUtil(nums,i+1,n,memo));

        return memo[i];
    }
}
