package dfs.easy.maximum_depth_of_binary_tree;

import common.node.TreeNode;

public class Solution {

    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        int curDepth=1;
        return preOrder(root,curDepth);
    }

    private int preOrder(TreeNode root,int curDepth) {
        int leftDepth=0;
        int rightDepth=0;
        if(root.left!=null){
            leftDepth=preOrder(root.left,curDepth+1);
        }else
            leftDepth=curDepth;
        if(root.right!=null)
            rightDepth=preOrder(root.right,curDepth+1);
        else
            rightDepth=curDepth;
        return Math.max(leftDepth,rightDepth);
    }

    public static void main(String[] args) {
        TreeNode treeNode=new TreeNode(4);
        treeNode.left=new TreeNode(5);
        treeNode.right=new TreeNode(3);
        System.out.println(new Solution().maxDepth(treeNode));

    }
}
