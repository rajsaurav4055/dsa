package Heaps;
import java.util.*;

public class LastStoneWeight {
    public int lastStoneWeight(int[]  stones){
        // Insert all the stones into a Max-Heap.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone: stones){
            maxHeap.add(stone);
        }

        // While there is more than one stone left, we need to remove the two largest
        // and smash them together. If there is a resulting stone, we need to put into
        // the heap.
        while(maxHeap.size() > 1){
            int y= maxHeap.poll();
            int x = maxHeap.poll();
            if(x!=y){
                int diff = y - x;
                maxHeap.add(diff);
            }
        }

        // Check whether or not there is a stone left to return.
        if(maxHeap.size()==1){
            return maxHeap.peek();
        }else{
            return 0;
        }
    }
}
//TC: creating maxHeap out of the array (O(N)) removing take O(nlogn) so total -----> O(nlogn)
//SC: Priority queue of N so O(N)
