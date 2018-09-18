// 206. Interval Sum
// Description
// Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.
//
// We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.
//
// Have you met this question in a real interview?
// Example
// For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]
//
// Challenge
// O(logN) time for each query


/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


public class Solution {
    /*
     * @param A: An integer list
     * @param queries: An query list
     * @return: The result list
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        // write your code here
        if (A == null || A.length == 0) {
            return null;
        }
        List<Long> result = new ArrayList<Long>();
        SegmentTreeNode root = build(A, 0, A.length - 1);
        for (Interval interval : queries) {
            result.add(query(root, interval.start, interval.end));
        }
        return result;
    }
    private SegmentTreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end, 0L);
        if (start != end) {
            int mid = start + (end - start) / 2;
            root.left = build (nums, start, mid);
            root.right = build(nums, mid + 1, end);
            root.sum = root.left.sum + root.right.sum;
        } else {
            // root.sum = nums[start];
            root.sum = Long.valueOf(nums[start]);
        }
        return root;
    }
    private long query(SegmentTreeNode root, int start, int end) {
        if (start <= root.start && end >= root.end) {
            return root.sum;
        }
        long leftSum = 0L;
        long rightSum = 0L;
        int mid = root.start + (root.end - root.start) / 2;
        if (start <= mid) {
            if (end <= mid) {
                leftSum = query(root.left, start, end);
            } else {
                leftSum = query(root.left, start, mid);
            }
        }
        if (end > mid) {
            if (start > mid) {
                rightSum = query(root.right, start, end);
            } else {
                rightSum = query(root.right, mid + 1, end);
            }
        }
        return leftSum + rightSum;
    }
}
class SegmentTreeNode {
    int start;
    int end;
    long sum;
    SegmentTreeNode left;
    SegmentTreeNode right;
    public SegmentTreeNode(int start, int end, long sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.left = null;
        this.right = null;
    }
}
