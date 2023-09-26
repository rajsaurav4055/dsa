
//See its explanation from code with harry YT channel.. Very very easy concept
//Constraint : Valid for only positive integers BUT we have a modified count sort to include the negative integers as well in LC 973- Kth largest element in an array
// Also doesn't work for decimal values
//TC: O(N)
//SC: O(maxValue) where maxValue is the maximum value in the array
public class CountSort {
    public int[] countSort(int[] nums){
        int n=nums.length;
        int maxValue = Integer.MAX_VALUE;
        for(int num: nums){
            maxValue= MATH.max(maxValue,num);
        }
        int[] count = new int[maxValue+1];
        for(int num : nums){
            count[num]++;
        }
        int[] sortedArray= new int[n];
        int pointer=0;
        for(int i = 0; i<count.length; i++){
            if(count[i]!=0){
                while(count[i]!=0){
                    sortedArray[pointer] = i;
                    count[i]-=1;
                    pointer+=1;
                }
            }
        }
        return sortedArray;
        
    }
}
