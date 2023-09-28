
//Using two heaps by dividing the given array into two parts
class MedianFinder {
    private PriorityQueue<Integer> minHeap;//larger elements of the sorted array in this heap
    private PriorityQueue<Integer> maxHeap;//smaller elements of the sorted array in this heap

    public MedianFinder() {
        minHeap= new PriorityQueue<>((a,b) -> a - b);
        maxHeap= new PriorityQueue<>((a,b) -> b - a);
    }
    
    //Two rules to follow when adding a number to the heaps.
    //1. The difference in size between the two heaps should not exceed 1.
    //2. The root of the maxHeap should always be less than or equal to the top of the minHeap
    public void addNum(int num) {
        maxHeap.add(num);

        //if after adding to the maxHeap that contains smaller numbers, we are checking the 2 rules mentioned above.
        if(maxHeap.size() - minHeap.size() > 1 || !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
            minHeap.add(maxHeap.poll());
        }

        //Now we also have to make sure that after adding the number to the minHeap the difference of size between the heaps should not exceed 1.
        if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.add(minHeap.poll());
        }
    }
    
    //Rules to check
    //1. if the size of the heaps are different-> median is the root of the heap having more size
    //2. if size of the heaps are same -> find average of top of both the heaps
    public double findMedian() {
        if(maxHeap.size() > minHeap.size()){
            return (double)maxHeap.peek();
        }else if(minHeap.size() > maxHeap.size()){
            return (double)minHeap.peek();
        }else{
            return (double)(minHeap.peek() + maxHeap.peek())/2;
        }
    }

    //TC: O(5 * log n) + O(1) = O(log n)
    //SC: O(n)
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */