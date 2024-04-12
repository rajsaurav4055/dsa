import java.util.*

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        //lcs tabulation code
        int m=str1.length();
        int n=str2.length();
        int[][] dp=new int[m+1][n+1];
        //Base case
        for(int i=0; i<=n; i++)dp[0][i]=0;
        for(int j=0; j<=m; j++)dp[j][0]=0;
        
        for(int i=1; i<=m ; i++){
            for(int j=1; j<=n; j++){
                //match
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j]=1 + dp[i-1][j-1];
                }else{
                    //not match
                    dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);
                }
                
            }
        }

        String ans="";
        int i=m, j=n;
        //1-based indexing 
        while(i>0 && j>0){
            if(str1.charAt(i-1)== str2.charAt(j-1)){
                ans+=str1.charAt(i-1);
                i--;
                j--;
            }else if(dp[i-1][j] > dp[i][j-1]){
                ans+=str1.charAt(i-1);
                i--;
            }else{
                ans+=str2.charAt(j-1);
                j--;
            }
        }
        while(i>0){
            ans+=str1.charAt(i-1);
            i--;
        }
        while(j>0){
            ans+=str2.charAt(j-1);
            j--;
        }

        //Note how to convert to a string builder and then reverse
        String ans2=new StringBuilder(ans).reverse().toString();
        return ans2;
    }
}
//TC:O(M*N)
//SC: O(M*N)


//If they had asked the length of the shortest common supersequence then:
// it is len(s1)+len(s2)-lcs(s1,s2)