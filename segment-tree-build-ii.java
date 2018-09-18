// 439. Segment Tree Build II
// Description
// The structure of Segment Tree is a binary tree which each node has two attributes start and end denote an segment / interval.
//
// start and end are both integers, they should be assigned in following rules:
//
// The root's start and end is given by build method.
// The left child of node A has start=A.left, end=(A.left + A.right) / 2.
// The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right.
// if start equals to end, there will be no children for this node.
// Implement a build method with a given array, so that we can create a corresponding segment tree with every node value represent the corresponding interval max value in the array, return the root of this segment tree.
//
// Have you met this question in a real interview?
// Clarification
// Segment Tree (a.k.a Interval Tree) is an advanced data structure which can support queries like:
//
// which of these intervals contain a given point
// which of these points are in a given interval
// See wiki:
// Segment Tree
// Interval Tree
//
// Example
// Given [3,2,1,4]. The segment tree will be:
//
//                  [0,  3] (max = 4)
//                   /            \
//         [0,  1] (max = 3)     [2, 3]  (max = 4)
//         /        \               /             \
// [0, 0](max = 3)  [1, 1](max = 2)[2, 2](max = 1) [3, 3] (max = 4)


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
     * @param A: a list of integer
     * @return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        // write your code here

        // 和 I 不同的是： start 和 end 表示数组的坐标， max 表示 start ～ end 之间最大的数组中的元素
        // 那肯定还是要通过递归来实现树的 build， 注意 max 怎么处理
        // 如果去找对应的区间内最大的数肯定很麻烦， 也不实际 - 每次都要 loop 一遍， 所以我们可以发现 max = Max{left.max, right.max}， 既然使用递归， 那就一直递归下去， max 从最后一个开始各个返回好了

        if (A == null || A.length == 0) {
            return null;
        }
        int n = A.length;
        return buildTree(A, 0, n - 1);
    }
    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        // similar to build I
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end, nums[start]);
        if (start != end) {
            int mid = start + (end - start) / 2;
            root.left = buildTree(nums, start, mid);
            root.right = buildTree(nums, mid + 1, end);
            root.max = Math.max(root.left.max, root.right.max);
        } else {
            root.max = nums[start];
        }
        return root;
    }
}
