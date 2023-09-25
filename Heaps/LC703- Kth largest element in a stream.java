
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