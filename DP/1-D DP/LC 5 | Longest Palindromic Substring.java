
//Brute force is you find all the possible substring and then check each of them that is it palindrome.. It takes O(n^3) time

//Then we can use DP to store the previously visited boundaries.. and reduce it to O(n^2)
// class Solution {
//     public String longestPalindrome(String s) {
//         int n=s.length();
//         boolean[][] dp=new boolean[n][n];
//         int[] ans=new int[]{0,0};

//         //substrings of length 1
//         for(int i=0; i<n; i++){
//             dp[i][i]=true;
//         }

//         //checking the substrings of length 2
//         for(int i=0; i<n-1; i++){
//             if(s.charAt(i) == s.charAt(i+1)){
//                 dp[i][i+1]=true;
//                 ans[0]= i;
//                 ans[1]= i+1;
//             }
//         }

//         for(int diff=2; diff < n ; diff++){
//             for(int i=0; i< n-diff; i++){
//                 int j= i + diff;
//                 if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]==true){
//                     dp[i][j]=true;
//                     ans[0]=i;
//                     ans[1]=j;
//                 }
//             }
//         }
//         int i=ans[0];
//         int j=ans[1];

//         return s.substring(i,j+1);
    
//     }



// }
//TC: O(n^2)
//SC: O(n)





//Best way is to check the palindrome expanding from the center.. And there will be n odd centres and n-1 even centres, so we need to go through 2n-1 iterations in total which is an optimized version of DP solution
class Solution {
    public String longestPalindrome(String s) {
        int[] ans=new int[]{0,0};

        for(int i=0; i<s.length(); i++){
            //Checking all the substring which is odd in length
            int oddLength= expand(i,i,s);
            if(oddLength > ans[1] - ans[0] + 1){
                //dist is the number of characters on each side of the center
                int dist= oddLength/2;
                ans[0] = i - dist;
                ans[1] = i + dist;
            }

            //Checking all the substring which is even in length
            int evenLength= expand(i,i+1,s);
            if(evenLength > ans[1] - ans[0] + 1){
                int dist= (evenLength/2) - 1;
                ans[0]= i - dist;
                ans[1]= i + 1 + dist;
            }
        }
        int i=ans[0];
        int j=ans[1];

        return s.substring(i, j + 1);//note the way to return a substring from a string- upper boundary should be increases by 1 if we need to pick up till j
    }

    //returns the length of all the substrings which is a valid palindrome
    private int expand(int i, int j, String s){
        int left=i;
        int right=j;

        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return right - left - 1;
    }


}
//TC: O(n^2)
//SC: O(1)