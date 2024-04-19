//one of a kind solutions
//prefix and suffix sums lo and then dono me se se max jo hai wo.
//if prefix or suffix product becomes 0 toh update it to 1 because 0 k baad we reset the product

class Solution {
    public int maxProduct(int[] nums) {
        int n=nums.length;
        int prefix=1;
        int suffix=1;
        int max=Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++){
            if(prefix==0)prefix=1;
            if(suffix==0)suffix=1;
            
            prefix*=nums[i];
            suffix*=nums[n-i-1];
            max=Math.max(max, Math.max(prefix,suffix));
        }
        return max;
    }
}
//TC;O(n)