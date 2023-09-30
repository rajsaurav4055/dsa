package Graphs;

//using dfs i.e. recursive
class PacificAtlanticSolution {

    int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
    void dfs(int[][]heights, int r, int c, int prev, boolean[][] ocean){
        int nr= heights.length, nc=heights[0].length;

        //recursion break condition
        if(r < 0 || c < 0 || r >=nr || c >= nc || heights[r][c] < prev || ocean[r][c]==true )return;

        ocean[r][c]=true;//mark as visited
        //check in all four directions
        for(int[] d: dir){
            dfs(heights, r + d[0], c + d[1], heights[r][c], ocean);
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();//this is how we initialize List
        int nr= heights.length;
        int nc= heights[0].length;
        boolean[][] pacific= new boolean[nr][nc];
        boolean[][] atlantic= new boolean[nr][nc];

        //Starting dfs from the edges thats near to the oceans horizontally
        for(int i=0; i<nc; i++){
            dfs(heights, 0, i, Integer.MIN_VALUE, pacific);
            dfs(heights, nr-1, i, Integer.MIN_VALUE, atlantic);
        }

        //Starting dfs from the edges thats near to the oceans horizontally
        for(int j=0; j<nr; j++){
            dfs(heights, j, 0, Integer.MIN_VALUE, pacific);
            dfs(heights, j, nc-1, Integer.MIN_VALUE, atlantic);
        }

        //once we apply the dfs from all sides we compare the boolean arrays for true
        //If the cell is true in both atlantic and pacific then push it in results
        for(int i=0; i< nr; i++){
            for(int j=0; j<nc; j++){
                if(atlantic[i][j] && pacific[i][j]){
                    result.add(Arrays.asList(i,j));//This is how we add in a list
                }
            }
        }
        return result;
    }
}


//C++ using bfs iterative solution 
// int redvisited[1001][1001], bluevisited[1001][1001];

// //to check if the current coordinate I am on is a valid cell that exists or not
// bool check(int x, int y, int n, int m){
//     if(x>=0 && x<n && y>=0 && y<m){
//         return true;
//     }
//     return false;
// }

// int Solution::solve(vector<vector<int> > &A) {
//     //number of rows, number of columns
//     int n=A.size(), m= A[0].size();
//     int result =0;
//     //marking all the cells in red and blue matrices as 0 for not visited
//     for(int i=0; i<n; i++){
//         for(int j=0; j<m; j++){
//             bluevisited[i][j] = 0;
//             redvisited[i][j] = 0;
//         }
//     }
//     //queue for performing dfs Note: queue of a pair to store coordinates
//     queue<pair<int,int>> q;
    
//     //All the coordinates which touches blue lake on left edge
//     //started from i=1 because (0,0) will be included in the next for loop
//     for(int i=1; i<n; i++){
//         bluevisited[i][0]+=1;
//         q.push({i,0});
//     }
//     //All the coordinates which touches blue lake on top edge 
//     for(int i=0; i<m; i++){ //(0,0) included here
//        bluevisited[0][i]+=1; 
//        q.push({0,i});
//     }
    
//     //this is a new concept to me. this helps in moving in the 4 direction, up, down, left and right
//     int dx[4]= {0,0,1,-1};
//     int dy[4]= {1, -1, 0, 0};
    
//     //bfs for the blue lake 
//     while(!q.empty()){
//         pair<int,int> idx = q.front();
//         q.pop();
//         for(int i=0; i<4; i++){
//             int x= idx.first + dx[i];
//             int y= idx.second + dy[i];
//             if(check(x,y,n,m) && bluevisited[x][y]==0 && A[x][y] >= A[idx.first][idx.second]){
//                 q.push({x,y});
//                 bluevisited[x][y]+=1;
//             }
//         }        
//     }
    
//     //All the coordinates which touches red lake on right edge
//     for(int i=0; i<n; i++){ //right corner included in this loop so will be excluded in the next loop
//         redvisited[i][m-1]+=1;
//         q.push({i,m-1});
//     }
//     //All the coordinates which touches red lake on bottom edge 
//     for(int i=m-2; i>=0; i--){ 
//        redvisited[n-1][i]+=1; 
//        q.push({n-1,i});
//     }
    
//     //bfs for red lake + counting the common cells
//     while(!q.empty()){
//         pair<int,int> idx= q.front();
//         //This is to check if the current coordinate has also been visited by blue lake
//         if(redvisited[idx.first][idx.second] == 1 && bluevisited[idx.first][idx.second] == 1){
//             result++;
//         }
//         q.pop();
//         for(int i=0; i<4; i++){
//             int x= idx.first + dx[i];
//             int y= idx.second + dy[i];
//             if(check(x,y,n,m) && redvisited[x][y]==0 && A[x][y] >= A[idx.first][idx.second]){
//                 q.push({x,y});
//                 redvisited[x][y]+=1;
//             }
//         }
//     }
//     return result;
    
    
// }


//Notes
//Kind of easy but becomes complex due to the complexity of 2D arrays but we can see a good way on how to navigate in 4 directions
//TC: worst case O(M*N)
//SC: O(M*N)
