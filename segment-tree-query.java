// 202. Segment Tree Query
// Description
// For an integer array (index from 0 to n-1, where n is the size of this array), in the corresponding SegmentTree, each node stores an extra attribute max to denote the maximum number in the interval of the array (index from start to end).
//
// Design a query method with three parameters root, start and end, find the maximum number in the interval [start, end] by the given root of segment tree.
//
// It is much easier to understand this problem if you finished Segment Tree Build first.
//
// Have you met this question in a real interview?
// Example
// For array [1, 4, 2, 3], the corresponding Segment Tree is:
//
//                   [0, 3, max=4]
//                  /             \
//           [0,1,max=4]        [2,3,max=3]
//           /         \        /         \
//    [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
// query(root, 1, 1), return 4
//
// query(root, 1, 2), return 4
//
// query(root, 2, 3), return 3
//
// query(root, 0, 2), return 4


/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if (start == root.start && end == root.end) {
            return root.max;
        }
        // 考虑给的 start & end 分别位于 root 的 start & end 的左边、右边
        int mid = root.start + (root.end - root.start) / 2;
        // for root: left -> [start, mid], right -> [mid + 1, end]
        // 因为有可能会横跨某一个 node， 可以分别找左右两边最大值
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        if (start <= mid) {
            // start is in the left child of root
            if (end > mid) {
                // end is in the right child of root: cross
                leftMax = query(root.left, start, mid);
            } else {
                // end is in the same left part: same part
                leftMax = query(root.left, start, end);
            }
        }
        if (end > mid) {
            // end is in the right child of root
            if (start > mid) {
                // start is also in the right child: same part
                rightMax = query(root.right, start, end);
            } else {
                // start is in the left child: cross
                rightMax = query(root.right, mid + 1, end);
            }
        }
        return Math.max(leftMax, rightMax);
    }
}
