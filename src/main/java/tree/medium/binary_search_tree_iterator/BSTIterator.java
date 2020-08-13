package tree.medium.binary_search_tree_iterator;

import common.node.TreeNode;

import java.util.Stack;
//173
//迭代器模式遍历 有序二叉树
//主要要注意的点是：要求空间复杂度较低，即你不能先自己内部中序遍历一遍，然后放到有序链表中，然后
// 直接调用有序链表，因为如果这样做，这个有序链表会占用O(n)的空间复杂度，如果数据量较大，将大量浪费内存
//同时也不符合迭代器模式的思想，因为你如果这样做，你直接新建一个方法返回这个有序链表给用户就好了，哪需要什么迭代器呢
// 题目将空间复杂度限制在O(h) ，事实上，这是基于需要被迭代的数据结构而言的，即：应该尽可能地小，O(1)是最好，在没有指向父节点的指针的树结构中，O(h)已经是最小的了，所以做了妥协
class BSTIterator {
    private Stack<TreeNode>stack;
    public BSTIterator(TreeNode root) {
        stack=new Stack<>();
        if(root==null)
            return;
        updateStack(root);
    }

    private void updateStack(TreeNode root) {
        stack.push(root);
        if(root.left!=null){
            updateStack(root.left);
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode treeNode=stack.pop();
        if(treeNode.right!=null)
            updateStack(treeNode.right);
        return treeNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size()>0;
    }
}
