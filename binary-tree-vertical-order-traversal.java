// 651. Binary Tree Vertical Order Traversal
// 中文English
// Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
//
// If two nodes are in the same row and column, the order should be from left to right.
//
// Example
// Example1
//
// Inpurt:  {3,9,20,#,#,15,7}
// Output: [[9],[3,15],[20],[7]]
// Explanation:
//    3
//   /\
//  /  \
//  9  20
//     /\
//    /  \
//   15   7
// Example2
//
// Input: {3,9,8,4,0,1,7}
// Output: [[4],[9],[3,0,1],[8],[7]]
// Explanation:
//      3
//     /\
//    /  \
//    9   8
//   /\  /\
//  /  \/  \
//  4  01   7


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
    /*
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
        // 为了统一 column， 设置 root 为 0， 向正负两断展开; 是分层输出， 那肯定要 BFS，所以除了 BFS 本身的 queue 外， 还需要一个来配合放入 column 的值， 把每个 column 的值先存在 HashMap 里面， 最后再一起拿出来
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        queue.offer(root);
        cols.offer(0); // set column of root as 0
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int col = cols.poll();
            if (map.containsKey(col)) {
                List<Integer> list = map.get(col);
                list.add(curr.val);
                map.put(col, list);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(curr.val);
                map.put(col, list);
            }
            // has left children
            if (curr.left != null) {
                queue.offer(curr.left);
                cols.offer(col - 1); // negative
            }
            // has right children
            if (curr.right != null) {
                queue.offer(curr.right);
                cols.offer(col + 1); // positive
            }
        }
        // following would return as order of adding
        // for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
        //     List<Integer> list = entry.getValue();
        //     result.add(list);
        // }
        int min = Collections.min(map.keySet());
        int max = Collections.max(map.keySet());
        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        return result;
    }
}
