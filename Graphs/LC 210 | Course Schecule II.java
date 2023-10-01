package Graphs;


//Topological sort
class CourseScheduleiiSolution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result=new int[numCourses];
        int[] indegree=new int[numCourses];
        List<List<Integer>> adjList=new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] prereq : prerequisites){
            adjList.get(prereq[1]).add(prereq[0]);
            indegree[prereq[0]]++;
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        int i=0;//counter to track the position where the course needs to be added in the result array
        int takencourses= 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            result[i++]=course;//this is how you add in an array
            takencourses++;
            for(int neighbor : adjList.get(course)){
                indegree[neighbor]--;
                if(indegree[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }
        if(takencourses == numCourses)return result;
        else return new int[0];//this is how you return empty array in Java

    }

    //TC: O(V+E)
    //SC: O(V+E)
}
