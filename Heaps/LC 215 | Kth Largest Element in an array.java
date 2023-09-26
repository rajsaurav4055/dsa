
//<--------------- GOOGLE QUESTION -----------------------------> 



//<------------------------ MINHEAP ------------------------------>

//using minHeap
//T.C. : O(Nlog k)
//Space: O(k)
// class Solution {
//     public int findKthLargest(int[] nums, int k) {
//         PriorityQueue<Integer> minHeap= new PriorityQueue<>();
//         for(int num : nums){
//             minHeap.add(num);
//             if(minHeap.size() > k){
//                 minHeap.remove();
//             }
//         }

//         return minHeap.peek();

//     }
// }

//<--------------------   QUICK SELECT.   -------------------------------->
//Using Quickselect

// TC: O(N) in average case and O(N^2) in worst case
// class Solution {

//     //return the pivot location after partioning the array
//     private int quickSelect(List<Integer> nums, int k){

//         //Good to know how random number is generated.
//         int pivotIndex = new Random().nextInt(nums.size()); 
//         //this is how you access elements in a list
//         int pivot = nums.get(pivotIndex);

//         List<Integer> left= new ArrayList<>();
//         List<Integer> mid= new ArrayList<>();
//         List<Integer> right= new ArrayList<>();

//         //add elements to the above declared lists and partition
//         for(int num : nums){
//             if(num > pivot){
//                 left.add(num);
//             }else if(num < pivot){
//                 right.add(num);
//             }else{
//                 mid.add(num);
//             }
//         }

//         if(k <= left.size()){
//             return quickSelect(left,k);
//         }

//         if(k > left.size() + mid.size()){
//             return quickSelect(right, k - left.size()- mid.size()); //very important to note what we did here. This is done because the left and mid list already had the larger elements in which the kth largest was not present so we have to delete those elements

//         }

//         return pivot;



//     }

//     public int findKthLargest(int[] nums, int k){
//         List<Integer> list= new ArrayList<>();
//         //converting arrays to lists because lists are dynamic like vectors in C++
//         for(int num : nums){
//             list.add(num); //this is how you add elements to list 
//         }

//         return quickSelect(list, k);
//     }
// }

//<---------------------  COUNT SORT   ----------------------------------------->
//Using count sort - one of the fastest way to sort array
//Only constraint is that we cannot use count sort for negative numbers
//But in this solution the standard count sort is slightly modified to include negaive number
//So using this code we can write a generic count sort algo
public class KthLargestArray {
    public int findKthLargest(int[] nums, int k){
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        //find the minimum (count sort modified to include negative integers) and maximum value in the array
        for(int num : nums){
            minValue = Math.min(minValue, num);
            maxValue = Math.max(maxValue, num);
        }

        //Instead of maxValue + 1 (in standard count sort) we modify to maxValue - minValue + 1 to include the negative values
        int[] count = new int[maxValue - minValue + 1];
        for(int num: nums){
            count[num - minValue]++;
        }
         
        //Instead of creating a sorted array we are just trying to find the kth largest from last by subtracting the counts from behind (reduces space by O(n))
        int remain = k;
        for(int j= count.length - 1; j >= 0; j --){
            remain = remain - count[j];
            if(remain <= 0){
                return j + minValue;
            }
        }

        return -1;

    }

    // Given n as length of nums and m as maxValue - minValue. 
    //TC: O(n+m) //which is a lot faster than the rest
    //SC: O(m)

    
}

//Conclusion is Count sort is the most optimal
//Very good question as it teaches you normal sort, minHeap sorting , Quick select and Count sort ---> Please go through all of the solutions when you see this comment
