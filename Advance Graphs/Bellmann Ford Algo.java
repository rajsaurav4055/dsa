
//https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
//A Bellman-Ford algorithm is also guaranteed to find the shortest path in a graph, similar to Dijkstra’s algorithm. 
//Although Bellman-Ford is slower than Dijkstra’s algorithm, it is capable of handling graphs with negative edge weights, 
//which makes it more versatile. The shortest path cannot be found if there exists a negative cycle in the graph. 
//If we continue to go around the negative cycle an infinite number of times, 
//then the cost of the path will continue to decrease (even though the length of the path is increasing). 
//As a result, Bellman-Ford is also capable of detecting negative cycles, which is an important feature.


//Principle of Relaxation of Edges for Bellman-Ford:
//It states that for the graph having N vertices, all the edges should be relaxed N-1 times to compute the single source shortest path.
//In order to detect whether a negative cycle exists or not, relax all the edge one more time and if the shortest distance for any node reduces then we can say that a negative cycle exists. In short if we relax the edges N times, and there is any change in the shortest distance of any node between the N-1th and Nth relaxation than a negative cycle exists, otherwise not exist.
public class BellmannFordAlgo {

    // The main function that finds shortest distances from
    // src to all other vertices using Bellman-Ford
    // algorithm. The function also detects negative weight
    // cycle
    void BellmanFord(Graph graph, int src)
    {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];
 
        // Step 1: Initialize distances from src to all
        // other vertices as INFINITE
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;
 
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE
                    && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }
 
        // Step 3: check for negative-weight cycles. The
        // above step guarantees shortest distances if graph
        // doesn't contain negative weight cycle. If we get
        // a shorter path, then there is a cycle.
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE
                && dist[u] + weight < dist[v]) {
                System.out.println(
                    "Graph contains negative weight cycle");
                return;
            }
        }
        printArr(dist, V);
    }

    //TC: Time Complexity when graph is connected:
    //Best Case: O(E), when distance array after 1st and 2nd relaxation are same , we can simply stop further processing
    //Average Case: O(V*E)
    //Worst Case: O(V*E)
    //Time Complexity when graph is disconnected:
    //All the cases: O(E*(V^2))
    //Auxiliary Space: O(V), where V is the number of vertices in the graph.
}
