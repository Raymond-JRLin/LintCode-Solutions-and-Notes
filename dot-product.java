// 1480. Dot Product
// 中文English
// Given two array, output their dot product
//
// Example
// Example1
//
// Input: A = [1,1,1] and B = [2,2,2]
// Output: 6
// Explanation:
// 1*2+1*2+1*2=6
// Example2
//
// Input: A = [3,2] and B = [2,3,3]
// Output: -1
// Explanation:
// there is no dot product
// Notice
// if there is no dot product, return -1.
//


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
