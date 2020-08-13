package dfs.easy.maximum_depth_of_binary_tree;

import common.node.TreeNode;

public class Solution2 {
    public int maxDepth(TreeNode root) {
        return root==null?0:1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
    public static void main(String[] args) {
        TreeNode treeNode=new TreeNode(4);
        treeNode.left=new TreeNode(5);
        treeNode.right=new TreeNode(3);
        System.out.println(new Solution().maxDepth(treeNode));

    }
}
