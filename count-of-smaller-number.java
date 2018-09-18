// 248. Count of Smaller Number
// Description
// Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller than the given integer.
//
// We suggest you finish problem Segment Tree Build and Segment Tree Query II first.
//
// Have you met this question in a real interview?
// Example
// For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]
//
// Challenge
// Could you use three ways to do it.
//
// Just loop
// Sort and binary search
// Build Segment Tree and Search.


public class Solution {
    /*
     * @param A: An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        if (A == null || queries == null) {
            return null;
        }
        List<Integer> result = new ArrayList<Integer>();
        if (queries.length == 0) {
            return result;
        }
        if (A.length == 0) {
            for (int i = 0; i < queries.length; i++) {
                result.add(0);
            }
            return result;
        }
        int n = A.length;
        int qLen = queries.length;
        // remember if problem did not say A is sorted then we should consider A as unsorted array, otherwise loop method and BS method would not work
        Arrays.sort(A);

        // method 1: loop
        // for (int i = 0; i < qLen; i++) {
        //     int count = 0;
        //     for (int j = 0; j < n; j++) {
        //         if (A[j] < queries[i]) {
        //             count++;
        //         } else {
        //             break;
        //         }
        //     }
        //     result.add(count);
        // }
        // return result;

        // method 2: BS
        // for (int i = 0; i < qLen; i++) {
        //     int count = count(A, queries[i]);
        //     result.add(count);
        // }
        // return result;

        // method 3: Segment Tree
        // 在这题之前， 我们做了 Segment Tree 相关的 build， query， modify， 那这题是将这三者结合起来， 如果用 Segment Tree， 很容易想到 node 可以额外加一个 attribute 为在 [0, A[i] - 1] 这个区间内， 小于 A[i] 的数量， 那可以仿照着做： 把 leaf node 为 A[i] 的 count 设置为 1， 其余为 0， 那么往上层层递归， 就可以统计到区间内有多少了
        // build Segment Tree
        SegmentTreeNode root = build(0, 10000, 0);
        // mark node with interval of A[] plus 1
        for (int i = 0; i < n; i++) {
            modify(root, A[i], 1);
        }
        // search 0 ~ A[i] - 1
        for (int i = 0; i < qLen; i++) {
            int count = query(root, 0, queries[i] - 1);
            result.add(count);
        }
        return result;
    }
    // method 2: BS
    private int count(int[] nums, int target) {
        // 要记住条件是 smaller 相等也不可以
        if (target <= nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // 注意 double check 各种情况
        if (nums[start] > target) {
            return start - 1;
        } else if (nums[start] == target) {
            return start;
        } else if (nums[start] < target && nums[end] > target) {
            return start + 1;
        } else if (nums[end] == target) {
            return end;
        } else {
            return end + 1;
        }
    }

    // method 3: Segment Tree
    private SegmentTreeNode build (int start, int end, int count) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end, count);
        if (start != end) {
            int mid = start + (end - start) / 2;
            root.left = build(start, mid, count);
            root.right = build(mid + 1, end, count);
        }
        return root;
    }
    private void modify(SegmentTreeNode root, int index, int value) {
        if (root.start == index && root.end == index) {
            root.count += value;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            modify(root.left, index, value);
        }
        if (index > mid) {
            modify(root.right, index, value);
        }
        root.count = root.left.count + root.right.count;
    }
    private int query(SegmentTreeNode root, int start, int end) {
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
class SegmentTreeNode {
    public int start, end, count;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
        this.left = this.right = null;
    }
}
