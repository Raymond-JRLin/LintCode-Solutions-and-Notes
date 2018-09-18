// 1567. Maximum Can Exchanged Subarray
// Description
// Given an array a of length n, now you have a chance to choose two numbers a[i], a[j] for exchange, of course you can choose to give up this opportunity.
// So how to choose, can make the maximum sum of subarray in the array is maximum output the sum of the largest subarray.
//
// 3 \leq n \leq 1e33≤n≤1e3
// -1e3 \leq a[i] \leq 1e3−1e3≤a[i]≤1e3
// Have you met this question in a real interview?
// Example
// Given a = [-3,1,2,3,-10,1]，return 7.
//
// Explanation:
// You can choose to swap a[1] and a[4], then the new a array is:
// [-3,-10,2,3,1,1]
// The maximum subarray at this time is [2,3,1,1], and sum is 7
// Given a = [1,1,-100,1,1,-100,-100,-100,1,2,-100]，return 6.
//
// Explanation:
// You can choose to swap a[2] and a[9], then the maximum subarray at this time is:
// [1,1,2,1,1], and sum is 6


public class Solution {
    /**
     * @param a: The array a
     * @return: Return the maximum sum
     */
    public int getAnswer(int[] a) {
        // Write your code here
        if (a == null || a.length == 0) {
            return 0;
        }

        return method1(a);
    }

    private int method1(int[] a) {
        // prefix sum， 但略有不一样， 只记录最大的 prefix sum， 如果前面是负的， 那么就是当前的自己
        int n = a.length;
        int[] left = new int[n]; // maximum prefix sum from left
        int[] lidx = new int[n]; // 记录对应的 prefix sum 起始的位置
        int[] right = new int[n]; // maximum prefix sum from right
        int[] ridx = new int[n]; // 同样记录从右边的位置
        left[0] = a[0];
        ridx[n - 1] = n - 1;
        right[n - 1] = a[n - 1];
        // max prefix sum from left
        for(int i = 1; i < n; i ++) {
            if(left[i - 1] > 0) {
                left[i] = left[i - 1] + a[i];
                lidx[i] = lidx[i - 1];
            } else {
                left[i] = a[i];
                lidx[i] = i;
            }
        }
        // maximum prefix sum from right
        for(int i = n - 2; i >= 0; i --) {
            if(right[i + 1] > 0) {
                right[i] = right[i + 1] + a[i];
                ridx[i] = ridx[i + 1];
            } else {
                right[i] = a[i];
                ridx[i] = i;
            }
        }
        int[] lMax = new int[n]; // 记录从左边开始单个的 a 中的当前最大值
        lMax[0] = a[0];
        int[] rMax = new int[n]; // 从右边开始
        rMax[n - 1] = a[n - 1];
        for(int i = 1; i < n; i ++) {
            if(a[i] > lMax[i - 1]) {
                lMax[i] = a[i];
            } else {
                lMax[i] = lMax[i - 1];
            }
        }
        for(int i = n -2; i >= 0; i --) {
            if(a[i] > rMax[i + 1]) {
                rMax[i] = a[i];
            } else {
                rMax[i] = rMax[i + 1];
            }
        }
        // print(left);
        // print(lidx);
        // print(right);
        // print(ridx);
        // print(lMax);
        // print(rMax);
        int max = Integer.MIN_VALUE; // result
        for(int i = 0; i < n; i ++) {
            max = Math.max(max, left[i] + right[i] - a[i]); // 包含 i 的最大的 subarray
            // 找 i 左边和右边中最大的 a[j]
            int r = ridx[i];
            int l = lidx[i];
            int temp = Integer.MIN_VALUE;
            // 这里还要注意找到了当前 i 下的左右两边最大的值后， 我们要找的是调换过后会变化的值， 所以在 lMax 和 rMax 中减 1， 意味着我们找的是左右两边不相连的 subarray， 因为相连的话已经算在 max 中了， 我们要去找一个不在当前的 subarray 当中的值， 因为已经在的话调换没有意义了
            if(l - 1 >= 0) {
                temp = Math.max(lMax[l - 1], temp);
            }
            if(r + 1 < n) {
                temp = Math.max(rMax[r + 1], temp);
            }
            // 如果存在一个 a[j] = temp 比当前 a[i] 还更大， 我们可以考虑调换， 查看调换之后的 subarray 是否更大一些
            if(temp > a[i]) {
                // 注意减掉两倍的 a[i], 就是左边减掉， 右边也减掉
                max = Math.max(max, left[i] + right[i] - 2 * a[i] + temp);
            }
        }
        // System.out.println(max + ", " + r + ", " + l + ", " + temp + ", " + max);
        return max;

    }
    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
