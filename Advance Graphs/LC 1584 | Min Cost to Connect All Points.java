
//This is basically a minimum spanning tree problem

//Using Prim's algorithm with priority queue
// Time Complexity: O(N^2 log(N)) where N is the length of points. N^2 comes from the fact we need to find the distance between a currNode and every other node to pick the shortest distance. log(N) comes from Priority Queue
// Space Complexity: O(N^2)
// class Solution {
//     public int minCostConnectPoints(int[][] points) {
//         PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
//         int cost=0;
//         pq.add(new int[] {0,0});
//         int len= points.length;
//         Set<Integer> visited= new HashSet<>();
        
//         while(visited.size() < len){
//             int[] arr= pq.poll();

//             int weight= arr[0];
//             int currNode= arr[1];
//             if(visited.contains(currNode))continue;
//             visited.add(currNode);
//             cost+=weight;

//             for(int nextNode=0; nextNode< len; nextNode++){
//                 if(!visited.contains(nextNode)){
//                     int nextWeight= Math.abs(points[nextNode][0]-points[currNode][0])+ Math.abs(points[nextNode][1] - points[currNode][1]);//edge weight, the index of next node
//                     pq.add(new int[]{nextWeight, nextNode});
//                 }
//             }

//         }
//         return cost;
//     }
// TC: O(N^2logn)
// SC: O(N^2) -> worst case we will have to push N⋅(N−1)/2≈N^2 edges into the heap.
// }

//Can we do better with the time complexity ?
//Yes below is the implementation in O(N^2) time to implement Prim's Algorithm which is without min heap
class MinCostConnectPointsSolution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int mstCost = 0;
        int edgesUsed = 0;
        
        // Track nodes which are visited.
        boolean[] inMST = new boolean[n];
        
        int[] minDist = new int[n];
        minDist[0] = 0;
        
        for (int i = 1; i < n; ++i) {
            minDist[i] = Integer.MAX_VALUE;
        }
        
        while (edgesUsed < n) {
            int currMinEdge = Integer.MAX_VALUE;
            int currNode = -1;
            
            // Pick least weight node which is not in MST.
            for (int node = 0; node < n; ++node) {
                if (!inMST[node] && currMinEdge > minDist[node]) {
                    currMinEdge = minDist[node];
                    currNode = node;
                }
            }
            
            mstCost += currMinEdge;
            edgesUsed++;
            inMST[currNode] = true;
            
            // Update adjacent nodes of current node.
            for (int nextNode = 0; nextNode < n; ++nextNode) {
                int weight = Math.abs(points[currNode][0] - points[nextNode][0]) + 
                             Math.abs(points[currNode][1] - points[nextNode][1]);
                
                if (!inMST[nextNode] && minDist[nextNode] > weight) {
                    minDist[nextNode] = weight;
                }
            }
        }
        
        return mstCost;
    }

    //TC: O(N^2) //TC optimized from O(N^2log n) to O(N^2)
    //SC: O(N) // Space also optimized from O(N^2) to O(N)
}