// 614. Binary Tree Longest Consecutive Sequence II
// 中文English
// Given a binary tree, find the length(number of nodes) of the longest consecutive sequence(Monotonic and adjacent node values differ by 1) path.
// The path could be start and end at any node in the tree
//
// Example
// Example 1:
//
// Input:
// {1,2,0,3}
// Output:
// 4
// Explanation:
//     1
//    / \
//   2   0
//  /
// 3
// 0-1-2-3
// Example 2:
//
// Input:
// {3,2,2}
// Output:
// 2
// Explanation:
//     3
//    / \
//   2   2
// 2-3


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    int sum = 0;
    public int longestConsecutive2(TreeNode root) {
        // Write your code here
        if (root == null) {
            return 0;
        }
        ResultType result = helper(root);
        return result.maxLength;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        int down = 0;
        int up = 0;
        if (root.left != null && root.left.val + 1 == root.val) {
            up = Math.max(up, left.maxUp + 1);
        }
        if (root.left != null && root.left.val - 1 == root.val) {
            down = Math.max(down, left.maxDown + 1);
        }
        if (root.right != null && root.right.val + 1 == root.val) {
            up = Math.max(up, right.maxUp + 1);
        }
        if (root.right != null && root.right.val - 1 == root.val) {
            down = Math.max(down, right.maxDown + 1);
        }
        int len = up + down + 1;
        len = Math.max(len, Math.max(left.maxLength, right.maxLength));
        return new ResultType(len, up, down);
    }
}
class ResultType {
    int maxDown;
    int maxUp;
    int maxLength;
    ResultType(int length, int up, int down) {
        maxLength = length;
        maxUp = up;
        maxDown = down;
    }

}
