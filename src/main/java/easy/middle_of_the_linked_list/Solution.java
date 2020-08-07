package easy.middle_of_the_linked_list;

import common.node.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public ListNode middleNode(ListNode head) {
        List<ListNode>list=new ArrayList<>();
        ListNode cur=head;
        while(cur!=null){
            list.add(cur);
            cur=cur.next;
        }
        return list.get(list.size()/2);
    }
}
