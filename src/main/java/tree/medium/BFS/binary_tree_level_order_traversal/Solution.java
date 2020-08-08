package tree.medium.BFS.binary_tree_level_order_traversal;

import common.node.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>>outerList=new LinkedList<>();
        List<Integer> curList;
        if (root==null)
            return outerList;
        Queue <TreeNode>queue=new ArrayDeque<>();
        queue.add(root);
        while (queue.size()>0){
            int temp=queue.size();//此时读到的size，就是每一层的节点数，以此作为循环次数，可以分清 每一层有哪些节点
            curList=new LinkedList<>();
            for (int i = 0; i < temp; i++) {  //当该循环执行完，就相当于把当前一层的节点遍历完，同时也维护了下一层的节点，如果不使用这个循环， 就无法知道每个节点来自那一层， 一般的层序遍历可以不需要知道层数，但该题有要求层数，
                TreeNode node=queue.poll();
                curList.add(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            outerList.add(curList);
        }
        return outerList;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        /*root.left=new TreeNode(9);
        root.right=new TreeNode(20);
        root.right.left=new TreeNode(15);
        root.right.right=new TreeNode(7);*/
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(3);
        root.left.left.left=new TreeNode(4);
        root.left.left.left.left=new TreeNode(5);

        new Solution().levelOrder(root);
    }
}
