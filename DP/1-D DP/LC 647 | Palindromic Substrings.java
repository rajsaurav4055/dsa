//DP solution

//Code is almost same as Longest Palindromic Substring question.. just slight change of introducing count
// class Solution {
//     public int countSubstrings(String s) {
//         int n=s.length();
//         boolean[][] dp=new boolean[n][n];
//         int count=0;
        
//         for(int i=0; i<s.length(); i++){
//             dp[i][i]=true;
//             count++;
//         }

//         for(int i=0;i<n-1; i++){
//             if(s.charAt(i) == s.charAt(i+1)){
//                 count++;
//                 dp[i][i+1]=true;
//             }
//         }

//         for(int diff=2; diff < s.length(); diff++){
//             for(int i=0; i<n-diff; i++){
//                 int j=i+diff;
//                 if(s.charAt(i)==s.charAt(j) && dp[i+1][j-1]){
//                     count++;
//                     dp[i][j]=true;
//                 }
//             }
//         }
//         return count;
        
//     }

    
// }
//TC: O(n^2)
//SC: O(n)

//Expanding from centre
class Solution {
    public int countSubstrings(String s) {
        int count=0;
        for(int i=0; i<s.length(); i++){
            count+=countPalindromeAroundCentre(i,i,s);
            count+=countPalindromeAroundCentre(i,i+1,s);
        }
        return count;
        
    }

    private int countPalindromeAroundCentre(int i, int j, String s){
        int left=i;
        int right=j;
        int count=0;
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
            count++;
        }
        return count;
    }

    
}
//TC: O(n^2)
//SC: O(1)