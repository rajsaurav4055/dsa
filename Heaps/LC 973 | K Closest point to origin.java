
//using maxHeaps 
//This approach is a sightly optimized approach here we can use a max heap and maintain its size as k.
//So when we do the removal the time complexity will reduce from logn to logk
//Max heap because we will remove the top elements (the one which are greater)
//Overall Time complexity O(NlogK)
class KClosestPointToOrigin{
    public int[][] kClosest(int[][] points, int k) {

        //comparision is done in reverse order to create a maxheap
        //also we are calculating the distance her 
        PriorityQueue<int[]> maxHeap= new PriorityQueue<>((a,b)-> Integer.compare((b[0]*b[0] + b[1]*b[1]) ,(a[0]*a[0] + a[1]*a[1]) ));
        for(int[] point: points){
            maxHeap.add(point);
            //remove when size increases more than k
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }

        int[][] result= new int[k][2];
        for(int i=0; i<k; i++){
            int[] curr= maxHeap.poll();
            result[i][0]=curr[0];
            result[i][1]=curr[1];
        }
        return result;


    }

}