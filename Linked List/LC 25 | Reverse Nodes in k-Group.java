/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

 //Neetcode solution
 class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy=new ListNode(0,head);
        ListNode prev=dummy;
        ListNode curr=head;
        ListNode temp=null;
        int count=k;

        while(curr!=null){
            if(count > 1){
               temp= prev.next;
               prev.next= curr.next;
               curr.next= curr.next.next;
               prev.next.next=temp;

               count--;
            }else{
                prev=curr;
                curr=curr.next;
                ListNode end=curr;
                for(int i=0; i<k;i++){
                    if(end==null){
                        return dummy.next;
                    }
                    end=end.next;
                }
                count=k;
            }
        }
        return dummy.next;
    }
}

//TC: O(n)
//SC: O(1)