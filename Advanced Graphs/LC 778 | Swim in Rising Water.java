
//this solution is modified Dijkstra algo
//NC solution
class Solution {
    private int[][] dir={{0,-1},{0,1},{1,0},{-1,0}};
    public int swimInWater(int[][] grid) {
        int len=grid.length;
        if(len == 1)return 0;
        boolean[][] seen=new boolean[len][len];
        seen[0][0]=true;
        //declaring minHeap
        PriorityQueue<Integer[]> minHeap=new PriorityQueue<Integer[]>((a,b)-> a[0] - b[0]);
        minHeap.add(new Integer[]{grid[0][0],0,0});
        int result=0;

        while(!minHeap.isEmpty()){
            Integer[] curr=minHeap.poll();
            result=Math.max(result, curr[0]);

            if(curr[1]==len-1 && curr[2]==len-1)break;

            for(int i=0; i<4; i++){
                int x= curr[1] + dir[i][0];
                int y=curr[2] + dir[i][1];
                if(x > len-1 || y > len -1 || x < 0 || y < 0 || seen[x][y]==true)continue;
                minHeap.add(new Integer[]{grid[x][y],x,y});
                seen[x][y]=true;
            }
        }
        return result;
    }
}
//TC: O(N^2 log N). We may expand O(N^2) nodes, and each one requires O(log⁡N) time to perform the heap operations.
//SC:O(N^2)
