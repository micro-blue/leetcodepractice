package easy.reverselist;

import common.node.ListNode;

public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if(head==null)
            return null;
        if(head.next==null)
            return head;
        ListNode nextHead=head.next;
        ListNode curHead=head;
        curHead.next=null;//第一个节点在循环中没有被修改next，所以在外部先置空
        while(nextHead!=null){
            ListNode tmp=nextHead.next;
            nextHead.next=curHead;
            curHead=nextHead;
            nextHead=tmp;
        }
        //head=null;
        return curHead;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        System.out.println(new ReverseList().reverseList(head));
    }
}
