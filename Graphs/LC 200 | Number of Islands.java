package Graphs;
//you can obviously do it by yourself, it is that simple
//using dfs
//TC: O(m*n)
//SC: O(m*n) -> recursion stack(worst case)

//Algorithm
//Scan the matrix from (0,0) to (N, M).
//If the current element is ‘1’, start a DFS.
//In the DFS traversal, mark every visited node.
//Count the number of islands as the number of nodes that trigger the DFS.
//Return count.
// class NumberOfIslands {
//     public void dfs(char[][] grid, int r, int c){
//         int nr= grid.length;
//         int nc= grid[0].length;

//         //recursion break condition
//         if(r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0')return;

//         grid[r][c] = '0'; //we are marking that this node has been visited now
//         dfs(grid, r, c-1);//check left
//         dfs(grid, r, c+1);//check right
//         dfs(grid, r-1, c);//check up
//         dfs(grid, r+1, c);//check down
//     }
//     public int numIslands(char[][] grid) {
//         if(grid == null || grid.length == 0){
//             return 0;
//         }
        
//         int numberOfIslands= 0;
//         int nr= grid.length;
//         int nc= grid[0].length;

//         for(int i=0; i < nr; i++){
//             for(int j=0; j< nc; j++){
//                 if(grid[i][j] == '1'){
//                     numberOfIslands++;
//                     dfs(grid,i,j);
//                 }
//             }
//         }
//         return numberOfIslands;
        
//     }
//     //After running the dfs through the code, I realised that the dfs recursion can be further optimised using dp ;)
// }


//BFS solution 

//Algorithm
//Scan the matrix from (0,0) till (N, M).
//If the current element is ‘1’, start a BFS.
//Consider a queue and put the current node into the queue.
//Iteratively visit its neighbours vertically and horizontally and mark them as visited.
//The count is the total number of times the BFS has been invoked.
//Return count.
class NumberOfIslands{
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int nr= grid.length;
        int nc= grid[0].length;
        int num_islands=0;

        for(int r=0; r < nr; r++){
            for(int c=0; c < nc; c++){
                if(grid[r][c] == '1'){
                    num_islands++;
                    grid[r][c]= '0'; //marked as visited
                    //NOTE we are queues implemented using LinkedList
                    Queue<Integer> neighbours= new LinkedList<>(); 
                    //NEW AND TRICKY WAY TO STORE the row and column number using just the integer
                    neighbours.add(r*nc + c);
                    while(!neighbours.isEmpty()){
                        int id= neighbours.remove();
                        int row= id/nc;
                        int col= id%nc;
                        if(row - 1 >=0 && grid[row-1][col]=='1'){
                            neighbours.add((row - 1)*nc + col);
                            grid[row - 1][col]='0';
                        }
                        if(row + 1 < nr && grid[row+1][col]=='1'){
                            neighbours.add((row + 1)* nc + col);
                            grid[row + 1][col]='0';
                        }
                        if(col - 1 >= 0 && grid[row][col - 1]=='1'){
                            neighbours.add((row)*nc + (col-1));
                            grid[row][col-1]='0';
                        }
                        if(col + 1  < nc && grid[row][col + 1]=='1'){
                            neighbours.add((row)*nc + (col + 1));
                            grid[row][col + 1]='0';
                        }

                    }

                }
            }
        }
        return num_islands;
    }
    //TC: O(M*N)
    //SC: O(min(M,N)) because in worst case when all the cell filled with 1, the queue cna grow upto min(M,N)

}

//Takeaways
//1. Queues for BFS in java is implementation of LinkedList --> Queue<Integer> neighbours = new LinkedList<>();
//2. Interesting way of storing both the rows and column in the form of an Integer. r*nc+ c. and then to get row divide this value by nc and to get c just take mod of this. 

