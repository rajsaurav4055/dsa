//top down with memoization
//last position se count krta hai and backward memo wale array ko fill krte jata hai
//slightly non intuitve to me
// class Solution {
//     public int numDecodings(String s) {
//         return numDecodingsUtil(s, 0, new Integer[s.length()]);
//     }

//     private int numDecodingsUtil(String s, int i, Integer[] memo){
//         if(i==s.length())return 1;
//         if(s.charAt(i)=='0')return 0;
//         if(memo[i] != null)return memo[i];

//         //include the current character
//         int count=0;
//         count+=numDecodingsUtil(s,i+1,memo);
//         if(i < s.length()-1 && (s.charAt(i)=='1' || s.charAt(i)=='2' && s.charAt(i+1)<'7')){
//             //include the next character and move on to the further characters
//             count+=numDecodingsUtil(s,i+2,memo);   
//         }
//         memo[i]=count;
//         return memo[i];
//     }
// }
//TC: O(n)
//SC: O(1)

//bottom up
// class Solution {

//     public int numDecodings(String s) {
//         int[] dp = new int[s.length() + 1];
//         dp[0] = 1; // empty string
//         dp[1] = s.charAt(0) == '0' ? 0 : 1;
//         for (int i = 2; i < s.length() + 1; i++) {
//             if (s.charAt(i - 1) != '0') {
//                 dp[i] += dp[i - 1];
//             }
//             if (
//                 s.charAt(i - 2) == '1' ||
//                 (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')
//             ) {
//                 dp[i] += dp[i - 2];
//             }
//         }
//         return dp[s.length()];
//     }
// }
//TC:O(n)
//SC: O(1)

// Optimal
class Solution {

    public int numDecodings(String s) {
        int twoBack = 1; // empty string
        int oneBack = s.charAt(0) == '0' ? 0 : 1;
        int current = oneBack;
        for (int i = 2; i < s.length() + 1; i++) {
            current = 0;
            if (s.charAt(i - 1) != '0') {
                current += oneBack;
            }
            if (
                s.charAt(i - 2) == '1' ||
                (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')
            ) {
                current += twoBack;
            }
            twoBack = oneBack;
            oneBack = current;
        }
        return current;
    }
}
//TC:O(n)
//SC: O(1)