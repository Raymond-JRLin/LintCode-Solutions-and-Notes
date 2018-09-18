// 1470. The Game Of Take Numbers
// Description
// Now there is an array arr. There are two players, No. 1 and No. 2 take turns to get numbers from the array. They can only fetch from both ends of the array, and only one can be taken at a time. Both of them adopt an optimal strategy. After the number in the last array is taken, the sum of the numbers taken is more, and the victory is won. Player No. 1 is taken first. Ask who will win in the end. If the No. 1 player wins or the two draw a tie , return 1 and if the 2nd player wins, return 2.
//
// 1 \leq n \leq 100001≤n≤10000
// 1 \leq arr[i] \leq 100001≤arr[i]≤10000
// Ensure that the array length is even
// Have you met this question in a real interview?
// Example
// Give arr=[1,3,1,1]. Return 1.
//
// sum1, sum2 are the scores of two players
// The optimal strategy for the No. 1 player takes the tail of the array, at which point the array is [1,3,1]. sum1=1.
// At this time, the second player has two ways to take it.
// 1,
// Player No. 2 takes the head and the array is [3,1]. sum2=1.
// Player No. 1 takes the head and the array is [1]. sum1=4.
// Player No. 2 takes the head. sum2=2.
// Sum1>sum2
// 2,
// Player No. 2 takes the head and the array is [1,3]. sum2=1.
// The first player takes the tail and the array is [1]. sum1=4.
// Player No. 2 takes the head. sum2=2.
// sum1>sum2
// Therefore, player No. 1 must win and return 1.
// Give arr=[1,3,3,1]. Returns 1.
//
// Sum1, sum2 are the scores of two players
// Since the array is symmetrical, the player No. 1 starts from the head and tail and the result is the same.
// The first player takes the array header, and the array is [3, 3, 1]. sum1=1.
// At this time, the second player has two ways to take it.
// 1,
// Player No. 2 takes the head and the array is [3, 1]. sum2=3.
// Player No. 1 takes the head and the array is [1]. sum1=4.
// Player No. 2 takes the head. Sum2=4.
// sum1=sum2
// 2,
// Player No. 2 takes the tail and the array is [3, 3]. sum2=1.
// Player No. 1 takes the head and the array is [3]. sum1=4.
// Player No. 2 takes the head. Sum2=4.
// sum1=sum2
// So the 1st player and the 2nd player draw a tie and return 1.


public class Solution {
    /**
     * @param arr: the array
     * @return: the winner
     */
    public int theGameOfTakeNumbers(int[] arr) {
        // Write your code here
        if (arr == null || arr.length == 0) {
            return 1;
        }

        // return mytry(arr);

        // return mytry2(arr);

        // return mytry3(arr);

        // return mytry4(arr);

        return method2(arr);
    }

    private int method2(int[] arr) {
        return 1;
    }

    private int mytry4(int[] p) {
        // 1D iteration DP
        int[] dp = Arrays.copyOf(p, p.length);;
        for (int d = 1; d < p.length; d++)
            for (int i = 0; i < p.length - d; i++)
                dp[i] = Math.max(p[i] - dp[i + 1], p[i + d] - dp[i]);
        return dp[0] >= 0 ? 1 : 2;
    }

    private int mytry3(int[] p) {
        // MLE
        // 2D iteration DP
        // same as LeetCode - stone game
        int n = p.length;
        // definition: dp[i][j] = the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j]
        // You can first pick piles[i] or piles[j].
        // If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
        // If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
        // so: dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
        int[][] dp  = new int[n][n];
        // initialization
        for (int i = 0; i < n; i++) {
            dp[i][i] = p[i];
        }
        // DP
        // for (int d = 1; d < n; d++) {
        //     for (int i = 0; i < n - d; i++) {
        //         dp[i][i + d] = Math.max(p[i] - dp[i + 1][i + d], p[i + d] - dp[i][i + d - 1]);
        //     }
        // }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(p[i] - dp[i + 1][j], p[j] - dp[i][j - 1]);
            }
        }
        // result
        return dp[0][n - 1] >= 0 ? 1 : 2;
    }

    private int mytry2(int[] nums) {
        // MLE
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        recursion(nums, memo, 0, n - 1);

        return memo[0][n - 1] >= sum / 2 ? 1 : 2;
    }
    private int recursion(int[] nums, int[][] memo, int start, int end) {
        // System.out.println("now start and end are " + start + ", " + end);
        if (end - start == 1) {
            return Math.max(nums[start], nums[end]);
        }
        // if (start > end) {
        //     return 0;
        // }
        if (memo[start][end] != -1) {
            return memo[start][end];
        }
        memo[start][end] = Math.max(nums[start] + recursion(nums, memo, start + 1, end), nums[end] + recursion(nums, memo, start, end - 1));
        return memo[start][end];
    }

    private int mytry(int[] values) {
        // MLE
        int n = values.length;
        // sum function to record sum from i to j
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    sum[i][j] = values[j];
                } else {
                    sum[i][j] = sum[i][j - 1] + values[j];
                }
            }
        }
        // definition
        int[][] f = new int[n][n];
        // DP
        for (int left = n - 1; left >= 0; left--) {
            for (int right = left; right < n; right++) {
                if (right == left) {
                    f[left][right] = values[left];
                } else {
                    f[left][right] = sum[left][right] - Math.min(f[left + 1][right], f[left][right - 1]);
                }
            }
        }
        // result
        return f[0][n - 1] >= sum[0][n - 1] / 2 ? 1 : 2;
    }
}
