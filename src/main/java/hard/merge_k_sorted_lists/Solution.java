package hard.merge_k_sorted_lists;

import common.node.ListNode;

import java.util.*;

/**
 * 这道题的重点是： 充分利用每个链表的是“已排序”的特点，在这个基础上，所有链表的头结点中最小的那个就一定是所有链表所有节点中最小的节点。我们用优先队列记录所有队列的头节点，并总是弹出最小值即可
 */
public class Solution {
    //方法2（优化后）
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){
            return null;
        }
        Queue<ListNode>queue=new PriorityQueue<>((ListNode e1,ListNode e2)-> {return e1.val-e2.val;});
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }
        //两个指针，一个静止的指针，用于返回结果，另一个指针用于算法中的动态处理
        ListNode dummyNode=new ListNode(-1);
        ListNode tmp=dummyNode;
        while(queue.peek()!=null){
            ListNode entry=queue.poll();
            tmp.next=entry;
            tmp=tmp.next;
            if(entry.next!=null){
                queue.add(entry.next);
            }
        }
        return dummyNode.next;
    }
/*  方法2（优化前） 第一次完成的版本，对比上面简化后的版本，主要有三个问题：
        ① 没必要使用Entry，ListNode本身的结构足够了,也不需要 new 新的node节点到queue中,并且，它其实是“复用”已有的节点，此时如果你释放原先的链表（=null），那么我们构建的dummyNode.Next引用并不会消失，足以满足题目要求
        ② comparator可以简化，不用做三个if分支
        ③ 当参数是数组时，除了数组本身判空，数组遍历时，个别元素也可能是空，也要进行判空
    class Entry{
        int value;
        ListNode listNode;

        public Entry(int value, ListNode listNode) {
            this.value = value;
            this.listNode = listNode;
        }
    }
    class EntryComparator implements Comparator<Solution.Entry>{
        @Override
        public int compare(Entry o1, Entry o2) {
            if(o1.value<o2.value){
                return -1;
            }else if(o1.value>o2.value){
                return 1;
            }
            return 0;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){
            return null;
        }
        Queue<Entry>queue=new PriorityQueue<>(new EntryComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) { //这里要进行必要的判空，因为数组不为空，不代表数组中的个别元素不为空
                continue;
            }
            ListNode p=null;
            queue.add(new Entry(lists[i].val,p=lists[i]));
        }
        ListNode dummyNode=new ListNode(-1);
        ListNode tmp=dummyNode;
        while(queue.peek()!=null){
            Entry entry=queue.poll();
            tmp.next=new ListNode(entry.value);
            tmp=tmp.next;
            if(entry.listNode.next!=null){
                queue.add(new Entry(entry.listNode.next.val,entry.listNode.next));
            }
        }
        return dummyNode.next;
    }*/
    //解法1，类似easy的解法，这个解法的时间复杂度是O((sum ni)log(sum ni)),较差，但可以只修改一行代码就能完成题目
    /*public ListNode mergeKLists(ListNode[] lists) {
        //treemap的key是int值，value是该int在两个链表的总数
        TreeMap<Integer,Integer> map=new TreeMap<>();
        //比起easy，只需要将这里修改为循环，其它完全不用动
        for (int i = 0; i < lists.length; i++) {
            addList(lists[i],map);
        }
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
    public void addList(ListNode node, Map<Integer,Integer>map) {
        while (node != null) {
            if (map.get(node.val) != null) {
                map.put(node.val, map.get(node.val) + 1);
            } else {
                map.put(node.val, 1);
            }
            node = node.next;
        }
    }*/
}
