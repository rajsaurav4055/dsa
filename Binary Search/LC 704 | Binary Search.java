//Solved myself
class Solution {
    public int search(int[] nums, int target) {
        int n=nums.length;
        int start = 0;
        int end = n - 1;
        while(start<=end){
            int mid = (start + end)/2;
            if(nums[mid] == target){
                return mid;
            }else if(target > nums[mid]){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
}
//TC: O(log n)
//SC: O(1)