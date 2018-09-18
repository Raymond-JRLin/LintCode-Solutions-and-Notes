// 1488. Longest Sequence
// Description
// Given an array a containing n positive integers, you can arbitrarily select a number of numbers to form an arithmetic progression, what is the maximum length of the arithmetic progression that can be composed?
//
// The length of the array does not exceed 5000
// Guarantee that a[i]a[i] is different from each other
// a[i] <= 1e9a[i]<=1e9
// Have you met this question in a real interview?
// Example
// Given a = [1,2,5,9,10],，return 3.
//
// Explanation:
// You can select [1, 5, 9] to form an arithmetic progression. The length of this is the longest, which is 3
// Given a = [1,3]，return 2.
//
// Explanation:
// At this time, only the series [1, 3] can be selected to form an arithmetic progression with the length of 2


public class Solution {
    /**
     * @param a: The array a
     * @return: Return the maximum length
     */
    public int getAnswer(int[] a) {
        // Write your code here
        if (a == null || a.length <= 1) {
            return 0;
        }

        // return mytry(a);

        return method1(a);
    }

    private int method1(int[] nums) {
        // DP: O(N ^ 2) time and space
        // ref: https://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
        // ref: https://github.com/tongzhang1994/Facebook-Interview-Coding/blob/master/Longest%20Arithmetic%20Subsequence.java
        int n = nums.length;
        Arrays.sort(nums); // to sort to use 2 pointers
        // definition: f[i][j] = # of AP from start of i and end of j
        int[][] f = new int[n][n];
        // initialization: at least has AP with length of 2
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = 2;
            }
        }
        // DP
        int result = 2; // at least we have 2
        // from back to front in refs
        // for (int i = n - 2; i >= 1; i--) {
        //     int left = i - 1;
        //     int right = i + 1;
        //     while (left >= 0 && right < n) {
        //         if (nums[left] + nums[right] < 2 * nums[i]) {
        //             right++;
        //         } else if (nums[left] + nums[right] > 2 * nums[i]) {

        //             left--;
        //         } else {
        //             f[left][i] = f[i][right] + 1;
        //             result = Math.max(result, f[left][i]);
        //             left--;
        //             right++;
        //         }
        //     }
        // }
        for (int i = 1; i < n - 1; i++) {
            // 2 pointers
            // we set i as middles number so that we can use 2 pointers, which means we can go left or right in different situations, 一定要记着用双指针要 “创造” 一个条件， 使得在不同情况下能够分别移动不同的指针， 如果设置第一个数或者第二个数的话， 那么没办法区分不同的情况并移动不同的指针
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < n) {
                if (nums[left] + nums[right] < 2 * nums[i]) {
                    right++;
                } else if (nums[left] + nums[right] > 2 * nums[i]) {
                    left--;
                } else {
                    // 此时 i 和 right 的差值正好等于 left 和 i 的差值， 那么意味着 right 能够连接到从 left 到 i 的已经存在了的 AP 上， 增加 1 个长度
                    f[i][right] = f[left][i] + 1;
                    result = Math.max(result, f[i][right]); // update
                    // move pointers
                    left--;
                    right++;
                }
            }
        }
        // result
        return result;
    }

    private int mytry(int[] nums) {
        // brute force: O(N ^ 3) time, TLE
        int n = nums.length;
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i];
                int prev = nums[j];
                int count = 2;
                for (int k = j + 1; k < n; k++) {
                    if (nums[k] - prev == diff) {
                        count++;
                        prev = nums[k];
                    }
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }
}
