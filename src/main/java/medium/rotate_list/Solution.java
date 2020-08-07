package medium.rotate_list;

class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if(head==null){
            return null;
        }
        if (k==0){
            return head;
        }
        int size=1;
        ListNode oldHead=head;
        ListNode oldTail=null;
        ListNode cur=head;
        while(cur.next!=null){
            oldTail=cur.next;
            cur=cur.next;
            size++;
        }
        int s=k%size;
        if(s==0)return head;
        int delta=size-s;
        ListNode newTail=head;
        for (int i = 1; i < delta; i++) {
            newTail=newTail.next;
        }
        ListNode newHead=newTail.next;
        oldTail.next=oldHead;
        newTail.next=null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(1);
        /*listNode.next=new ListNode(2);
        listNode.next.next=new ListNode(3);
        listNode.next.next.next=new ListNode(4);
        listNode.next.next.next.next=new ListNode(5);*/

        new Solution().rotateRight(listNode,1);

    }
}
