//Basically the logic is that when the number goes into loop, same number get repeated again so keep a set and keep inserting in it, as soon as there is a duplicate- break and return false.
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set=new HashSet<>();
        if(n==1 || n==10 || n==100)return true;
        int sum=0;
        while(sum!=1){
            sum=0;
            while(n>0){
                sum+=Math.pow(n%10,2);
                n=n/10;
            }
            if(sum == 1)return true;
            n=sum;
            if(!set.contains(n)){//note on how to find in the set. I got an error here
                set.add(n);
            }else{
                return false;
            }
        }
        return true;
    }
    
}
//Solved myself 


