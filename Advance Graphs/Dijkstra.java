
//Given a graph and a source vertex in the graph, find the shortest paths from the source to all vertices in the given graph.
//Dijkstra’s shortest path algorithm for Adjacency List using Heap in O(E logV):
//For Dijkstra’s algorithm, it is always recommended to use Heap (or priority queue) as the required operations (extract minimum and decrease key) match with the specialty of the heap (or priority queue). However, the problem is, that priority_queue doesn’t support the decrease key. To resolve this problem, do not update a key, but insert one more copy of it. So we allow multiple instances of the same vertex in the priority queue. This approach doesn’t require decreasing key operations and has below important properties.

//Whenever the distance of a vertex is reduced, we add one more instance of a vertex in priority_queue. Even if there are multiple instances, we only consider the instance with minimum distance and ignore other instances.
//The time complexity remains O(E * LogV) as there will be at most O(E) vertices in the priority queue and O(logE) is the same as O(logV)
//Below is the implementation of the above approach:

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    private int V;
    private List<List<iPair>> adj;
 
    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new iPair(v, w));
        adj.get(v).add(new iPair(u, w));
    }

    //core logic is written here
    void shortestPath(int src) {
        PriorityQueue<iPair> pq = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.first));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
 
        pq.add(new iPair(0, src));
        dist[src] = 0;
 
        while (!pq.isEmpty()) {
            int u = pq.poll().second;
 
            for (iPair v : adj.get(u)) {
                if (dist[v.first] > dist[u] + v.second) {
                    dist[v.first] = dist[u] + v.second;
                    pq.add(new iPair(dist[v.first], v.first));
                }
            }
        }
 
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
 
    static class iPair {
        int first, second;
 
        iPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
 
public class Main {
    public static void main(String[] args) {
        int V = 9;
        Graph g = new Graph(V);
 
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);
 
        g.shortestPath(0);
    }
}
