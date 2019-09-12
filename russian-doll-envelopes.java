// 602. Russian Doll Envelopes
// 中文English
// Give a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
// Find the maximum number of nested layers of envelopes.
//
// Example
// Example 1:
//
// Input：[[5,4],[6,4],[6,7],[2,3]]
// Output：3
// Explanation：
// the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
// Example 2:
//
// Input：[[4,5],[4,6],[6,7],[2,3],[1,1]]
// Output：4
// Explanation：
// the maximum number of envelopes you can Russian doll is 4 ([1,1] => [2,3] => [4,5] / [4,6] => [6,7]).


public class Solution {
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        // Write your code here
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length < 2) {
            return -1;
        }
        int n = envelopes.length;
        if (n == 1) {
            return 1;
        }
        // O(n^2) method
        // // sorting
        // Arrays.sort(envelopes, new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] a, int[] b) {
        //         if (a[0] == b[0]) {
        //             return a[1] - b[1]; // ascending of height if widths are equal
        //         } else {
        //             return a[0] - b[0]; // ascending of width
        //         }
        //     }
        // });
        // // definition
        // int[] f = new int[n];
        // // initialization
        // for (int i = 0; i < n; i++) {
        //     f[i] = 1; // at least they have themselves
        // }
        // // DP
        // int result = 0;
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < i; j++) {
        //         if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
        //             f[i] = Math.max(f[i], f[j] + 1);
        //         }
        //     }
        //     result = Math.max(result, f[i]);
        // }
        // // result
        // // it's not necessarily f[n - 1]
        // return result;

        // O(nlogn) method - based on BS
        // sorting
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1]; // descending of height if widths are equal
                } else {
                    return a[0] - b[0]; // ascending of width
                }
            }
        });
        int[] dp = new int[n];
        int count = 0;
        for (int[] env : envelopes) {
            int index = Arrays.binarySearch(dp, 0, count, env[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = env[1];
            if (index == count) {
                count++;
            }
        }
        return count;
    }
}
