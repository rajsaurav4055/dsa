//seems like pretty straight forward BFS problem

//Takeaways:
//1. How to convert a weighted graph into adjacency list

//This can also be done using Bellman Ford and Dijkstra (that is why it is part of Advanced graph) but using BFS is the most time efficient

class CheapestFlightSolution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        //Create adjacency list-> must know how to convert weighted graph into adjacency list
        Map<Integer,List<int[]>> adjList= new HashMap<>();
        for(int[] flight: flights){
            adjList.computeIfAbsent(flight[0],value-> new ArrayList<>()).add(new int[]{ flight[1] ,flight[2] });
        }

        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{src,0});
        int stops=0;

        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);

        while(stops <= k && !q.isEmpty()){
            int size= q.size();

            //Iterate on current level
            while(size-- > 0){
                int[] temp=q.poll();
                int node=temp[0];
                int distance= temp[1];

                if(!adjList.containsKey(node))continue;
                //Loop over neighbours of popped node
                for(int[] e : adjList.get(node)){
                    int neighbour = e[0];
                    int price= e[1];
                    if(dist[neighbour] < price + distance)continue;
                    dist[neighbour]=price + distance;
                    q.add(new int[]{neighbour, dist[neighbour]});
                }
            }
            stops++;
        }
        return dist[dst]==Integer.MAX_VALUE?-1:dist[dst];  

    }
    //TC: O(N + E.K) where N is number of cities, E is number of flights
    //SC: O(N + E.K)
}