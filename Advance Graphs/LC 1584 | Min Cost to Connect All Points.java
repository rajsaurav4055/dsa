//Using Prim's algorithm

class Solution {
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        int cost=0;
        pq.add(new int[] {0,0});
        int len= points.length;
        Set<Integer> visited= new HashSet<>();
        
        while(visited.size() < len){
            int[] arr= pq.poll();

            int weight= arr[0];
            int currNode= arr[1];
            if(visited.contains(currNode))continue;
            visited.add(currNode);
            cost+=weight;

            for(int nextNode=0; nextNode< len; nextNode++){
                if(!visited.contains(nextNode)){
                    int nextWeight= Math.abs(points[nextNode][0]-points[currNode][0])+ Math.abs(points[nextNode][1] - points[currNode][1]);//edge weight, the index of next node
                    pq.add(new int[]{nextWeight, nextNode});
                }
            }

        }
        return cost;
    }
}