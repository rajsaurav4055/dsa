package Graphs;


    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class cloneGraphProblem {
    HashMap<Integer, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null)return null;
        if(visited.containsKey(node.val))return visited.get(node.val);
        //create a copy node from the old node
        Node newNode= new Node(node.val, new ArrayList<Node>());
        //Hashmap me we use put function not add
        visited.put(node.val, newNode);

        //iterating to it's neigbours and creating them as well (dfs)
        for(Node iter : node.neighbors){
            newNode.neighbors.add(cloneGraph(iter));
        }
        return newNode;
    }
    //TC: O(V + E)
    //SC: Hashmap-> O(N) + O(H) = Height of the graph for the recursion stack
}
