// 1557. Array Maximum Value
// Description
// Given an array a containing n positive integers, there is another array b of length n, indicating that the value of the i positive integer is b[i]. We can choose any non-intersecting interval [i, j], which needs to satisfy i < j and a[i] = a[j], then we can get all the numbers' value in intervals [i, j] , that the value of b[i] + b[i+1] + ... + b[j].
// Output the maximum value of the array you can get.
//
// 2 \leq n \leq 1e52≤n≤1e5
// 1 \leq a[i], b[i] \leq 1e31≤a[i],b[i]≤1e3
// Have you met this question in a real interview?
// Example
// Given a = [1,2,3,4,5,6], b = [1,1,1,1,1,1]，return 0.
//
// Explanation:
// Because there are no equal numbers in the array a, no interval can be selected.
// The maximum value that can be obtained is 0
// Given a = [4,2,2,1,2,1], b = [1,2,1,2,1,100]，return 106.
//
// Explanation:
// Because all the intervals selected can't intersect, we should choose the interval:
// [1,2] ,[3,5]
// a[1] = a[2] = 2
// a[3] = a[5] = 1
// The values that can be obtained at this time are:
// b [1] + b [2] + b [3] + b [4] + b [5] = 106


public class Solution {
    /**
     * @param a: The array a
     * @param b: The array b
     * @return: Return the maximum value
     */
    public int getAnswer(int[] a, int[] b) {
        // Write your code here
        if (a == null || a.length == 0) {
            return 0;
        }

        return method1(a, b);
    }

    // 定义数组 dp[i] 表示 前i个数构成的子数组能够获得的最大价值。 则 dp[n] 即为我们需要输出的答案。

    // 结合题意，求解dp[i]时需要考虑两种情况：

    //     没有任何区间包含a[i]。那么b[i]最终不计入最大价值中，有 dp[i] = dp[i - 1]
    //     存在一个区间[k , i] , 满足 k < i 且 a[k] = a[i]， 那么 dp[i] = max{dp[k-1] + (a[k] + a[k+1] + .. + a[i])}
    // 如果处理一个前缀和数组， 记为sum，那么第二种情况的dp方程可以转化为：

    // dp[i] = max{dp[k-1] + sum[i] - sum[k-1]} = sum[i] + max{dp[k-1] - sum[k-1]}

    // 故如果在 i 前面存在多个 k 满足 a[i] = a[k]， 我们只需要找到最大的 dp[k-1] - sum[k-1] 来转移dp方程。
    // 这个可以使用一个新数组Mx来维护，即对于每一个出现的数a[k]，我们都维护一下： Mx[a[k]] = max{dp[k-1] - sum[k-1]}

    // 那么遍历到a[i]时，只需要根据 Mx[a[i]]的值来转移即可。

    // 时间复杂度: O(n)

    private int method1(int[] a, int[] b) {
        int n = a.length;
        // prefix sum of b values
        int[] prefix = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i - 1] + b[i - 1];
        }
        int[] result = new int[n + 1]; // 前 i 个数构成的子数组能够获得的最大价值
        int[] max = new int[1001]; // index is a[i], max[i] =  max{result[k - 1] - prefix[k -1 ]}
        boolean[] visited = new boolean[1001]; // if there's a[i], index is a[i]
        for (int i = 1; i < n + 1; i++) {
            if (visited[a[i - 1]]) {
                // 前面出现过相同的数
                // 这里注意 result 中的 i 从 1 开始， 这样就和 prefix 的 i 的定义一样了
                result[i] = Math.max(result[i - 1], prefix[i] + max[a[i - 1]]);
                max[a[i - 1]] = Math.max(max[a[i - 1]], result[i - 1] - prefix[i - 1]);
            } else {
                result[i] = result[i - 1];
                visited[a[i - 1]] = true;
                max[a[i - 1]] = result[i - 1] - prefix[i - 1];
            }
        }
        return result[n];
    }
}


// answer:

定义数组 dp[i] 表示 前i个数构成的子数组能够获得的最大价值。 则 dp[n] 即为我们需要输出的答案。

结合题意，求解dp[i]时需要考虑两种情况：

没有任何区间包含a[i]。那么b[i]最终不计入最大价值中，有 dp[i] = dp[i - 1]
存在一个区间[k , i] , 满足 k < i 且 a[k] = a[i]， 那么 dp[i] = max{dp[k-1] + (a[k] + a[k+1] + .. + a[i])}
如果处理一个前缀和数组， 记为sum，那么第二种情况的dp方程可以转化为：

dp[i] = max{dp[k-1] + sum[i] - sum[k-1]} = sum[i] + max{dp[k-1] - sum[k-1]}

故如果在 i 前面存在多个 k 满足 a[i] = a[k]， 我们只需要找到最大的 dp[k-1] - sum[k-1] 来转移dp方程。
这个可以使用一个新数组Mx来维护，即对于每一个出现的数a[k]，我们都维护一下： Mx[a[k]] = max{dp[k-1] - sum[k-1]}

那么遍历到a[i]时，只需要根据 Mx[a[i]]的值来转移即可。

时间复杂度: O(n)


public class Solution {
    /**
     * @param a: The array a
     * @param b: The array b
     * @return: Return the maximum value
     */
    public int getAnswer(int[] a, int[] b) {
        // Write your code here
        int dp[] = new int[100010];
        int pre[] = new int[100010];
        int Mx[] = new int[1010];
        boolean flag[] = new boolean[1010];
        pre[0] = 0;
        for (int i = 1; i <= a.length; i++) {
            pre[i] = pre[i - 1] + b[i - 1];
        }
        dp[0] = 0;
        for (int i = 1; i <= a.length; i++) {
            if (!flag[a[i - 1]]) {
                flag[a[i - 1]] = true;
                dp[i] = dp[i - 1];
                Mx[a[i - 1]] = dp[i - 1] - pre[i - 1];
            } else {
                dp[i] = max(dp[i - 1], Mx[a[i - 1]] + pre[i]);
                Mx[a[i - 1]] = max(Mx[a[i - 1]], dp[i - 1] - pre[i - 1]);
            }
        }
        return dp[a.length];
    }

    int max(int a, int b) {
        if (a > b) return a;
        return b;
    }
}
