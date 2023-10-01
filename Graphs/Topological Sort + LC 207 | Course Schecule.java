package Graphs;

//Revision needed


//Basically there shouldn't be a cycle in the graph.
//To detect cycle we can use Union-find algorithm I guess
//We can use Topological sorting as well
//We can use DFS as well

//This is a DFS solution
// class Solution {
//     boolean isCyclic(List<List<Integer>> adjList, int course, boolean[] visited, boolean[] onPath){
//         if(onPath[course] == true)return true;
//         if(visited[course] == true)return false;
        
//         //Mark current course as visited and part of current recursion stack
//         visited[course]=true;
//         onPath[course] = true;
//         for(int neighbour : adjList.get(course)){
//             if(isCyclic(adjList, neighbour, visited, onPath) == true){
//                 return true;
//             }
//         }
//         onPath[course]=false;
//         return false;

//     }
    
    
    
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         //create an adjacency list
//         List<List<Integer>> adjList= new ArrayList<>(numCourses);
//         for(int i=0; i<numCourses; i++){
//             adjList.add(new ArrayList<>());
//         }

//         for(int[] prerequisite : prerequisites){
//             adjList.get(prerequisite[1]).add(prerequisite[0]);
//         }

//         //visited array is to track the nodes that are alread visited so that it removes the rendundancy of checking again from the same node (in the below for loop)if it has already been checked in previous dfs calls
//         boolean[] visited= new boolean[numCourses];

//         //onPath array is to keep track of the nodes that are on current path and check if the path is a loop or not based on it being true or false when the node is reached 
//         boolean[] onPath= new boolean[numCourses];

//         for(int i=0; i<numCourses; i++){
//             if(isCyclic(adjList, i,visited, onPath)){
//                 return false;
//             }
//         }
//         return true;


//     }
//     //TC: O(V + E) where v is the vertex and E is the edge
//     //SC: O(V) + O(V) + O(V + E)= O(V + E) 
// }


//Using Topological Sort using Kahn's Algo-  BFS based- very interesting- do give it a read
class CourseScheduleSolution {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        int[] indegree= new int[numCourses];

        //create an adjacency list
        List<List<Integer>> adjList= new ArrayList<>(numCourses);
        for(int i=0; i<numCourses; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] prerequisite : prerequisites){
            adjList.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> queue= new LinkedList<>();
        //Push all the nodes with indegree 0 in the queue
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }  
        }
        int nodesVisited= 0;//to keep track of total number of nodes visited
        while(!queue.isEmpty()){
            int course= queue.poll();
            nodesVisited++;

            for(int neighbor: adjList.get(course)){
                //delete the edge "node->neighbor"
                indegree[neighbor]--;
                if(indegree[neighbor]==0){
                    queue.add(neighbor);
                }
            }
        }
        return nodesVisited == numCourses;

    }
    //TC: O(V + E) where v is the vertex and E is the edge
    //adjList takes O(E) and indegree array takes O(V). Queue takes O(N)or O(V) as each node will be pushed worst case
    //SC: O(V + E) 
}
