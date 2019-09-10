// 1198. Most Frequent Subtree Sum
// 中文English
// Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
//
// Example
// Example 1:
//
// Input:
// {5,2,-3}
// Output:
// [-3,2,4]
// Explanation:
//   5
//  /  \
// 2   -3
// since all the values happen only once, return all of them in any order.
// Example 2:
//
// Input:
// {5,2,-5}
// Output:
// [2]
// Explanation:
//   5
//  /  \
// 2   -5
// since 2 happens twice, however -5 only occur once.
// Notice
// You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
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
     * @param root: the root
     * @return: all the values with the highest frequency in any order
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        // Write your code here
        if (root == null) {
            return new int[0];
        }

        return mytry(root);
    }

    private int[] mytry(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>(); // <sum, freq>
        recursion(root, map);

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxFreq) {
                list.add(entry.getKey());
            }
        }

        int size = list.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
    private int maxFreq = 0;
    private int recursion(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        int left = recursion(root.left, map);
        int right = recursion(root.right, map);
        int sum = left + right + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        maxFreq = Math.max(maxFreq, map.get(sum));
        return sum;
    }
    private class mapComparator implements Comparator<Map.Entry<Integer, Integer>> {
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return Integer.compare(o2.getValue(), o1.getValue());
        }
    }
}
