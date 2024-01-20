class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        if(position.length == 1)return 1;

        //Step 1: convert position and speed into list of pairs and then sort them based on their position in descending order
        int[][] cars=new int[position.length][2];
        for(int i=0; i<position.length; i++){
            cars[i][0]=position[i];
            cars[i][1]=speed[i];
        }
        //sort the array based on the position
        Arrays.sort(cars,java.util.Comparator.comparingInt(o->o[0]));

        //once sorted apply the logic
        Stack<Double> stack=new Stack<>();
        for(int i=cars.length-1; i>=0; i--){
            double currentTime = (double)(target - cars[i][0])/cars[i][1];
            if(!stack.isEmpty() && currentTime <= stack.peek()){
                continue;
            }else{
                stack.push(currentTime);
            }
        }

        return stack.size();

    }
}
//TC: O(N)
//SC: O(N)
