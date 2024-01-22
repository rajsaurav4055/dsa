class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea=0;
        Stack<Pair<Integer,Integer>> stack=new Stack<>();
        int start;
        int n=heights.length;
        for(int i=0; i<n; i++){
            int currHt= heights[i];
            start=i;
            while(!stack.isEmpty() && stack.peek().getValue() > currHt){
                Pair<Integer,Integer> pair=stack.pop();
                int index= pair.getKey();
                int h= pair.getValue(); 
                maxArea = Math.max(maxArea, h*(i - index));
                start=index;
            }
            stack.push(new Pair(start,currHt));
        }

        while(!stack.isEmpty()){
            Pair<Integer,Integer> pair= stack.pop();
            int index = pair.getKey();
            int h = pair.getValue();
            maxArea = Math.max(maxArea, h * (n - index));
        }
        return maxArea;
    }
}
//TC: O(n)
//SC: O(n)