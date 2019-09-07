// 535. House Robber III
// 中文English
// The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
// Determine the maximum amount of money the thief can rob tonight without alerting the police.
//
// Example
// Example1
//
// Input:  {3,2,3,#,3,#,1}
// Output: 7
// Explanation:
// Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
//   3
//  / \
// 2   3
//  \   \
//   3   1
// Example2
//
// Input:  {3,4,5,1,3,#,1}
// Output: 9
// Explanation:
// Maximum amount of money the thief can rob = 4 + 5 = 9.
//     3
//    / \
//   4   5
//  / \   \
// 1   3   1
// Notice
// This problem is the extention of House Robber and House Robber II
//


/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int x) { val = x; }
 * }
 */


public class Solution {
    /*
     * @param root: The root of binary tree.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber3(TreeNode root) {
        // write your code here
        // III 就不能用 DP 了， 因为 DP 走了就不回头了
        // 看起来要遍历所有的分支
        // 注意看例子， 不在同一个分支的也可以偷， 所以只要不相邻， 就都可以偷, 包括不在同一个分支上或者相隔不止一个
        // 所以感觉要记住包含和不包含当前节点的总值
        if (root == null) {
            return 0;
        }

        // method 1: divide conquer
        int[] result = divideCon(root);
        return Math.max(result[0], result[1]);
    }
    private int[] divideCon(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        // go left
        int[] left = divideCon(root.left);
        // go right
        int[] right = divideCon(root.right);
        // calculate result, use an array with 2 space to record result
        int[] result = new int[2];
        // do not rob current node
        // result[0] = left[1] + right[1];
        // above sentence is wrong, because if we do not rob current node, we can also choose not to rub left/right child and go further to rob
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // rob current node, then do not rob left and right child
        result[1] = root.val + left[0] + right[0];
        return result;
    }
};
