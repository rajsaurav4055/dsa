class Solution {
    public int[] plusOne(int[] digits) {
        int carry=1;
        int len=digits.length;
        int[] newList=new int[len+1];
        for(int i=len-1; i>=0; i--){
            int currSum = digits[i]+carry;
            if(currSum > 9){
                digits[i]=currSum % 10;
                newList[i+1]=digits[i];
                carry=1;
            }else{
                digits[i]=currSum;
                newList[i+1]=digits[i];
                carry=0;
                break;
            }
        }
        if(carry == 1){
            newList[0]=1;
            return newList;
        }
        return digits;
    }
}
//TC: O(n)
//SC: O(1)
