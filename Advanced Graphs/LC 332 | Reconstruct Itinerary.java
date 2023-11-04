//Short and sweet NC solution
//This is basically iterative DFS I guess
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        
        //used to store the output
        LinkedList<String> itinerary=new LinkedList<>();

        //VI as you messed up the map part in the interview
        //Map to construct the graph
        Map<String, PriorityQueue<String>> adjMap= new HashMap<>();
        Stack<String> stack=new Stack<>();

        //Construct the adjacency list
        for(List<String> ticket: tickets){
            adjMap.computeIfAbsent(ticket.get(0),k ->new PriorityQueue<>()).add(ticket.get(1));
        }

        stack.push("JFK");
        while(!stack.isEmpty()){
            String nextDestination= stack.peek();

            if(!adjMap.getOrDefault(nextDestination, new PriorityQueue<>()).isEmpty()){
                stack.push(adjMap.get(nextDestination).poll());
            }else{
                itinerary.addFirst(stack.pop());
            }
        }
        return itinerary;


    }
}
//TC: O(V + E)^2
//SC: O(V + E)
