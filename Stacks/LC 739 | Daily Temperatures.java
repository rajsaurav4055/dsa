
//Did it myself--> Super proud in 55 minutes
//Basically I am traversing from right to left in the temperatures array.
//In the stack I am pushing the index of the array instead of the value
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result=new int[temperatures.length];
        Stack<Integer> stack=new Stack<>();
        int n=temperatures.length;
        stack.push(n-1);
        result[n-1]=0;
        for(int i=n-2; i>=0; i--){
            while(!stack.isEmpty()){
                //If the temp at the top of the stack is greater than current days temperature -> we found a day so update the result array and push current days temp so that we can compare it against its previous days
                //else just pop that temperature as it is not useful anymore
                if(temperatures[stack.peek()] > temperatures[i] ){
                    result[i]=stack.peek()-i;
                    stack.push(i);
                    break;
                }else{
                    stack.pop();
                }
            }

            //If the stack goes empty and you didn't find a warmer temperature this means the result for that day=0 and then push today's temp in the stack to compare it against previous days.
            if(stack.isEmpty()){
                result[i]=0;
                stack.push(i);
            }
        }
        return result;
    }
}

//TC: O(n)
//SC: O(n)
