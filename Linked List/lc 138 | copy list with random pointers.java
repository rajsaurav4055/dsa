/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

//We will do two passes.
class Solution {
    public Node copyRandomList(Node head) {
        Node cur=head;
        HashMap<Node,Node> map=new HashMap<>();
        //first pass
        while(cur != null){
            map.put(cur,new Node(cur.val));
            cur=cur.next;
        }

        cur=head;
        //second pass
        while(cur!=null){
            map.get(cur).next=map.get(cur.next);
            map.get(cur).random=map.get(cur.random);
            cur=cur.next;
        }
        return map.get(head);
    }
}
//sc: O(n)
//tc: O(n)
