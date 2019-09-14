// 183. Wood Cut
// 中文English
// Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
//
// Example
// Example 1
//
// Input:
// L = [232, 124, 456]
// k = 7
// Output: 114
// Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.
// Example 2
//
// Input:
// L = [1, 2, 3]
// k = 7
// Output: 0
// Explanation: It is obvious we can't make it.
// Challenge
// O(n log Len), where Len is the longest length of the wood.
//
// Notice
// You couldn't cut wood into float length.
//
// If you couldn't get >= k pieces, return 0.


public class Solution {
    /**
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        if (L == null || L.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }
        int start = 1;
        int end = max;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (Comp(mid, L) == k) {
                start = mid;
            } else if (Comp(mid, L) < k) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (Comp(end, L) >= k) {
            return end;
        }
        if (Comp(start, L) >= k) {
            return start;
        }
        return 0;
    }

    private int Comp(int mid, int[] L) {
            int sum = 0;
            for (int i = 0; i < L.length; i++) {
                sum = sum + L[i] / mid;
            }
            return sum;
        }
}
