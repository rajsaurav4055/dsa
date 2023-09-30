//using dfs -> recursive and also count as a global variable
//My solution beat 100 percent users in Java
// class Solution {
//     int count=0; //global variable
//     int dfs(int[][] grid, int r, int c){
//         int nr= grid.length;
//         int nc=grid[0].length;

//         if(r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == 0){
//             return 0;
//         }
//         grid[r][c] = 0;
//         count+=1;
//         dfs(grid, r+1, c);
//         dfs(grid, r-1, c);
//         dfs(grid, r, c+1);
//         dfs(grid, r, c-1);
//         return count;

//     }
//     public int maxAreaOfIsland(int[][] grid) {
//         int maxArea=0;
//         int nr=grid.length;
//         int nc=grid[0].length;

//         if(grid== null || grid.length==0)return 0;

//         for(int r=0; r < nr; r++){
//             for(int c=0; c < nc; c++){
//                 if(grid[r][c] == 1){
//                     int area = dfs(grid, r, c); 
//                     if(area > maxArea){
//                         maxArea = area;
//                     }
//                     count=0;
//                 }
//             }
//         }
//         return maxArea;
//     }
//     //TC: O(m*n)
//     //SC: O(m*n) for the recursion stack if all of the cells is 1
// }


//Suppose the interviewer says we can't use global variable
// class Solution {
//     int dfs(int[][] grid, int r, int c){
//         int nr= grid.length;
//         int nc=grid[0].length;

//         if(r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == 0){
//             return 0;
//         }
//         grid[r][c] = 0;
//         return 1 + dfs(grid, r+1, c) + dfs(grid, r-1, c) + dfs(grid, r, c+1) + dfs(grid, r, c-1);
//     }

//     public int maxAreaOfIsland(int[][] grid) {
//         int maxArea=0;
//         int nr=grid.length;
//         int nc=grid[0].length;

//         if(grid== null || grid.length==0)return 0;

//         for(int r=0; r < nr; r++){
//             for(int c=0; c < nc; c++){
//                 if(grid[r][c] == 1){
//                     int area = dfs(grid, r, c); 
//                     if(area > maxArea){
//                         maxArea = area;
//                     }
//                 }
//             }
//         }
//         return maxArea;
//     }
//     //TC: O(n*m)
//     //SC: O(n*m)
// }

//Now what if the interviewer says, I don't want you to write a recursive dfs. Write an iterative dfs

//Iterative dfs is written using stack
//Improved space as we don't need to use an extra seen 2D array for visited. We mark the cells as visited in the original given array
class maxAreaOfIslandSolution {

    public int maxAreaOfIsland(int[][] grid) {

        if(grid== null || grid.length==0)return 0;
        int maxArea=0;
        int nrows=grid.length;
        int ncolumn=grid[0].length;
        int[] dr=new int[]{1, -1, 0, 0}; //I didn't know how to define this in an array
        int[] dc=new int[]{0, 0, 1, -1};

        for(int r=0; r < nrows; r++){
            for(int c=0; c < ncolumn; c++){
                if(grid[r][c] == 1){
                    int area=0;
                    Stack<int[]> stack=new Stack();//this is how we initialize stack. <> is missig?
                    stack.push(new int[]{r,c});//queue me add function hota hai
                    grid[r][c]=0;//marked r,c as visited
                    while(!stack.empty()){
                        int[] node=stack.pop();
                        int row=node[0], col= node[1];
                        area++;

                        //Now navigate all four directions to the current row and col
                        for(int k=0; k<4; k++){
                            int nr= row + dr[k];
                            int nc= col + dc[k];

                            if(nr >=0 && nc>=0 && nr < nrows && nc < ncolumn && grid[nr][nc]== 1){
                                stack.push(new int[]{nr,nc});
                                grid[nr][nc] = 0;
                            }
                        }
                    }
                    maxArea= Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
    //TC: O(n*m)
    //SC: O(n*m) -> The space used by stack
}

//Takeaways
// 1. Iterative code of dfs (Code 3)
// 2. How to use recursion to add area without using a global variable (Code number 2)
// 4. Did the first one by myself so good for confidence :)
// 5. How to initialize and use stack. It is different than queues