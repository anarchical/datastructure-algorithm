package _0105_Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YeYaqiao
 * 105. 从前序与中序遍历序列构造二叉树
 */
public class Solution {
    //递归
    //前序遍历用来定位根节点
    //中序遍历用来定位左右子树
    int[] preorder;
    Map<Integer, Integer> inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        this.preorder = preorder;
        this.inorder = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            this.inorder.put(inorder[i], i);
        }
        return recur(0, 0, length - 1);
    }

    private TreeNode recur(int rootIndex, int leftIndex, int rightIndex) {
        //若左边界=右边界，则表示当前节点为叶子节点
        if (leftIndex > rightIndex)
            return null;
        TreeNode node = new TreeNode(preorder[rootIndex]);
        int partition = inorder.get(node.val);
        //对于前序遍历，根节点位置+1 就是左子树的根节点位置
        //对于中序遍历，左子树的左边界leftIndex，右边界则是根节点的位置-1
        node.left = recur(rootIndex + 1, leftIndex, partition - 1);
        //对于前序遍历，根节点位置+左子树的长度就是右子树的根节点位置
        //对于中序遍历，右子树的左边是根节点位置+1，右边界始终是树的大小
        node.right = recur(rootIndex + partition - leftIndex + 1, partition + 1, rightIndex);
        return node;
    }

    //迭代
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        int length = preorder.length;
//        TreeNode root = new TreeNode(preorder[0]);
//        Deque<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//        int inorderIndex = 0;
//        for (int i = 1; i < length; i++) {
//            int nodeValue = preorder[i];
//            TreeNode node = stack.peek();
//            if (node != null && node.val != inorder[inorderIndex]) {
//                node.left = new TreeNode(nodeValue);
//                stack.push(node.left);
//            } else {
//                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
//                    node = stack.pop();
//                    inorderIndex++;
//                }
//                node.right = new TreeNode(nodeValue);
//                stack.push(node.right);
//            }
//        }
//        return root;
//    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

