package 3D DP;

//Memoization
//We are also moving both robots together so both of them will reach the destination at the same time
class Solution {
    public int cherryPickup(int[][] grid) {
        int r= grid.length;
        int c= grid[0].length;
        int[][][] dp=new int[r][c][c];
        for(int[][] rows:dp){
            for(int[] row:rows){
                Arrays.fill(row,-1);
            }
        }
        return cherryPickUtil(grid, 0, 0, c-1, r, c, dp);
    }

    public int cherryPickUtil(int[][] grid, int i, int j1, int j2, int r, int c, int[][][] dp) {
        //Out of bound base case
        if(j1<0 || j1>=c || j2<0 || j2>=c)return Integer.MIN_VALUE;
        
        //When the robots reach the destination base case
        if(i == r-1 ){
            if(j1 == j2){
                return grid[i][j1];
            }else{
            return grid[i][j1] + grid[i][j2];
            }
        } 
        if(dp[i][j1][j2]!= -1)return dp[i][j1][j2];
        int maxi= -(int)Math.pow(10,8);
        //Only j changes, i will always be i+1
        //So for each j1-1, j2 can be j2-1, j2 and j2+1-> So that is what we are trying to achieve from this for nested loops
        //Very interesting approach
        for(int k=-1; k<=1; k++){
            for(int l=-1; l<=1; l++){
                if(j1==j2)maxi=Math.max(maxi, grid[i][j1] + cherryPickUtil(grid, i+1, j1+k, j2+l, r, c, dp));
                else maxi=Math.max(maxi, grid[i][j1] + grid[i][j2] + cherryPickUtil(grid, i+1, j1+k, j2+l, r, c, dp));
            }
        }
        return dp[i][j1][j2] = maxi;
    }
}
//TC: Number of states which is O(N*M*M)
//SC: Recursion stack + dp matrix space => O(n) + O(m*n*n)

//To remove the recursion stack space
