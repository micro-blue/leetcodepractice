package medium.remove_nth_node_from_end_of_list;

import common.node.ListNode;
//维护两个必要的指针即可
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next==null)
            return null;
        ListNode first=head;
        ListNode second=head;
        for (int i = 0; i < n; i++) {
            second=second.next;
        }
        if(second==null){
            head=head.next;
            return head;
        }
        while(second.next!=null){
            first=first.next;
            second=second.next;
        }
        first.next=first.next.next;
        return head;
    }
}
