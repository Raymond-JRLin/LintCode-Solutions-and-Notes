// 1559. 取数求和
// 描述
// 现在你有n个数，保存在arr数组中，你需要选出任意两个数求积，问所有这些可能性的和是多少。返回这个和。返回值对1000000007取模。
//
// 1 \leq n \leq 1000001≤n≤100000
//
// 您在真实的面试中是否遇到过这个题？
// 样例
// Give arr=[1,2,3,4,5] , return 85.
//
// [1,2], 1*2=2
// [1,3], 1*3=3
// [1,4], 1*4=4
// [1,5], 1*5=5
// [2,3], 2*3=6
// [2,4], 2*4=8
// [2,5], 2*5=10
// [3,4], 3*4=12
// [3,5], 3*5=15
// [4,5], 4*5=20
// 2+3+4+5+6+8+10+12+15+20=85
// Give arr=[2,4,4,6,8] , return 220.
//
// [2,4], 2*4=8
// [2,4], 2*4=8
// [2,6], 2*6=12
// [2,8], 2*8=16
// [4,4], 4*4=16
// [4,6], 4*6=24
// [4,8], 4*8=32
// [4,6], 4*6=24
// [4,8], 4*8=32
// [6,8],6*8=48
// 8+8+12+16+16+24+32+24+32+48=220


public class Solution {
    /**
     * @param arr: the arr
     * @return: the sum
     */
    public int takeTheElementAndQueryTheSum(int[] arr) {
        // Write your code here
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // return mytry(arr);

        // return mytry2(arr);

        return method2(arr);
    }

    private int method2(int[] nums) {
        int n = nums.length;
        // 把顺序颠倒一下也是一样， 或者说换一个角度看， 就是正着来算后缀和，e.g.[1,2,3,4,5]: 看 column， 5 相乘的是 1 - 4
        // 1 * 2 + 1 * 3 + 1 * 4 + 1 * 5 = 1 * (2 + 3 + 4 + 5)
        //         2 * 3 + 2 * 4 + 2 * 5 = 2 * (3 + 4 + 5)
        //                 3 * 4 + 3 * 5 = 3 * (4 + 5)
        //                         4 * 5 = 4 * (5)
        //                         => 5 * (1 + 2 + 3 + 4)
        long prefix = 0;
        long sum = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            sum = (sum + nums[i] * prefix % MOD + MOD) % MOD;
            prefix = (prefix + nums[i] % MOD + MOD) % MOD;
        }
        return (int) sum;
    }

    private int mytry2(int[] nums) {
        int n = nums.length;
        // remember: in order not to lose digits, we should use long type, and MOD to each result everytime we get a new one
        // 每个数只和它后面的数之和相乘，所以可以考虑后缀和，e.g.[1,2,3,4,5]:
        // 1 * 2 + 1 * 3 + 1 * 4 + 1 * 5 = 1 * (2 + 3 + 4 + 5)
        //         2 * 3 + 2 * 4 + 2 * 5 = 2 * (3 + 4 + 5)
        //                 3 * 4 + 3 * 5 = 3 * (4 + 5)
        //                         4 * 5 = 4 * (5)
        // 可以从后面倒着算起
        long prefix = nums[n - 1];
        long sum = 0;
        final int MOD = 1000000007;
        for (int i = n - 2; i >= 0; i--) {
            sum = (sum + nums[i] * prefix % MOD + MOD) % MOD;
            prefix = (prefix + nums[i] % MOD + MOD) % MOD;
        }
        return (int) sum;
    }

    private int mytry(int[] nums) {
        // brute force: TLE
        int n = nums.length;
        int sum = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                sum = (sum + nums[i] * nums[j] + MOD) % MOD;
            }
        }
        return sum;
    }
}


// 考点
//
// 后缀和
//
// 题解
//
// 可以发现，每个数都需要乘上它后面的数就遍历了所有的方案，所以可以先对所有的数求出后缀和，然后乘上自己即可。最后累加起来，复杂度为O(n)。
//
// answer:

public class Solution {
    /**
     * @param arr: the arr
     * @return: the sum
     */
    public int takeTheElementAndQueryTheSum (int[] arr) {
        // Write your code here
        int mod = 1000000007, i, j;
        long sum = 0, ans = 0;
        int n = arr.length;
        for (i = 0; i < n; i++) {
            ans += (sum * arr[i]) % mod;
            ans %= mod;
            sum += arr[i];
            sum %= mod;
        }
        return (int) ans;
    }
}
