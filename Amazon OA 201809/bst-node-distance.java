// 1561. BST Node Distance
// Description
// Given a list of numbers, construct a BST from it(you need to insert nodes one-by-one with the given order to get the BST) and find the distance between two given nodes.
//
// If two nodes do not appear in the BST, return -1
// We guarantee that there are no duplicate nodes in BST
// The node distance means the number of edges between two nodes
// Have you met this question in a real interview?
// Example
// input:
// numbers = [2,1,3]
// node1 = 1
// node2 = 3
// output:
// 2


public class Solution {
    /**
     * @param numbers: the given list
     * @param node1: the given node1
     * @param node2: the given node2
     * @return: the distance between two nodes
     */
    public int bstDistance(int[] numbers, int node1, int node2) {
        // Write your code here
        if (numbers == null || numbers.length < 2) {
            return -1;
        }

        return mytry(numbers, node1, node2);
    }

    private int mytry(int[] nums, int node1, int node2) {

        int n = nums.length;
        // check if node1/2 in nums
        boolean hasNode1 = false;
        boolean hasNode2 = false;
        // build the BST first
        TreeNode root = new TreeNode(nums[0]);
        if (nums[0] == node1) {
                hasNode1 = true;
            }
            if (nums[0] == node2) {
                hasNode2 = true;
            }
        for (int i = 1; i < n; i++) {
            if (nums[i] == node1) {
                hasNode1 = true;
            }
            if (nums[i] == node2) {
                hasNode2 = true;
            }
            // 注意这里构造树是按照 nums 输入的顺序来构造 BST， 大了向右， 小了向左， 直到找到合适的位置插入， 并不平衡， 也不是按照 nums 的 index 来放置左右
            buildTree(root, nums[i]);
        }

        if (!hasNode1 || !hasNode2) {
            return -1;
        }
        return findDist(root, node1, node2);
    }
    private int findDist(TreeNode root, int node1, int node2) {
        // similar to LCA
        if (root == null) {
            return 0;
        }
        if (root.val > node1 && root.val > node2) {
            return findDist(root.left, node1, node2);
        }
        if (root.val < node1 && root.val < node2) {
            return findDist(root.right, node1, node2);
        }
        // node1/2 is LCA, or node1/2 are in 2 side of LCA
        return distFrom(root, node1) + distFrom(root, node2);
    }
    private int distFrom(TreeNode root, int target) {
        // System.out.println("check " + root.val + " , target is " + target);
        if (root.val == target) {
            return 0;
        } else if (root.val > target) {
            return 1 + distFrom(root.left, target);
        } else {
            return 1 + distFrom(root.right, target);
        }
    }
    private void buildTree(TreeNode root, int curr) {
        // 是每次 insert 所以如果有比如 TreeNode 返回值不好做
        if (curr < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(curr);
            } else {
                buildTree(root.left, curr);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(curr);
            } else {
                buildTree(root.right, curr);
            }
        }
    }

    private class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
