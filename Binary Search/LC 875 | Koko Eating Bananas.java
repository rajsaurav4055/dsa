//Explanation from Neetcode
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        int max=piles[0];
        for(int i=1; i<piles.length; i++){
            max = piles[i]>max?piles[i]:max;
        }
        int k=max;
        int end=max;
        while(start < end){
            int mid=(start+end)/2;
            int hours=0;
            for(int i=0; i<piles.length; i++){
                hours+=Math.ceil((double)piles[i]/mid);
            }
            if(hours <= h && mid < k){
                k=mid;
                end=mid;//this line is crucial. It shouldn't be mid - 1 because then that end value is never checked in some cases.

            }else{
                start=mid + 1;
            }
        }
        return k;

    }
}
//TC: O(log(max(piles)*piles.length))
//SC: O(1)
