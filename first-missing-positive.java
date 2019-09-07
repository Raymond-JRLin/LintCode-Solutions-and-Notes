// 189. First Missing Positive
// 中文English
// Given an unsorted integer array, find the first missing positive integer.
//
// Example
// Example 1:
//
// Input:[1,2,0]
// Output:3
// Example 2:
//
// Input:[3,4,-1,1]
// Output:2
// Challenge
// Your algorithm should run in O(n) time and uses constant space.
//


public class Solution {
    /**
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
        // write your code here
        // 看了一些 discussion 和答案，应该这么理解题意： 给定的数组可以有任意的 integer， 但是 positive int 从 1 开始， 所以我们不论给的数组是怎么样的， 我们就从 1 开始比对， 找到以 1， 2， 3 。。。 这个顺序第一个没出现的正整数就好了
        if (A == null || A.length == 0) {
            return 1; // return the 1st positive integer: 1
        }
        for (int i = 0; i < A.length; i++) {
            while (A[i] > 0 && A[i] <= A.length && A[A[i] - 1] != A[i]) {
                // int temp = A[i];
                // A[i] = A[A[i] - 1];
                // A[A[i] - 1] = temp;
                // above would cause TLE since A[i] has been changed when we calculate A[A[i] - 1], so we can use swap method to fix 2 indices
                // swap(A, i, A[i] - 1);

                // or
                int temp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = temp;
            }
        }
        // for (int i = 0; i < A.length; i++) {
        //      // 1: A[i] is in the range;
        //      // 2: A[i] > 0.
        //      // 3: The target is different;
        //      while (A[i] <= A.length && A[i] > 0 && A[A[i] - 1] != A[i]) {
        //          swap(A, i, A[i] - 1);
        //      }
        //  }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return A.length + 1;
    }
    private void swap(int[] A, int l, int r) {
        int temp = A[l];
        A[l] = A[r];
        A[r] = temp;
    }
}
