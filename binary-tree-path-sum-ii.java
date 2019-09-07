// 246. Binary Tree Path Sum II
// 中文English
// Your are given a binary tree in which each node contains a value. Design an algorithm to get all paths which sum to a given value. The path does not need to start or end at the root or a leaf, but it must go in a straight line down.
//
// Example
// Example 1:
//
// Input:
// {1,2,3,4,#,2}
// 6
// Output:
// [[2, 4],[1, 3, 2]]
// Explanation:
// The binary tree is like this:
//     1
//    / \
//   2   3
//  /   /
// 4   2
// for target 6, it is obvious 2 + 4 = 6 and 1 + 3 + 2 = 6.
// Example 2:
//
// Input:
// {1,2,3,4}
// 10
// Output:
// []
// Explanation:
// The binary tree is like this:
//     1
//    / \
//   2   3
//  /
// 4
// for target 10, there is no way to reach it.


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
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // Write your code here
        // the diff from I is the start and end node is not fixed, so the possibilities are much more
        // except DFS to traverse all paths from root, we also need to check all paths starting at all nodes
        // so if we DSF, it would track all paths starting with root, as for path starting at any node in the middle, we need to track bottom-up when we reach one node
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        List<Integer> path = new ArrayList<>();
        helper(results, target, root, path, 0);
        return results;
    }
    private void helper(List<List<Integer>> results, int target, TreeNode head, List<Integer> path, int level) {
        if (head == null) {
            return;
        }
        int residue = target;
        path.add(head.val);
        for (int i = level; i >= 0; i--) {
            residue -= path.get(i);
            if (residue == 0) {
                List<Integer> list = new ArrayList<>();
                for (int j = i; j <= level; j++) {
                    list.add(path.get(j));
                }
                results.add(list);
            }
        }
        // in recusion, the target would not be changed, because everytime, we need to find a new path from any node, whose sum equals target
        helper(results, target, head.left, path, level + 1);
        helper(results, target, head.right, path, level + 1);
        path.remove(path.size() - 1);
    }
}
