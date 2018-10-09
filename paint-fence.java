// 514. Paint Fence
// Description
// There is a fence with n posts, each post can be painted with one of the k colors.
// You have to paint all the posts such that no more than two adjacent fence posts have the same color.
// Return the total number of ways you can paint the fence.
//
// n and k are non-negative integers.
//
// Have you met this question in a real interview?
// Example
// Given n=3, k=2 return 6
//
//       post 1,   post 2, post 3
// way1    0         0       1
// way2    0         1       0
// way3    0         1       1
// way4    1         0       0
// way5    1         0       1
// way6    1         1       0


public class Solution {
    /**
     * @param n non-negative integer, n posts
     * @param k non-negative integer, k colors
     * @return an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        // Write your code here
        // 不要对颜色 k 去做 edge case 的判断， 因为情况很多， 当 fence 很多的时候， k 只要超过 2 就可以了， 而 n 比较小的时候， k 不一定
        if (n == 0) {
            // no fence needs to be painted
            return 0;
        }
        if (n == 1) {
            // if we only have 1 fence, we can paint as much as colors we have
            return k;
        }

        // return method1(n, k);

        // return method2(n, k);

        return method3(n, k);
    }

    private int method3(int n, int k) {
        // method 2: DP with 2 variables
        // reference: http://www.cnblogs.com/grandyang/p/5231220.html
        // we define same and diff as the way of painting same or different with/from previous one at current position
        int same = 0; // start form the 1st
        int diff = k;
        for (int i = 2; i <= n; i++) {
            int temp = diff;
            diff = same * (k - 1) + diff * (k - 1); // 涂不相同的， 前一个相同或者不相同的可以
            same = temp; // 涂相同， 就是前一个涂不同的方法的次数
        }
        return same + diff;
    }

    private int method2(int n, int k) {
        // method 1 - 2:
        // 也可以用 size == 3 的滚动数组来减少空间使用， reference: 参考答案 和 referenc: https://zhengyang2015.gitbooks.io/lintcode/paint_fence_514.html
        if (n == 2) {
            return k * k;
        }
        // definition
        int[] f = new int[3];
        // initialization
        f[0] = k;
        f[1] = k * k;
        // DP
        for (int i = 3; i <= n; i++) {
            f[2] = f[0] * (k - 1) + f[1] * (k - 1);
            f[0] = f[1];
            f[1] = f[2];
        }
        // result
        return f[2];
    }

    private int method1(int n, int k) {
        // DP with array
        // definition: n-th 涂的总方法数
        int[] f = new int[n + 1];
        // initialization
        f[0] = 0;
        f[1] = k; // 1st fence, we can paint by k ways
        f[2] = k * k; // 2nd fence can be painted by same color
        // DP: no more than 2 adjacent fence 应该理解成不能有连续的超过两根 fence 被涂成相同的颜色， 所以第三个要么和第一个不同颜色， 要么和第二个不同颜色
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] * (k - 1) + f[i - 2] * (k - 1);
        }
        // result
        return f[n];
    }
}
