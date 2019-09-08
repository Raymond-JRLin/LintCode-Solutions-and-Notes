// 85. Insert Node in a Binary Search Tree
// 中文English
// Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.
//
// Example
// Example 1:
// 	Input:  tree = {}, node = 1
// 	Output:  1
//
// 	Explanation:
// 	Insert node 1 into the empty tree, so there is only one node on the tree.
//
// Example 2:
// 	Input: tree = {2,1,4,3}, node = 6
// 	Output: {2,1,4,3,6}
//
// 	Explanation:
// 	Like this:
//
//
//
// 	  2             2
// 	 / \           / \
// 	1   4   -->   1   4
// 	   /             / \
// 	  3             3   6
//
// Challenge
// Can you do it without recursion?
//
// Notice
// You can assume there is no duplicate values in this tree + node.
//


/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if (node == null) {
            return root;
        }

        // method 1: recursion
        // 还是要掌握好递归的用法
        // 一路向下查找， 比当前 root.val 大， 就去右子树， 如果比当前 root.val 小， 那就去左子树， 一直查找到 Leaf node， 再插入
        // 只是 BST 不用考虑插在中间和旋转
        // if (root == null) {
        //     // 递归的出口
        //     return node;
        // }
        // if (root.val < node.val) {
        //     // 向右
        //     root.right = insertNode(root.right, node);
        // } else {
        //     // 向左
        //     root.left = insertNode(root.left, node);
        // }
        // return root;

        // method 2: iteration (non-recursion)
        // 那肯定要不断的循环来做， 关键是什么时候退出循环以及内部转换, 插入 node 的条件是什么
        if (root == null) {
            return node;
        }
        TreeNode curr = root; // because we use iteration, and we would change "root" as looping, so we should use another reference of root
        while (curr != null) {
            if (curr.val < node.val) {
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    curr.right = node; // insert node to right child
                    break;
                }
            } else {
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    curr.left = node; // insert node to left child
                    break;
                }
            }
        }
        return root;
    }
}
