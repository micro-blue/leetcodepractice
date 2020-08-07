package easy.intersection_of_two_linked_lists;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 用hash保证其唯一性，用一个hash表保存一个链表的所有节点，再遍历另一个链表进行查重
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null)
            return null;
        HashSet hashSet=new HashSet();
        ListNode node=headA;
        while(node!=null){
            hashSet.add(node);
            node=node.next;
        }
        node=headB;
        while(node!=null){
            if (hashSet.contains(node)){
                return node;
            }
            node=node.next;
        }
        return null;
    }
}
