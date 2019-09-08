// 843. Digital Flip
// 中文English
// Given an array of 01. You can flip 1 to be 0 or flip 0 to be 1.
//
// Find the minimum number of flipping steps so that the array meets the following rules:
//
// The back of 1 can be either1 or 0, but0 must be followed by 0.
//
// Example
// Example 1:
//
// Input: [1,0,0,1,1,1]
// Output: 2
// Explanation: Turn two 0s into 1s.
// Example 2:
//
// Input: [1,0,1,0,1,0]
// Output: 2
// Explanation: Turn the second 1 and the third 1 into 0.
// Notice
// The length of the given array n <= 100000.
//


public class Solution {
    /**
     * @param nums: the array
     * @return: the minimum times to flip digit
     */
    public int flipDigit(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums);

        // return method1(nums);

        // return method2(nums);

        // return method3(nums);

        return method4(nums);
    }

    private int method4(int[] nums) {
        // statistics/math: acoording to the requirements, 1 can be followed by 0 or 1, but 0 can be only followed by 0. So after flipping, the order must be like 111...1000..00, which mean front part should only have 1s, and latter part should only have 0s (of course, there's edge case like all 1s or all 0s). So we need to count the best case that how many 0s should be flipped to 1s in front and how many 1s should be flipped to 0s in latter part
        int n = nums.length;
        int total = 0; // count how many 1s totally
        for (int num : nums) {
            if (num == 1) {
                total++;
            }
        }
        int ones = 0; // count how many 1s until current i
        int result = Math.min(total, n - total); // worse case: flip all 1s to 0s, or all 0s to 1s
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                ones++;
            }
            // i - ones: # 0s needs to be flip to 1s in front part
            // total - ones: # 1s need to be flip to 0 in latter
            result = Math.min(result, i - ones + 1 + total - ones);
        }
        return result;
    }

    private int method3(int[] nums) {
        // 另一种方法是从前往后做 DP
        int n = nums.length;
        // definition/initialization: zero / one 表示把当前的数翻成 0 / 1 的从头到 i 所需要的翻转次数
        int zero = nums[0] == 0 ? 0 : 1;
        int one = nums[0] == 1 ? 0 : 1;
        // System.out.println("initialization with one: " + one + ", zero: " + zero);
        // DP
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                // System.out.println("it's one: ");
                // 当前是 0
                // 如果要 flip 成 0， 当前不需要动， 0 之前可以是 0 也可以是 1， 所以取小的那个
                zero = Math.min(zero, one); // do this first without updating one
                // 如果要把当前的 0 flip 成 1， 所以要有 + 1， 因为 1 之前只能是 1， 所以取开始到 i 到翻转成 1 的次数 + 1
                one = one + 1;
                // System.out.println("and change one: " + one + ", zero: " + zero);
            } else {
                // System.out.println("it's one: ");
                // 当前是 1
                // 如果要 flip 成 0， 当前需要 + 1， 0 之前可以是 0 也可以是 1， 所以就等于开始到 i 到翻转成 0 的次数 + 1
                zero = Math.min(zero, one) + 1;
                // 当前的 1 不要动， 1 之前只能是 1， 所以不变
                // System.out.println("and change one: " + one + ", zero: " + zero);
            }
        }
        // result
        return Math.min(zero, one);
    }

    private int method2(int[] nums) {
        // decrease the space complextiy by using 2 variables to do DP
        // O(N) time and O(1) space
        int n = nums.length;
        int zero = nums[n - 1] == 0 ? 0 : 1;
        int one = nums[n - 1] == 1 ? 0 : 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                one = Math.min(zero, one) + 1;
            } else {
                one = Math.min(zero, one); // we should this first without updating zero
                zero = zero + 1;
            }
        }
        return Math.min(zero, one);
    }

    private int method1(int[] nums) {
        // DP: O(N) time and space
        int n = nums.length;
        // definition: f[i][0]/f[i][1] = from i to end, how many flips we should do if we change i-th char to 0/1, e.g. 把当前的数翻成 0 ／ 1 的从 i 到结束一共要有的翻转次数
        int[][] f = new int[n][2];
        // initialization
        f[n - 1][0] = nums[n - 1] == 0 ? 0 : 1;
        f[n - 1][1] = nums[n - 1] == 1 ? 0 : 1;
        // DP
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                // 当前是 0
                // 如果要 flip 成 0， 当前不需要动， 0 之后只能有 0， 所以就等于 i + 1 开始到末位要 flip 成 0 的次数
                f[i][0] = f[i + 1][0];
                // 如果要把当前的 0 flip 成 1， 所以要有 + 1， 因为 1 之后可以是 1 也可以是 0， 所以取 i + 1 到结束较少的 flip 次数
                f[i][1] = Math.min(f[i + 1][0], f[i + 1][1]) + 1;
            } else {
                // 当前是 1
                // 如果要 flip 成 0， 当前需要 + 1， 0 之后只能有 0， 所以就等于 i + 1 开始到末位要 flip 成 0 的次数再加 1
                f[i][0] = f[i + 1][0] + 1;
                // 当前的 1 不要动， 1 之后可以是 1 也可以是 0， 所以取 i + 1 到结束较少的 flip 次数
                f[i][1] = Math.min(f[i + 1][0], f[i + 1][1]);
            }
        }
        // result
        return Math.min(f[0][0], f[0][1]);
    }

    /* wrong at: [0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,0,0,1,1,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1,0,1,0,1,1,1,1,0,0,0,1,0,0,0], you may change some 0 to 1 and some 1 to 0
    private int mytry(int[] nums) {
        int n = nums.length;
        int first_zero = n;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                first_zero = i;
                break;
            }
        }
        if (first_zero == n || first_zero == n - 1) {
            return 0;
        }
        int last_zero = n;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                last_zero = i;
                break;
            }
        }
        if (first_zero == last_zero) {
            return 1;
        }
        if (last_zero == n - 1) {
            last_zero--;
        }

        int zeros = 0;
        int ones = 0;
        for (int i = first_zero; i < n; i++) {
            if (nums[i] == 1) {
                ones++;
            }
            if (i <= last_zero && nums[i] == 0) {
                zeros++;
            }
        }

        return Math.min(zeros, ones);
    }*/
}
