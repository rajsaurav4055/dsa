package Graphs;

//Reverse thinking strategy - Neetcode
//Using dfs
//TC: O(m*n)
//Space: O(m*n) recusrsion stack
class SurroundedRegionsSolution {
    void dfs(char[][] board, int r, int c){
        int nr=board.length, nc= board[0].length;
        if(r < 0 || c < 0 || r >=nr || c >= nc || board[r][c]!='O')return;

        //Mark unsurrounded cells to 'T' on temporary basis
        board[r][c]='T';
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);

    }
    public void solve(char[][] board) {
        int rows= board.length, cols= board[0].length;
        //1. part 1 Capture unsurrounded regions => left and right border columns => O->T
        for(int i=0; i<rows; i++){
            if(board[i][0] == 'O')dfs(board, i, 0);
            if(board[i][cols-1]=='O')dfs(board, i, cols - 1);
        }

        //1. Part 2  Capture unsurrounded regions => top and bottom border rows => O->T
        for(int i=0; i<cols; i++){
            if(board[0][i] == 'O')dfs(board, 0, i);
            if(board[rows - 1][i]=='O')dfs(board, rows-1, i);
        }

        
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                //2. Converting all the remaining O which is surrounded to X
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                //3. And converting all the T back to O
                if(board[i][j] == 'T'){
                    board[i][j] = 'O';
                }
            }
        }

    }
}
