package tree.easy;

import common.node.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root==null)
            return true;
        return compareTwo(root.left,root.right);
    }
    private boolean compareTwo(TreeNode left, TreeNode right) {
        if (left==null||right==null)
            return right==left;
        if (left.val!=right.val)
            return false;
        return compareTwo(left.left,right.right)&&compareTwo(left.right,right.left);
    }
}
