// 164. Unique Binary Search Trees II
// 中文English
// Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
//
// Example
// Example 1:
//
// Input:3
// Output:
//     1         3     3       2    1
//      \       /     /       / \    \
//       3     2     1       1   3    2
//      /     /       \                \
//     2     1         2                3


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
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> generateTrees(int n) {
        // write your code here
        List<TreeNode> result = new ArrayList<>();
        if (n == 0) {
            result.add(null);
            return result;
        }
        // if (n == 1) {
        //     TreeNode root = new TreeNode(1);
        //     result.add(root);
        //     return result;
        // }
        return buildBST(1, n);
    }
    private List<TreeNode> buildBST(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            // 如果不 add(null) 那么在 for 循环中就不会 loop 到这个点， 从而 root 也不会被达到， e.g. n = 1
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSub = buildBST(start, i - 1);
            List<TreeNode> rightSub = buildBST(i + 1, end);
            for (TreeNode left : leftSub) {
                for (TreeNode right : rightSub) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
