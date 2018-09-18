// 247. Segment Tree Query II
// Description
// For an array, we can build a SegmentTree for it, each node stores an extra attribute count to denote the number of elements in the the array which value is between interval start and end. (The array may not fully filled by elements)
//
// Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.
//
// It is much easier to understand this problem if you finished Segment Tree Buildand Segment Tree Query first.
//
// Have you met this question in a real interview?
// Example
// For array [0, 2, 3], the corresponding value Segment Tree is:
//
//                      [0, 3, count=3]
//                      /             \
//           [0,1,count=1]             [2,3,count=2]
//           /         \               /            \
//    [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
// query(1, 1), return 0
//
// query(1, 2), return 1
//
// query(2, 3), return 2
//
// query(0, 2), return 2
//
// Challenge
// It is much easier to understand this problem if you finished Segment Tree Buildand Segment Tree Query first.


/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, count;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int count) {
 *         this.start = start;
 *         this.end = end;
 *         this.count = count;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here

        // 这里的线段树是值型线段树， 每个 node 表示的是数组里的值而非 index
        if (root == null || start > end) {
            return 0;
        }
        // 能够直接返回 count 的 不仅仅是恰好相等， 而是在其范围内都是： node 如果比 start-end 范围大， 那要继续深入递归下去； 如果 node 的范围在 start-end 内， 那么可以返回这个 node 的 count
        if (root.start >= start && root.end <= end) {
            return root.count;
        }
        int leftCount = 0;
        int rightCount = 0;
        int mid = root.start + (root.end - root.start) / 2;
        if (start <= mid) {
            if (end <= mid) {
                leftCount = query(root.left, start, end);
            } else {
                leftCount = query(root.left, start, mid);
            }
        }
        if (end > mid) {
            if (start > mid) {
                rightCount = query(root.right, start, end);
            } else {
                rightCount = query(root.right, mid + 1, end);
            }
        }
        return leftCount + rightCount;
    }
}
