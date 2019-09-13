// 971. Surplus Value Backpack
// 中文English
// There is a backpack with a capacity of c.
// There are n Class A items, the volume of the i th Class A item is a[i], and the value of the item is the remaining capacity of the backpack after loading the item * k1.
// There are m Class B items, the volume of the i th Class B item is b[i], and the value of the item is the remaining capacity of the backpack after loading the item * k2.
// Find the maximum value can be obtained.
//
// Example
// Example 1:
//
// Given k1 = `3`,k2 = `2`,c = ` 7`,n = `2`,m = `3`,a = `[4,3]`,b = `[1,3,2]`，return `23`.
// Input:
// 3 2 7 2 3
// [4,3]
// [1,3,2]
// Output:
// 23
//
// Explanation:
// 2 * (7-1)+2*(6-2) + 3 * (4-3) = 23
// Example 2:
//
// Given k1 = `1`,k2 = `2`,c = ` 5`,n = `1`,m = `1`,a = `[2]`,b = `[1]`，return `10`.
// Input:
// 1 2 5 1 1
// [2]
// [1]
// Output:
// 10
//
// Explanation:
// 2 * (5-1)+1*(4-2) = 10
// Notice
// 1 <= k1, k2, c, a[i], b[i] <= 10^7
// 1 <= n, m <= 1000


public class Solution {
    /**
     * @param k1: The coefficient of A
     * @param k2: The  coefficient of B
     * @param c: The volume of backpack
     * @param n: The amount of A
     * @param m: The amount of B
     * @param a: The volume of A
     * @param b: The volume of B
     * @return: Return the max value you can get
     */
    public long getMaxValue(int k1, int k2, int c, int n, int m, int[] a, int[] b) {
        // Write your code here

        return method1(k1, k2, c, n, m, a, b);
    }

    public long method1(int k1, int k2, int c, int n, int m, int[] a, int[] b) {
        // 这么思考： 对于 a 来说， 就是如果只放入 a 的东西的话， 那么一定是按照东西的大小从小到大放， 对于只放 b 的话， 也是如此, 那么这是 greed。 那么既放 a 又放 b 的时候， 不管两个 k 相对大小， 也是从小到大放， 只不过这个时候有个选择， 是放 a 的小的还是 b 的小的， 那就要用到 k 了。 所以在这里总是拿 a 和 b 中小的， 那么在排序之后， 我们就可以选择前 j 个， 因此我们可以用 DP
        Arrays.sort(a);
        Arrays.sort(b);
        // 预处理 prefix sum， 就可以快速拿到前几个放入的总大小
        long[] prefixA = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixA[i] = prefixA[i - 1] + a[i - 1];
        }
        long[] prefixB = new long[m + 1];
        for (int i = 1; i < m + 1; i++) {
            prefixB[i] = prefixB[i - 1] + b[i - 1];
        }
        long result = 0L;
        // definition
        long[][] f = new long[n + 1][m + 1];
        // initialization
        f[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            // don't not put b
            // don't forget to add values got in last time
            if (prefixA[i] > c) {
                break;
            }
            f[i][0] = f[i - 1][0] + (c - prefixA[i]) * k1;
            result = Math.max(result, f[i][0]);
        }
        for (int j = 1; j < m + 1; j++) {
            // don't not put a
            if (prefixB[j] > c) {
                break;
            }
            f[0][j] = f[0][j - 1] + (c - prefixB[j]) * k2;
            result = Math.max(result, f[0][j]);
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (prefixA[i] + prefixB[j] > c) {
                    break;
                }
                long getA = f[i - 1][j] + (c - prefixA[i] - prefixB[j]) * k1;
                long getB = f[i][j - 1] + (c - prefixB[j] - prefixA[i]) * k2;
                f[i][j] = Math.max(getA, getB);
                result = Math.max(result, f[i][j]);
            }
        }
        // result
        return result;
    }
}
