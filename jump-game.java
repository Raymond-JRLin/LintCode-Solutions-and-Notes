// 116. Jump Game
// 中文English
// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Determine if you are able to reach the last index.
//
// Example
// Example 1
//
// Input : [2,3,1,1,4]
// Output : true
// Example 2
//
// Input : [3,2,1,0,4]
// Output : false
// Notice
// This problem have two method which is Greedy and Dynamic Programming.
//
// The time complexity of Greedy method is O(n).
//
// The time complexity of Dynamic Programming method is O(n^2).
//
// We manually set the small data set to allow you pass the test in both ways. This is just to let you learn how to use this problem in dynamic programming ways. If you finish it in dynamic programming ways, you can try greedy method to make it accept again.
//


public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        // wirte your code here
        if (A == null || A.length == 0) {
            return false;
        }
        int n = A.length;
        // // definition
        // boolean[] f = new boolean[n];
        // // initialization
        // f[0] = true;
        // for (int i = 1; i < n; i++) {
        //     f[i] = false;
        // }
        // // DP
        // for (int i = 1; i < n; i++) {
        //     for (int j = 0; j < i; j++) {
        //         if (f[j] == true && A[j] + j >= i) {
        //             f[i] = true;
        //         }
        //     }
        // }
        // // answer
        // return f[n - 1];

        // greedy
        int farthest = A[0];
        for (int i = 1; i< n; i++) {
            if (i <= farthest && A[i] + i > farthest) {
                farthest = A[i] + i;
            }
        }
        return farthest >= n - 1;
    }
}
