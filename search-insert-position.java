// 60. Search Insert Position
// 中文English
// Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
// You may assume NO duplicates in the array.
//
// Example
// [1,3,5,6], 5 → 2
//
// [1,3,5,6], 2 → 1
//
// [1,3,5,6], 7 → 4
//
// [1,3,5,6], 0 → 0
//
// Challenge
// O(log(n)) time
//


public class Solution {
    /**
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if (A == null) {
            return -1;
        }
        if (A.length == 0) {
            return 0;
        }
        int n = A.length;
        if (target <= A[0]) {
            return 0;
        }
        if (target > A[n - 1]) {
            return n;
        }

        // method 1: BS, O(nlogn) time
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        // we did not find target and need to insert it, return inserted position
        if (A[start] == target) {
            return start;
        } else if (A[end] == target) {
            return end;
        } else if (A[start] < target) {
            return end;
        } else {
            return end + 1;
        }

        // method 2: simple traversal, O(n)
        // for (int i = 1; i < n; i++) {
        //     if (A[i] == target) {
        //         return i;
        //     } else if (A[i - 1] < target && A[i] > target) {
        //         return i;
        //     }
        // }
        // return n;
    }
}
