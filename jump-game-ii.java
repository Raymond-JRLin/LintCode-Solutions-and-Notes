// 117. Jump Game II
// 中文English
// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Your goal is to reach the last index in the minimum number of jumps.
//
// Example
// Example 1
//
// Input : [2,3,1,1,4]
// Output : 2
// Explanation : The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)


public class Solution {
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        // write your code here
        if (A == null || A.length <= 1) {
            return 0;
        }
        int n = A.length;
        // DP analyze: if we defin f(i) = #min jumps to reach i position, last step is we can jump from some positions j(s) before i, and we choose the min f(j) plus 1 hop as f(i); it's O(n^2)
        // so state transfer function:
        // f(i) = min{f(j)} + 1, 0 <= j < i && j + A[j] >= i
        // // definition
        // int[] f = new int[n];
        // // initialization
        // f[0] = 0;
        // for (int i = 1; i < n; i++) {
        //     f[i] = n; // max jumps is n - 1
        // }
        // // DP
        // for (int i = 1; i < n; i++) {
        //     // i should start from 1
        //     for (int j = 0; j < i; j++) { // condition: j + A[j] >= i
        //         // but j can start from 0 position, i.e. from beginning
        //         if (j + A[j] >= i) { // condition: 0 <= j < i
        //             f[i] = Math.min(f[i], f[j] + 1); // state transfer function
        //         }
        //     }
        // }
        // // result
        // return f[n - 1];

        // above DP is correct but would be TLE
        // so we should use greedy like Jump Game I: everytime we jump to farthest we can do, and count 1 jump, when we jump over the last index, we're done
        int start = 0;
        int end = 0;
        int count = 0;
        while (end < n - 1) {
            count++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return count;
    }
}
