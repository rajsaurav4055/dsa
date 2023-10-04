//THis is an important question to practice Union Find Algorithm

class RedundantConnectionSolution {
    int MAX_EDGE_VAL = 1000;
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu=new DSU(MAX_EDGE_VAL + 1);
        for(int[] edge: edges){
            if(dsu.union(edge[0],edge[1])== false)return edge;
        }
        throw new AssertionError();
    }
}

class DSU {
    int[] parent;
    int[] rank;

    //constructor
    public DSU(int size){
        parent= new int[size];
        rank= new int[size];
        for(int i=0; i<size; i++)parent[i]=i;
    }

    //find the parent (not clear yet)
    public int find(int x){
        //if i is not the parent of itself, then i is not the representative of 
        // his set. So we recursively call Find on its parent
        if(parent[x] != x)parent[x]=find(parent[x]);
        return parent[x];
    }

    //union function
    //It takes two elements as input and finds the representatives of their    sets using the Find operation, and finally puts either one of the trees (representing the set) under the root node of the other tree.
    public boolean union(int x, int y){
        int parentx= find(x), parenty= find(y);
        if(parentx == parenty){
            //this means it already belongs to the same set
            return false;
        }else if(rank[parentx] < rank[parenty]){
            parent[parentx] = parenty;
        }else if(rank[parentx] > rank[parenty]){
            parent[parenty]=parentx;
        }else{
            parent[parenty]=parentx;
            rank[parentx]++;
        }
        return true;
    }

}