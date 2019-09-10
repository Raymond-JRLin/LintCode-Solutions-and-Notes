// 65. Median of two Sorted Arrays
// 中文English
// There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.
//
// Example
// Example 1
//
// Input:
// A = [1,2,3,4,5,6]
// B = [2,3,4,5]
// Output: 3.5
// Example 2
//
// Input:
// A = [1,2,3]
// B = [4,5]
// Output: 3
// Challenge
// The overall run time complexity should be O(log (m+n)).
//
// Clarification
// The definition of the median:
//
// The median here is equivalent to the median in the mathematical definition.
//
// The median is the middle of the sorted array.
//
// If there are n numbers in the array and n is an odd number, the median is A[(n-1)/2]A[(n−1)/2].
//
// If there are n numbers in the array and n is even, the median is (A[n / 2] + A[n / 2 + 1]) / 2.
//
// For example, the median of the array A=[1,2,3] is 2, and the median of the array A=[1,19] is 10.
//


class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0; // double type
        } else {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }
    private double findKth(int[] A, int startA, int[] B, int startB, int k) {
        if (startA >= A.length) {
            return B[startB + k - 1];
        }
        if (startB >= B.length) {
            return A[startA + k - 1];
        }
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }
        int keyA = startA + k / 2 - 1 <= A.length ? A[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int keyB = startB + k / 2 - 1 <= B.length ? B[startB + k / 2 - 1] : Integer.MAX_VALUE;
        if (keyA <= keyB) {
            return findKth(A, startA + k / 2, B, startB, k - k / 2);
        } else {
            return findKth(A, startA, B, startB + k / 2, k - k / 2);
        }
    }
}
