

// Working of heapsort
// Basically, there are two phases involved in the sorting of elements using heap sort algorithm they are as follows:

// 1.  First, start with the construction of a heap by adjusting the array elements.
// 2. Once the heap is created repeatedly eliminate the root element of the heap by shifting it to the end of the array and then store the heap structure with remaining elements.


public class HeapSort{

    //Function to sort the array using heap sort
    public void heap_sort(int A[]){
        int n=A.length;

        //heapify the given array - we are building it to max heap
        for(int i= n/2 -1; i>=0; i++){
            heapify(A, i, n);
        }

        //Question: Why did we use i=n/2 -1 in the above loop
        //Answer in the you tube explanation of this
        // When building a heap, you start from the middle of the array and move towards the beginning. 
        //This is because the elements from index n/2 to n-1 are all leaves in the heap. 
        //Leaves are already valid heaps by themselves since they don't have any children.
        //By starting from the middle and moving towards the beginning, you can efficiently build the heap in a bottom-up manner. 
        //Starting from the leaves allows you to skip most of the nodes, as you only need to ensure the heap property for non-leaf nodes (parents) and their subtrees.

        //Now to sort the array we will keep eliminating the root node
        for(int i=n-1; i>=0; i--){

            //move current root to the end node (Standard process of sorting)
            int temp = A[i];
            A[i]=A[0];
            A[0] = temp;

            //call max heap on the reduced heap
            heapify(A, 0, i);
        }




    }

    //Heapify function to create a heap out of the given array
    //n= size of the heap // i is the root node

    //We use this approach instead of creating a heap because this approach just takes O(n) time
    void heapify(int A[], int i, int n){
        int largest = i; //intitalize largest as heap node
        int left_child = 2*i + 1;
        int right_child = 2* i + 2;

        //If left child is larger than root
        if(left_child < n && A[left_child] > A[largest])
            largest = left_child;
        
        //If right child is larger than largest so far    
        if(right_child < n && A[right_child] > A[largest])
            largest = right_child;

        //If largest is not the root    
        if(largest != i){
            int swap = A[largest];
            A[largest] = A[i];
            A[i] = swap;

            heapify(A, largest, n);
        }
    }
    

    static void printArray(int A[]){
        for(int i=0; i<A.length; i++){
            System.out.print(A[i]+"");
            
        }
        System.out.println();

    }
    public static void main(String args[]){
        int A[]= {22, 21, 3, 25, 26, 7};
        int n= A.length;
        
        HeapSort object = new HeapSort();
        object.heap_sort(A);

        System.out.println("Sorted Array is ");
        printArray(A);
    }

}

//Time complexity:
// Heapify is O(n)
// Heap sort by deleting the root is O(nlog n)
//So overall is O(nlog n)