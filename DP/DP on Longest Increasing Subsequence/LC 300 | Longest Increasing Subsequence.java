//BEST SOLUTION
//Now for Amazon, Google and Microsoft, they won't ask for the O(n^2) for LIS
//They will need the O(nlog n) solution, which can be achieved using BINARY SEARCH
//The code and the logic below

//Main use of binary search here is
// if there is an element arr[i], find arr[i]-> if not found, then find element > arr[i]

//This is the BEST SOLUTION as it takes O(nlogn) time -> every other solutions take atleast O(n^2)


//BEST SOLUTION
//Using Binary Search
class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(nums[0]);
        int len=1;
        for(int i=1; i<nums.length; i++){
            if(nums[i] > temp.get(temp.size()-1)){
                temp.add(nums[i]);
                len++;
            }else{//replace
                //do binary search and replace it with the another element
                int index=  Collections.binarySearch(temp, nums[i]);
                if(index < 0){
                    index= -index-1;
                }
                temp.set(index, nums[i]);
            }
        }
        return len; 
    }
}

//TC: O(nlogn)
//SC: O(n)


//DP SOLUTIONS


//recursive
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        return lengthOfLISUtil(0,-1,nums,n);
    }

    public int lengthOfLISUtil(int index, int prevIndex, int[] nums, int n){
        
        //Pehle mai ye base case daala tha but it is simply repeating so apan ne take k condition me likha hai
        // and -1 as a prevIndex direct isme jaa rha tha for an array with 1 element giving out of bound error
        // On closer look you can see that instead of n-1, we should directly check if(index == n) which hm logone baad me kara
        
        // if(index==n-1){
        //     if(nums[prevIndex] < nums[index])return 1;
        //     else return 0;
        // }
        
        
        //base case
        if(index == n)return 0;
        
        //explore all possibilities
        int notTake = lengthOfLISUtil(index + 1, prevIndex, nums,n);
        int take=Integer.MIN_VALUE;
        if(prevIndex == -1 || nums[prevIndex] < nums[index]){
            take= 1 + lengthOfLISUtil(index + 1, index, nums,n);
        }

        return Math.max(notTake, take);
    }
}
//TC: O(exponential)
//SC: O(n)


//Memoization
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        //we will do coordinate shift for the prevIndex so that dp[0][-1] converts to dp[0][0]
        //so whatever we were going to store at dp[i][j] previous we will store it at dp[i][j+1]
        int[][] dp=new int[n][n+1]; 
        for(int[] rows:dp)Arrays.fill(rows,-1);
        return lengthOfLISUtil(0,-1,nums,n,dp);
    }

    public int lengthOfLISUtil(int index, int prevIndex, int[] nums, int n, int[][] dp){
        //base case
        if(index == n)return 0;
        if(dp[index][prevIndex + 1]!=-1)return dp[index][prevIndex + 1];//coordinate shift change
        
        //explore all possibilities
        int notTake = lengthOfLISUtil(index + 1, prevIndex, nums,n, dp);
        int take=Integer.MIN_VALUE;
        if(prevIndex == -1 || nums[prevIndex] < nums[index]){
            take= 1 + lengthOfLISUtil(index + 1, index, nums,n, dp);
        }

        return dp[index][prevIndex + 1] = Math.max(notTake, take);//coordinate shift change
    }
}

//TC: O(n^2)
//SC: O(n ^ 2) + O(n + n)




//tabulation
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int[][] dp=new int[n+1][n+1]; 
        for(int index=n-1; index>=0; index--){
            for(int prevIndex=index-1; prevIndex>=-1; prevIndex--){//Important note: j=i-1 kyuk prevIndex hai inner loop and wo toh index k before se saara piche hi hoga na
                
                //coordinate shift, second parameter of dp sb jagah +1 add kr diye on memoized solution
                int notTake = dp[index + 1][prevIndex+1];
                int take=Integer.MIN_VALUE;
                if(prevIndex == -1 || nums[prevIndex] < nums[index]){
                    take= 1 + dp[index + 1][index+1];
                }
                dp[index][prevIndex + 1] = Math.max(notTake, take);
            }
        }

        return dp[0][0];//dp[0][-1+1]

    }
}

//TC: O(n^2)
//SC: O(n^2)




//space optimized
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int[] next=new int[n+1];
        int[] current=new int[n+1];
        // int[][] dp=new int[n+1][n+1]; 
        for(int index=n-1; index>=0; index--){
            for(int prevIndex=index-1; prevIndex>=-1; prevIndex--){//Important note: j=i-1 kyuk prevIndex hai inner loop and wo toh index k before se saara piche hi hoga na
                
                //coordinate shift, second parameter of dp sb jagah +1 add kr diye on memoized solution
                int notTake = next[prevIndex+1];
                int take=Integer.MIN_VALUE;
                if(prevIndex == -1 || nums[prevIndex] < nums[index]){
                    take= 1 + next[index+1];
                }
                current[prevIndex + 1] = Math.max(notTake, take);
            }
            next=current;
        }

        return next[0];//dp[0][-1+1]

    }
}

//TC: O(n^2)
//SC: O(n) * 2

//But this is not the best solution
//There is a separate tabulation method where the space can be optimized to O(n)


//see the explanation at take u forward
//this solution is also required to print the lis
//tabulation
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n+1]; //dp[i] represents the longest LIS till the ith index so far
        Arrays.fill(dp,1);
        for(int index=0; index<n; index++){
            for(int prev=0; prev <index; prev++){
                if(nums[prev] < nums[index])
                    dp[index]= Math.max(dp[prev] + 1, dp[index]);
            }
        }
        int max=0;
        for(int i=0; i<n;i++){
            max=Math.max(dp[i],max);
        }
        return max;
    }
}
//TC: O(n^2)
//SC: O(n)


//print lis
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        int[] hash=new int[n];//this is used to keep track of the previous index of the lis 
        int maxi=1;
        int lastIndex=0;
        Arrays.fill(dp,1);
        for(int index=0; index<n; index++){
            hash[index]=index;
            for(int prev=0; prev <index; prev++){
                if(nums[prev] < nums[index] && dp[prev] + 1 > dp[index]){
                        dp[index]=dp[prev] + 1;
                        hash[index]=prev;
                }
            }
            if(dp[index] > maxi){
                maxi=dp[index];
                lastIndex=index;
            }
        }

        //adding the lis to the list
        ArrayList<Integer> lis=new ArrayList<>();
        lis.add(nums[lastIndex]);
        while(hash[lastIndex]!=lastIndex){
            lastIndex=hash[lastIndex];
            lis.add(nums[lastIndex]);
        }

        //printing the lis
        for(int i=lis.size()-1; i>=0;i--){
            System.out.print(lis.get(i)+" ");
        }
        
        return maxi;
    }
}
//TC: O(n^2)
//SC: O(n)

