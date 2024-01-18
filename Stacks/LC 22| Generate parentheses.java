
//We have three cases:
//1. Only add open parentheses if open < n
//2. Only add closed parentheses if closed < open
//3. Valid if open == closed == n
class Solution {

    List<String> result=new ArrayList<>();
    //Stack<String> stack=new Stack<>(); --> this is wrong, we only need to use Character
    Stack<Character> stack=new Stack<>();
    public List<String> generateParenthesis(int n) {
        generateParenthesisUtil(0,0,n);
        return result;
    }

    //Back-tracking
    public void generateParenthesisUtil(int openP, int closeP, int n){
        //Base case
        if (openP == closeP && closeP == n) {
            //How to iterate over a stack if you can't pop the elements in JAVA 
            Iterator itr = stack.iterator();
            String temp = "";
            while (itr.hasNext()) {
                temp = temp + itr.next();
            }
            result.add(temp);
        }

        if(openP < n){
            stack.push('(');
            generateParenthesisUtil(openP + 1, closeP,n);
            stack.pop();
        }

        if(closeP < openP){
            stack.push(')');
            generateParenthesisUtil(openP, closeP + 1,n);
            stack.pop();
        }

    }

}

//TC: 
//SC: O(n) 