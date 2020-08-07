package tree.hard.Codec;

import common.node.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
//使用前序遍历的 二叉树编解码 ，

//层序遍历的解法 https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/shou-hui-tu-jie-gei-chu-dfshe-bfsliang-chong-jie-f/
//可以看到 基本都能做到 序列化与反序列化都是形式一致，只是过程相反而已 ，即都是先序遍历的形式 或者都是 层序遍历的形式
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        serialize(root,sb);
        return sb.toString();
    }
    private StringBuilder serialize(TreeNode root,StringBuilder s){
        if(root==null)
            return s.append("NULL,");
        s.append(root.val);
        s.append(",");
        serialize(root.left,s);
        serialize(root.right,s);
        return s;
    }

    public TreeNode deserialize(String data) {
        String [] deData=data.split(","); //利用split()函数将data分割成只包含节点值和NULL的数组
        Queue<String> q=new LinkedList<>();
        Collections.addAll(q,deData); //将deData的值复制到队列q中
        return deserialize(q);
    }
    private TreeNode deserialize(Queue<String> q){
        String tem=q.remove(); //删除队首，如果有值就创建节点，否则就返回null
        if(tem.equals("NULL")){
            return null;
        }
        TreeNode node=new TreeNode(Integer.parseInt(tem));
        node.left=deserialize(q);
        node.right=deserialize(q);
        return node;
    }
}

