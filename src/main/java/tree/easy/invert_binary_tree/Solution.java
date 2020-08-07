package tree.easy.invert_binary_tree;

import common.node.TreeNode;

public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root==null)
            return null;
        invert(root);
        return root;
    }

    private void invert(TreeNode root) {
        if(root==null)
            return;
        TreeNode temp=root.right;
        root.right=root.left;
        root.left=temp;
        invert(root.left);
        invert(root.right);
    }
}
