// 1480. Dot Product
// Description
// Given two array, find their dot product.(Wikipedia)
//
// if there is no dot product, return -1.
//
// Have you met this question in a real interview?
// Example
// Input:A = [1,1,1]
// B = [2,2,2]
// Output:6


public class Solution {
    /**
     * @param A: an array
     * @param B: an array
     * @return: dot product of two array
     */
    public int dotProduct(int[] A, int[] B) {
        // Write your code here
        if (A == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length) {
            return -1;
        }

        return mytry(A, B);
    }

    private int mytry(int[] A, int[] B) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] * B[i];
        }
        return sum;
    }
}
