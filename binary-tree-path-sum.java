// 376. Binary Tree Path Sum
// 中文English
// Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
//
// A valid path is from root node to any of the leaf nodes.
//
// Example
// Example 1:
//
// Input:
// {1,2,4,2,3}
// 5
// Output: [[1, 2, 2],[1, 4]]
// Explanation:
// The tree is look like this:
// 	     1
// 	    / \
// 	   2   4
// 	  / \
// 	 2   3
// For sum = 5 , it is obviously 1 + 2 + 2 = 1 + 4 = 5
// Example 2:
//
// Input:
// {1,2,4,2,3}
// 3
// Output: []
// Explanation:
// The tree is look like this:
// 	     1
// 	    / \
// 	   2   4
// 	  / \
// 	 2   3
// Notice we need to find all paths from root node to leaf nodes.
// 1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5
// There is no one satisfying it.


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
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayList<Integer> path = new ArrayList<>();

        // based on sum
        // int sum = root.val;
        // path.add(root.val);
        // helper(root, target, path, sum, result);
        // return result;

        // based on remain
        path.add(root.val);
        dfs(root, target - root.val, path, result);
        return result;
    }
    private void helper(TreeNode root, int target, ArrayList<Integer> path,
                        int sum, List<List<Integer>> result) {
        // if (root == null) {
        //     return ..
        // }

        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
                return;
            }
        }

        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, target, path, sum + root.left.val, result);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, target, path, sum + root.right.val, result);
            path.remove(path.size() - 1);
        }

    }
    // or we can only transfer remain
    private void dfs(TreeNode root, int remain, ArrayList<Integer> path, List<List<Integer>> result) {
        // because problem require a valid path as root -> leaf
        if (root.left == null && root.right == null) {
            if (remain == 0) {
                result.add(new ArrayList<Integer>(path));
            }
        }
        if (root.left != null) {
            path.add(root.left.val);
            dfs(root.left, remain - root.left.val, path, result);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.val);
            dfs(root.right, remain - root.right.val, path, result);
            path.remove(path.size() - 1);
        }
    }
}
