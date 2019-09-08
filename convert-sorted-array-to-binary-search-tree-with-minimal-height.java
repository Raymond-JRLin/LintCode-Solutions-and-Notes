// 177. Convert Sorted Array to Binary Search Tree With Minimal Height
// 中文English
// Given a sorted (increasing order) array, Convert it to create a binary search tree with minimal height.
//
// Example
// Example 1:
//
// Input: []
// Output:  {}
// Explanation: The binary search tree is null
// Example 2:
//
// Input: [1,2,3,4,5,6,7]
// Output:  {4,2,6,1,3,5,7}
// Explanation:
// A binary search tree with minimal height.
//
//          4
//        /   \
//       2     6
//      / \    / \
//     1   3  5   7
//
//
// Notice
// There may exist multiple valid solutions, return any of them.

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
     * @param A: an integer array
     * @return: a tree node
     */
    public TreeNode sortedArrayToBST(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return null;
        }
        int n = A.length;
        int median = n / 2;
        int val = A[median];
        TreeNode root = new TreeNode(val);
        root.right = sortedArrayToBST(Arrays.copyOfRange(A, median + 1, n));
        root.left = sortedArrayToBST(Arrays.copyOfRange(A, 0, median));
        return root;
    }
}
