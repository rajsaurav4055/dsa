package 1-D DP;

import java.util.Arrays;
//Question url: https://www.naukri.com/code360/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=SUBMISSION
//Memoization
public class NinjaTraining {
    public static int ninjaTraining(int n, int points[][]) {
        int[][] dp=new int[n+1][4];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return ninjaTrainingUtil(n-1, points, 3, dp);
    }
    public static int ninjaTrainingUtil(int index, int points[][], int lastAct,int dp[][]) {
            //Base case
            if(index==0){
                int max= Integer.MIN_VALUE;
                for(int i=0; i<3;i++){
                    if(i!=lastAct){
                        max=Math.max(max, points[index][i]);
                    }
                }
                return dp[index][lastAct]=max;
            }

            if(dp[index][lastAct] != -1)return dp[index][lastAct];
            int max=Integer.MIN_VALUE;
            for(int i=0; i<3; i++){
                
                if(i!=lastAct){
                    int point = points[index][i] + ninjaTrainingUtil(index - 1, points, i,dp);
                    max=Math.max(max, point);
                }
                
            }
            return dp[index][lastAct]=max;
    }

}
//TC: O(N*4)
//SC: O(N*4)
