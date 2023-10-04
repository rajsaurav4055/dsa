package Graphs;

class GraphValidTreeSolution {
    private int[] parent;
    private int[] rank;
    public boolean validTree(int n, int[][] edges) {
        parent= new int[n];
        rank= new int[n];
        for(int i=0; i<n; i++){
            parent[i]=i;
            rank[i]=1;
        }

        int result=n;//this is to keep track of connected components
        for(int i=0; i<edges.length; i++){
            if(union(edges[i][0],edges[i][1])==0){
                return false;
            }else{
                result--;
            }
        }

        if(result > 1){
            return false;
        }
        return true;
    }

    private int find(int node){
        int result = node;
        while(parent[result] != result){
            parent[result]=parent[parent[result]];
            result= parent[result];
        }
        return result;
    }

    private int union(int n1, int n2){
        int p1= this.find(n1);
        int p2= this.find(n2);

        if(p1 == p2){
            return 0;
        }

        if(rank[p1]>rank[p2]){
            parent[p2]=p1;
            rank[p1]+=rank[p2];
        }else{
            parent[p1]=p2;
            rank[p2]+=rank[p1];
        }
        return 1;
    }
}
//TC is O(N * alpha(N)) where alpha(N) never exceeds value of 4 in real world scenario. So it is technically equal to O(N)
//So this is the best approach to find if a graph is valid tree or not compared to DFS or BFS which has a TC of (O(E+V))

//SC: O(N) to store parent and rank array 
