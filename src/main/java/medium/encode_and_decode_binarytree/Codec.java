package medium.encode_and_decode_binarytree;

import java.util.ArrayDeque;

public class Codec {
    public StringBuilder postorder(TreeNode root, StringBuilder sb) {
        if (root == null) return sb;
        //使用后续遍历 子节点保存在前
        //其实不一定要用后续 总之你以什么形式存进去，你就用“逆形式”取出来 就行了
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.val);
        sb.append(' ');
        return sb;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = postorder(root, new StringBuilder());
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
        if (nums.isEmpty()) return null;
        int val = nums.getLast();
        if (val < lower || val > upper) return null;
        //由于使用后续遍历， 根节点在后，所以从最后开始，逐个往前“复原”
        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper(val, upper, nums);
        root.left = helper(lower, val, nums);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        for (String s : data.split("\\s+"))
            nums.add(Integer.valueOf(s));
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(8);
        root.left=new TreeNode(4);
        root.right=new TreeNode(12);


        Codec codec = new Codec();
        String str=codec.serialize(root);
         codec.deserialize(str);
    }
}

/*
作者：LeetCode
        链接：https://leetcode-cn.com/problems/serialize-and-deserialize-bst/solution/xu-lie-hua-he-fan-xu-lie-hua-er-cha-sou-suo-shu-2/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
