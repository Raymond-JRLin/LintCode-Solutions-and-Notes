// 460. Find K Closest Elements
// 中文English
// Given target, a non-negative integer k and an integer array A sorted in ascending order, find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. Otherwise, sorted in ascending order by number if the difference is same.
//
// Example
// Example 1:
//
// Input: A = [1, 2, 3], target = 2, k = 3
// Output: [2, 1, 3]
// Example 2:
//
// Input: A = [1, 4, 6, 8], target = 3, k = 3
// Output: [4, 1, 6]
// Challenge
// O(logn + k) time
//
// Notice
// The value k is a non-negative integer and will always be smaller than the length of the sorted array.
// Length of the given array is positive and will not exceed 10^410
// ​4
// ​​
// Absolute value of elements in the array will not exceed 10^410
// ​4
// ​​


public class Solution {
    /**
     * @param A an integer array
     * @param target an integer
     * @param k a non-negative integer
     * @return an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // Write your code here
        if (A == null || A.length < k) {
            return null;
        }
        if (k == 0) {
            return new int[0];
        }
        int[] results = new int[k];
        int n = A.length;
        // we use BS first to locate the target position or the closest value in array
        int start = 0;
        int end = n - 1;
        int pointer = -1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                pointer = mid;
                break;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (pointer == -1) {
            pointer = getDiff(A[start], target) <= getDiff(A[end], target) ? start : end;
            // we use = here because if the difference is equal, we need to use previous one
        }
        // we found the target or the closest number to target
        results[0] = A[pointer];
        // then we use 2 pointers to traverse to left and right
        int left = pointer - 1;
        int right = pointer + 1;
        int count = 1;
        while (count < k && left >= 0 && right < n) {
            int diffLeft = getDiff(A[left], target);
            int diffRight = getDiff(A[right], target);
            if (diffLeft <= diffRight) {
                // put = here with the same reason above
                results[count] = A[left];
                left--;
                count++;
            } else {
                results[count] = A[right];
                right++;
                count++;
            }
        }
        // the number of answer may not be enough to k
        while (count < k && left >= 0) {
            results[count] = A[left];
            left--;
            count++;
        }
        while (count < k && right < n) {
            results[count] = A[right];
            right++;
            count++;
        }
        return results;
    }
    private int getDiff(int num, int target) {
        return Math.abs(num - target);
    }
}
