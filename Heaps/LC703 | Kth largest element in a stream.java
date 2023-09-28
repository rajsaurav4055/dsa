
package Heaps;
import java.util.PriorityQueue;
import java.util.*;


class KthLargest{
    private static int k;
    private PriorityQueue<Integer> minHeap; //way to declare priority queue

    //constructor
    public KthLargest(int k, int[] nums){
        this.k = k;
        minHeap = new PriorityQueue<>();
        for(int num: nums){
            minHeap.add(num);
        }

        while(minHeap.size() > k){
            minHeap.poll();
        }
    }

    public int add(int val){
        minHeap.add(val);
        if(minHeap.size() > k){
            minHeap.poll();
        }
        return minHeap.peek();
    }
}

//Given N as the length of nums and M as the number of calls to add(),

//Time complexity: O(N⋅log⁡(N)+M⋅log⁡(k))O(N \cdot \log(N) + M \cdot \log(k))O(N⋅log(N)+M⋅log(k))

//The time complexity is split into two parts. First, the constructor needs to turn nums into a heap of size k. In Python, heapq.heapify() can turn nums into a heap in O(N)O(N)O(N) time. Then, we need to remove from the heap until there are only k elements in it, which means removing N - k elements. Since k can be, say 1, in terms of big OOO this is N operations, with each operation costing log⁡(N)\log(N)log(N). Therefore, the constructor costs O(N+N⋅log⁡(N))=O(N⋅log⁡(N))O(N + N \cdot \log(N)) = O(N \cdot \log(N))O(N+N⋅log(N))=O(N⋅log(N)).

//Next, every call to add() involves adding an element to heap and potentially removing an element from heap. Since our heap is of size k, every call to add() at worst costs O(2∗log⁡(k))=O(log⁡(k))O(2 * \log(k)) = O(\log(k))O(2∗log(k))=O(log(k)). That means M calls to add() costs O(M⋅log⁡(k))O(M \cdot \log(k))O(M⋅log(k)).

//Space complexity: O(N)O(N)O(N)

//The only extra space we use is the heap. While during add() calls we limit the size of the heap to k, in the constructor we start by converting nums into a heap, which means the heap will initially be of size N.
