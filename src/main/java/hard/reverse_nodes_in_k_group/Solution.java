package hard.reverse_nodes_in_k_group;

import common.node.ListNode;

import java.util.Queue;
import java.util.Stack;

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead;
        if((newHead=isLengthEnough(head,k))==null){
            return head;
        }
        Stack<ListNode>stack=new Stack();
        ListNode curGroupHead=head;
        ListNode dummyHead=new ListNode(-1);dummyHead.next=curGroupHead;
        ListNode prevNode=dummyHead;
        while(curGroupHead!=null){
            ListNode tmp=curGroupHead;
            int i;
            for (i = 0; i <k ; i++) {
                if(tmp!=null){
                    stack.push(tmp);
                    tmp=tmp.next;
                }else{
                    break;//防止在退出循环前进行i++，for循环更好的写法是 把第一个分号前和第二个分号后的表达式拿出来，for中只留下一个boolean，类似while
                }
            }
            if(i<=k-1){//如果剩余节点不够k个
                prevNode.next=curGroupHead;//最后一段保持不变
                break;
            }

            ListNode tmp2=prevNode;
            int size=stack.size();
            for (int j = 0; j <size-1 ; j++) {
                tmp2.next=stack.pop();
                tmp2=tmp2.next;
            }
            tmp2.next=stack.pop();
            prevNode=tmp2.next;
            prevNode.next=null;//断开引用，防止循环引用，因为如果k刚好整除于链表的长度，curGroupHead为null，此时就退出了循环，这样的话，此时最后一个元素就指向前一个元素，前一个元素又指向最后以一个元素，出现循环引用
            curGroupHead=tmp;
        }
        return newHead;
    }

    private ListNode isLengthEnough(ListNode head, int k) {
        ListNode tmp=head;
        for (int i=0;i <k ; i++) {
            if (i==k-1)
                return tmp;
            tmp=tmp.next;
        }
        
        return null;
    }

    public static void main(String[] args) {
        ListNode listNode1=new ListNode(1);
        listNode1.next=new ListNode(2);
        listNode1.next.next=new ListNode(3);
        listNode1.next.next.next=new ListNode(4);
        listNode1.next.next.next.next=new ListNode(5);

        new Solution().reverseKGroup(listNode1,5);

    }
}
