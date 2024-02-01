IMPORTANT PROBLEM- AS IT SHOWS IMPLEMENTATION OF DOUBLY LINKED LIST



//Doubly linked List
class ListNode{
    int key;
    int value;
    ListNode next;
    ListNode prev;

    //constructor
    public ListNode(int key, int val){
        this.key=key;
        this.value=val;
    }
}


class LRUCache {
    Map<Integer, ListNode> cache;
    int capacity;
    ListNode head;
    ListNode tail;

    //constructor
    public LRUCache(int capacity) {
        this.capacity=capacity;
        cache=new HashMap<>();
        head=new ListNode(-1,-1);
        tail=new ListNode(-1,-1);
        head.next=tail;
        tail.prev=head;
    }
    

    public int get(int key) {
        //agar key present ni hai toh
        if(!cache.containsKey(key))return -1;
        ListNode node=cache.get(key);
        remove(node);
        add(node);//adding it to the end as it is the most recently used
        return node.value;
    }
    
    public void put(int key, int value) {
        //agar capacity if already full
        if(cache.containsKey(key)){
            ListNode oldNode= cache.get(key);
            remove(oldNode);
        }

        ListNode newNode=new ListNode(key,value);
        cache.put(key,newNode);
        add(newNode);

        if(cache.size() > capacity){
            ListNode nodeToDelete= head.next;
            remove(nodeToDelete);
            cache.remove(nodeToDelete.key);
        }
    }

    public void remove(ListNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    //add the node to the end of the list always as it is most recently used
    public void add(ListNode node){
        ListNode currLastNode=tail.prev;
        currLastNode.next=node;
        tail.prev=node;
        node.prev=currLastNode;
        node.next=tail;
    }
}

//get and put operation in O(1)
//we used both a hashmap and a doubly linked list
//get and put both operation in O(1)

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
