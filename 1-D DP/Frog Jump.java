package 1-D DP;


// Question Link: https://www.naukri.com/code360/problems/frog-jump_3621012?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
//TakeuForward explanation: https://www.youtube.com/watch?v=EgG3jsGoPvQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=5&ab_channel=takeUforward
import java.util.* ;
import java.io.*; 
public class FrogJump {
    public static int frogJumpUtil(int ind, int heights[], int[] dp) {
        if(ind==0)return 0;
        if(dp[ind]!=-1)return dp[ind];
        int l=frogJumpUtil(ind-1,heights,dp) + Math.abs(heights[ind] - heights[ind-1]);
        int r=Integer.MAX_VALUE;
        if(ind > 1)r= frogJumpUtil(ind-2,heights,dp) + Math.abs(heights[ind] - heights[ind-2]);

        return dp[ind]= Math.min(l,r);
    }

    public static int frogJump(int n, int heights[]) {
        int[] dp=new int[n+1];
        Arrays.fill(dp, -1);
        return frogJumpUtil( n-1, heights,dp);
    }

}

//TC: O(n)
//SC: O(n)
