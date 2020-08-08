package tree.medium.DFS.binary_tree_level_order_traversal;

import common.node.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer>list=new LinkedList<>();
        inOrder(root,list);
        return list;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if(root==null)
            return;
        inOrder(root.left,list);
        list.add(root.val);
        inOrder(root.right,list);
    }
}
