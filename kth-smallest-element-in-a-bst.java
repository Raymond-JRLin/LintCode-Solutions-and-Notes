// 902. Kth Smallest Element in a BST
// Description
// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
//
// Have you met this question in a real interview?
// Example
// Given root = {1,#,2}, k = 2, return 2.
//
// Challenge
// What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?


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
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        // write your code here

        // return method1(root, k);

        // return method2(root, k);

        // return method3(root, k);

        return method4(root, k);
    }

    private int method4(TreeNode root, int k) {
        // challenge: 如果要多次查询， 那么就采取 method1 中的方法， 但是把每次求得的左子树的个数用 map 记录下来， 这样每次查询的时候就会更快一些。 有点类似于 quick select 的思想， 加上 map 存
        Map<TreeNode, Integer> map = new HashMap<>(); // <node, # children in left subtree>
        countNodes2(root, map);
        return quickSelect(root, k, map);
    }
    private int quickSelect(TreeNode root, int k, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        int left = root.left == null ? 0 : map.get(root.left); // use map to get # quickly
        if (left >= k) {
            return quickSelect(root.left, k, map);
        } else if (left + 1 < k) {
            return quickSelect(root.right, k - left - 1, map);
        } else {
            return root.val;
        }
    }
    private int countNodes2(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        int left = countNodes2(root.left, map);
        int right = countNodes2(root.right, map);
        map.put(root, left + right + 1); // record the # subtree of current root
        return left + right + 1;
    }

    private int method3(TreeNode root, int k) {
        // iterative inorder traverse
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        // 这里如果我们要返回栈顶的那个 node， 那么在 iteration 的时候就不能先 pop 出来， 而先 peek 看看。 我觉得是因为 pop 出来的在 for 里面， 最后不好 return
        // 这里的 iteration 和基本的 inorder iteration 有些不一样， 因为最后是 peek 留在 stack 中栈顶的那个为答案， 所以在循环里面 pop 和 push 有些不同
        for (int i = 0; i < k - 1; i++) {
            // System.out.println("now " + (i + 1) + "th small");
            TreeNode curr = stack.peek();
            // System.out.println("curr is " + curr.val);
            if (curr.right == null) {
                // System.out.println("no right, pop it");
                curr = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == curr) {
                    // 一直 pop 到此 curr 的 root 节点， 这个时候 pop 的其实已经是前面出现过的， 比如此 curr 是第三小， 那么会把第二小的也 pop 出来， 因为已经过了并且不需要返回
                    curr = stack.pop();
                    // System.out.println("keep pop: " + curr.val);
                }
            } else {
                // 如果有右子树的话， 一直 push 进去直到右子树的最小 node （最左的leaf node）
                curr = curr.right;
                // System.out.println("has right, now it's " + curr.val);
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                    // System.out.print("push it, now it's ");
                    // if (curr == null) {
                    //     System.out.println("null");
                    // } else {
                    //     System.out.println(curr.val);
                    // }
                }
            }
        }
        return stack.peek().val;
    }

    private int result;
    private int count;
    private int method2(TreeNode root, int k) {
        // recursive inorder traverse
        // O(k + h) time where h is the height of BST
        result = root.val;
        count = 0;
        inorder(root, k);
        return result;
    }
    private void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inorder(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
        }
        inorder(root.right, k);
    }

    private int method1(TreeNode root, int k) {
        // recursion
        int leftSub = countNodes(root.left);
        if (leftSub >= k) {
            return method1(root.left, k);
        } else if (leftSub + 1 < k) {
            return method1(root.right, k - leftSub - 1);
        } else {
            return root.val;
        }
    }
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }
}
