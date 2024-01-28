
//Using Deque solution
//TC: O(n)
//It is not as hard as it looks
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
    // Create an array to store the result
    int[] ans = new int[nums.length - k + 1];
    
    // Initialize an index variable for the result array
    int j = 0;

    // Create a deque to store indices of elements in the current window
    Deque<Integer> q = new LinkedList<>();

    // Iterate through the input array
    for (int i = 0; i < nums.length; i++) {
        // Remove indices that are outside the current window from the front of the deque
        if (!q.isEmpty() && q.peekFirst() < i - k + 1) {
            q.pollFirst();
        }

        // Remove indices of smaller elements from the back of the deque
        while (!q.isEmpty() && nums[i] > nums[q.peekLast()]) {
            q.pollLast();
        }

        // Add the current index to the deque
        //We push indices and not the value because playing with indices helps in if conditions for calculating the window size in the belwo if as well as the above while condition.
        q.offer(i);

        // If the window size is reached, store the maximum element for the current window
        if (i >= k - 1) {
            ans[j++] = nums[q.peekFirst()];
        }
    }

    // Return the result array
    return ans;
}

}