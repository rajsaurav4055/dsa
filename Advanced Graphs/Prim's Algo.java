
//Prims algorithm using Priority queue
//https://www.geeksforgeeks.org/prims-algorithm-using-priority_queue-stl/
//O(ElogV)

//Approach
//1) Initialize keys of all vertices as infinite and 
//    parent of every vertex as -1.

// 2) Create an empty priority_queue pq.  Every item
//    of pq is a pair (weight, vertex). Weight (or 
//    key) is used  as first item  of pair
//    as first item is by default used to compare
//    two pairs.

// 3) Initialize all vertices as not part of MST yet.
//    We use boolean array inMST[] for this purpose.
//    This array is required to make sure that an already
//    considered vertex is not included in pq again. This
//    is where Prim's implementation differs from Dijkstra.
//    In Dijkstra's algorithm, we didn't need this array as
//    distances always increase. We require this array here 
//    because key value of a processed vertex may decrease
//    if not checked.

// 4) Insert source vertex into pq and make its key as 0.

// 5) While either pq doesn't become empty 
//     a) Extract minimum key vertex from pq. 
//        Let the extracted vertex be u.

//     b) Include u in MST using inMST[u] = true.

//     c) Loop through all adjacent of u and do 
//        following for every vertex v.

//            // If weight of edge (u,v) is smaller than
//            // key of v and v is not already in MST
//            If inMST[v] = false && key[v] > weight(u, v)

//                (i) Update key of v, i.e., do
//                      key[v] = weight(u, v)
//                (ii) Insert v into the pq 
//                (iv) parent[v] = u
               
// 6) Print MST edges using parent array.

public class PrimsAlgo {
    // Print MST using Prim's algorithm
    void primMST()
    {
        // Create a priority queue to store vertices that
        // are being primMST. This is weird syntax in Java.
        // Refer below link for details of this syntax
        // https://www.geeksforgeeks.org/implement-min-heap-using-stl/
        PriorityQueue<iPair> pq = new PriorityQueue<>(
            V, new Comparator<iPair>() {
                public int compare(iPair a, iPair b)
                {
                    return a.second - b.second;
                }
            });
 
        int src = 0; // Taking vertex 0 as source
 
        // Create a vector for keys and initialize all
        // keys as infinite (INF)
        int INF = Integer.MAX_VALUE;
        int[] key = new int[V];
        Arrays.fill(key, INF);
 
        // To store parent array which in turn store MST
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
 
        // To keep track of vertices included in MST
        boolean[] inMST = new boolean[V];
 
        // Insert source itself in priority queue and
        // initialize its key as 0.
        pq.offer(new iPair(0, src));
        key[src] = 0;
 
        /* Looping till priority queue becomes empty */
        while (!pq.isEmpty()) {
            // The first vertex in pair is the minimum key
            // vertex, extract it from priority queue.
            // vertex label is stored in second of pair (it
            // has to be done this way to keep the vertices
            // sorted key (key must be first item
            // in pair)
            int u = pq.peek().second;
            pq.poll();
 
            // Different key values for same vertex may
            // exist in the priority queue. The one with the
            // least key value is always processed first.
            // Therefore, ignore the rest.
            if (inMST[u]) {
                continue;
            }
 
            inMST[u] = true; // Include vertex in MST
 
            // 'i' is used to get all adjacent vertices of a
            // vertex
            for (iPair i : adj.get(u)) {
                // Get vertex label and weight of current
                // adjacent of u.
                int v = i.first;
                int weight = i.second;
 
                //  If v is not in MST and weight of (u,v)
                //  is smaller
                // than current key of v
                if (!inMST[v] && key[v] > weight) {
                    // Updating key of v
                    key[v] = weight;
                    pq.offer(new iPair(key[v], v));
                    parent[v] = u;
                }
            }
        }
 
        // Print edges of MST using parent array
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i);
        }
    }
}
