package Graphs;

//Intuition is typical BFS
class RottingOrangesSolution {
    public int orangesRotting(int[][] grid) {
        int rows= grid.length;
        int cols= grid[0].length;
        Queue<int[]> rottenQueue= new LinkedList<>(); //space
        
        //Push first level of rotten oranges in the queue and
        //Count the total number of fresh oranges
        //We are counting total fresh oranges because once our bfs is complete, we will check if there is any fresh orange left and if it is left then we will have to return -1 as per the question
        int fresh=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 2)rottenQueue.add(new int[]{i,j});
                else if(grid[i][j]==1)fresh+=1;
            }
        }

        int timecount = 0;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

        while(!rottenQueue.isEmpty() && fresh > 0){
            
            int size= rottenQueue.size();
            
            //this for loop is to pop all the oranges of one level from the queue and add all the next level of fresh oranges 4-directionally adjancent that is going to become rotten in the next minute.
            for(int i=0; i<size; i++){
                
                int[] rotten= rottenQueue.poll();
                int r=rotten[0], c=rotten[1];

                for(int[] d : dir){

                    int x= r+ d[0], y= c+ d[1];

                    if(x >=0 && y>=0 && x<rows && y<cols && grid[x][y] == 1){
                        rottenQueue.add(new int[]{x, y});
                        grid[x][y]=2;
                        fresh-=1;
                    }
                }
                
            }
            timecount++;
        }

        return fresh > 0?-1:timecount;


    }
    //TC: O(M * N)
    //SC: O(M * N)
}
