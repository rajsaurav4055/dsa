package Graphs;

//Trick is to do bfs from gates towards the empty rooms
//Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1, the distance to an empty room must be the shortest.
class WallsAndGatesSolution {
    // void dfs(int[][] rooms, int r, int c){
    //     int nr= rooms.length();
    //     int nc=rooms.length();

    // }
    
    
    public void wallsAndGates(int[][] rooms) {
        int rows=rooms.length, cols=rooms[0].length;
        Queue<int[]> gateQueue=new LinkedList<>();

        //push all gates in the queue
        //O(m*n)
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(rooms[i][j] == 0){
                    gateQueue.add(new int[]{i,j});
                }
            }
        }
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        //Don't over complicate it. it is straight forward actually
        //Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1, the distance to an empty room must be the shortest.
        while(!gateQueue.isEmpty()){
               int[] cell= gateQueue.poll(); 
               int r=cell[0], c=cell[1];
                for(int[] d: dir){
                    int x= r + d[0], y= c+d[1];
                    if(x < 0 || y < 0 || x >= rows || y >= cols || rooms[x][y]!=Integer.MAX_VALUE)continue;
                    rooms[x][y] = rooms[r][c] + 1; //making use of the fact that value of gate =0
                    gateQueue.add(new int[]{x,y});
                }
            }
        }

}
