// 205. Interval Minimum Number
// Description
// Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.
//
// We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.
//
// Have you met this question in a real interview?
// Example
// For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]
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
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
        if (A == null || A.length == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<Integer>();
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
        SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
        if (start != end) {
            int mid = start + (end - start) / 2;
            root.left = build(nums, start, mid);
            root.right = build(nums, mid + 1, end);
            root.min = Math.min(root.left.min, root.right.min);
        } else {
            root.min = nums[start];
        }
        return root;
    }
    private int query(SegmentTreeNode root, int left, int right) {
        if (left == root.start && right == root.end) {
            return root.min;
        }
        int mid = root.start + (root.end - root.start) / 2;
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;
        if (left <= mid) {
            if (right <= mid) {
                leftMin = query(root.left, left, right);
            } else {
                leftMin = query(root.left, left, mid);
            }
        }
        if (right > mid) {
            if (left <= mid) {
                rightMin = query(root.right, mid + 1, right);
            } else {
                rightMin = query(root.right, left, right);
            }
        }
        return Math.min(leftMin, rightMin);
    }
}
class SegmentTreeNode {
    int start;
    int end;
    int min;
    SegmentTreeNode left; // don't forget to build left and right children
    SegmentTreeNode right;
    public SegmentTreeNode(int start, int end, int min) {
        this.start = start;
        this.end = end;
        this.min = min;
        this.left = null;
        this.right = null;
    }
}
