

// Bellman Ford ALgorithm -> Shortest path problem for directed edges and also negative edges but in this question we don't need to deal with negative edges
// Time Complexty (n * t) | Space Complexity O(n) where t is the length of times
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //Initialize dist array and fill it with infinity apart from the src
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[k-1]=0;

        //Relax all the edges n-1 times where n is the number of nodes
        for(int i=1;i < n; i++){
            int[] temp= new int[n];
            temp=Arrays.copyOf(dist,dist.length);

            for(int j=0; j<times.length; j++){
                int src= times[j][0];
                int dest= times[j][1];
                int time= times[j][2];
                if(temp[src - 1]!=Integer.MAX_VALUE && temp[dest - 1]>temp[src - 1]+time){
                    temp[dest - 1]= temp[src - 1] + time;
                }
            }
            dist = temp;
        }

        //calculating the minimum time
        int minTime=Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            if(dist[i]==Integer.MAX_VALUE){
                return -1;
            }
            minTime= Math.max(dist[i],minTime);
        }
        return minTime;

    }
}

