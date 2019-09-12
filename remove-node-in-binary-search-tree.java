// 87. Remove Node in Binary Search Tree
// 中文English
// Given a root of Binary Search Tree with unique value for each node. Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.
//
// Example
// Example 1
//
// Input:
// Tree = {5,3,6,2,4}
// k = 3
// Output: {5,2,6,#,4} or {5,4,6,2}
// Explanation:
// Given binary search tree:
//     5
//    / \
//   3   6
//  / \
// 2   4
// Remove 3, you can either return:
//     5
//    / \
//   2   6
//    \
//     4
// or
//     5
//    / \
//   4   6
//  /
// 2
//
// Example 2
//
// Input:
// Tree = {5,3,6,2,4}
// k = 4
// Output: {5,3,6,2}
// Explanation:
// Given binary search tree:
//     5
//    / \
//   3   6
//  / \
// 2   4
// Remove 4, you should return:
//     5
//    / \
//   3   6
//  /
// 2


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
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here

        if (root == null) {
            return null;
        }
        // because we should remove and change removed node's parent and children nodes, so when we search this node, we should record its parents too
        // thus, we can create a dummy node as we do in LinkedList
        TreeNode dummy = new TreeNode(0); // create a dummy node as parent of root
        dummy.left = root;
        TreeNode parent = search(dummy, root, value); // found the value node's parent
        // we should determine the node to be removed is in left or right side
        if (parent.left != null && parent.left.val == value) {
            // left
            remove(parent, parent.left);
        } else if (parent.right != null && parent.right.val == value) {
            // right
            remove(parent, parent.right);
        } else {
            // did not find - nothing change
            // return root;
            // cannot return root, because root may be removed, same following; and that's why we create and use dummy
            return dummy.left;
        }
        // return root;
        return dummy.left;
    }
    private TreeNode search(TreeNode parent, TreeNode root, int value) {
        if (root == null) {
            // leaf node
            return parent;
        }
        if (root.val == value) {
            return parent;
        } else if (value > root.val) {
            return search(root, root.right, value);
        } else {
            return search(root, root.left, value);
        }
    }
    private void remove(TreeNode parent, TreeNode node) {
        // 1: node is a leaf; 2: node has only 1 child; 3: node has 2 children
        // case 1
        if (node.left == null && node.right == null) {
            // determine the node is left or right child of parent
            if (node == parent.left) {
                parent.left = null;
            }
            if (node == parent.right) {
                parent.right = null;
            }
        }
        // case 2 - 1: only left child, connect directly parent to left child of node
        if (node.left != null && node.right == null) {
            if (node == parent.left) {
                parent.left = node.left;
            }
            if (node == parent.right) {
                parent.right = node.left;
            }
        }
        // case 2 - 2: only right child, connect directly parent to right child of node
        if (node.left == null && node.right != null) {
            if (node == parent.left) {
                parent.left = node.right;
            }
            if (node == parent.right) {
                parent.right = node.right;
            }
        }
        // case 3: node has 2 children
        if (node.left != null && node.right != null) {
            if (node == parent.left) {
                parent.left = node.left;
            }
            if (node == parent.right) {
                parent.right = node.left;
            }
            // // method 1: node.left -> node.right, and connect node.left's right children to the bottom of node.right's left children
            // // find minimum node/bottom in left children of node's right child
            // TreeNode min = node.right;
            // while (min.left != null) {
            //     min = min.left;
            // }
            // TreeNode child = node.left.right;
            // // connect node.left to node.right
            // node.left.right = node.right;
            // // connect node.left's right subtree to the bottom of node.right's left subtree
            // min.left = child;

            // method 2: put node.right subtree to the bottom of node.left's right subtree
            TreeNode max = node.left;
            while (max.right != null) {
                max = max.right;
            }
            // connect max to the node.right;
            max.right = node.right;
        }
    }
}
