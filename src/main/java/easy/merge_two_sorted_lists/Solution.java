package easy.merge_two_sorted_lists;

import java.util.*;

public class Solution {
    //解法2 时间复杂度O(m+n)，快于解法1
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }else if (l2 == null) {
                return l1;
            }
            ListNode dummyNode=new ListNode(-1);
            ListNode tmp=dummyNode;
            while(l1!=null||l2!=null){
                if(l1==null){
                    tmp.next=l2;
                    break;
                }else if(l2==null){
                    tmp.next=l1;
                    break;
                }
                if(l1.val<l2.val){
                    tmp.next=new ListNode(l1.val);
                    l1=l1.next;
                }else if(l1.val>l2.val) {
                    tmp.next = new ListNode(l2.val);
                    l2 = l2.next;
                }else{
                    tmp.next = new ListNode(l1.val);
                    tmp.next.next = new ListNode(l2.val);
                    l1=l1.next;
                    l2 = l2.next;
                    tmp=tmp.next.next;
                    continue;
                }
                tmp=tmp.next;
            }
            return dummyNode.next;
    }

    //方法1： 使用TreeMap，但时间复杂度是O((m+n)log(m+n))
    /*public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //treemap的key是int值，value是该int在两个链表的总数
        TreeMap<Integer,Integer>map=new TreeMap<>();
        addList(l1,map);
        addList(l2,map);
        ListNode dummyNode=new ListNode(-1);
        ListNode tmp=dummyNode;
        for (Map.Entry<Integer,Integer>e:map.entrySet()) {
            for(int i=0;i<e.getValue();i++){
                tmp.next=new ListNode(e.getKey());
                tmp=tmp.next;
            }
        }
        return dummyNode.next;
    }
    public void addList(ListNode node, Map<Integer,Integer>map){
        while(node!=null){
            if(map.get(node.val)!=null){
                map.put(node.val,map.get(node.val)+1);
            }else {
                map.put(node.val,1);
            }
            node=node.next;
        }
    }*/

    public static void main(String[] args) {
        ListNode listNode1=new ListNode(1);;
        listNode1.next=new ListNode(2);
        listNode1.next.next=new ListNode(4);
        ListNode listNode2=new ListNode(1);;
        listNode2.next=new ListNode(3);
        listNode2.next.next=new ListNode(4);



        new Solution().mergeTwoLists(listNode1,listNode2);
    }
}

