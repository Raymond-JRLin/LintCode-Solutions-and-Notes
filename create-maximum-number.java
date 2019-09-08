// 552. Create Maximum Number
// 中文English
// Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.
//
// Example
// Example 1:
//
// Input：nums1 = [3, 4, 6, 5]， nums2 = [9, 1, 2, 5, 8, 3]，k = 5
// Output：[9, 8, 6, 5, 3]
// Explanation：
// select [9,8,3] from the first array and select[6,5] from the second array
// Example 2:
//
// Input：nums1 = [6, 7]， nums2 = [6, 0, 4]，k = 5
// Output：[6, 7, 6, 0, 4]
// Explanation：
// select [6,7] from the first array and select[6,0,4] from the second array
// Example 3:
//
// Input：nums1 = [3, 9]， nums2 = [8, 9]，k = 3
// Output：[9, 8, 9]
// Explanation：
// select [9] from the first array and select[8, 9] from the second array


public class Solution {
    /**
     * @param nums1 an integer array of length m with digits 0-9
     * @param nums2 an integer array of length n with digits 0-9
     * @param k an integer and k <= m + n
     * @return an integer array
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // Write your code here
        if (k < 1) {
            return new int[0];
        }
        int len1 = nums1.length;
        int len2 = nums2.length;

        // 分三步来考虑：
        // 1：从nums1里取i个元素组成最大数组，从nums2里取k-i个元素组成最大数组。
        // 2：合并之前结果，得到一个长度为k的最大数组。
        // 3：对于不同长度分配的情况，比较每次得到的长度为k的最大数组，返回最大的一个。
        // 三个不同的函数分别用于取，合并，比较。

        int[] result = new int[k];
        if (len1 + len2 < k) {
            return null;
        }
        if (len1 + len2 == k) {
            return merge(nums1, nums2, k);
        }
        if (len1 + len2 > k) {
            // 注意有可能其中一个数组不足 k 个
            int max = len1 > k ? k : len1;
            int min = len2 > k ? 0 : k - len2;
            for (int i = min; i <= max; i++) {
                int[] arr1 = maxOneArray(nums1, i);
                int[] arr2 = maxOneArray(nums2, k - i);
                int[] temp = merge(arr1, arr2, k);
                if (isLarger(temp, 0, result, 0)) {
                    result = temp;
                }
            }
        }
        return result;
    }
    // nums 中取 k 个组成最大的数组
    private int[] maxOneArray(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int n = nums.length;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (n - i + stack.size() > k && stack.size() > 0 && stack.peek() < nums[i]) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }
        int[] result = new int[k];
        int index = k - 1;
        while (!stack.isEmpty()) {
            result[index] = stack.pop();
            index--;
        }

        // 栈的长度固定为 k， 所以可以用数组来模拟栈
        // int i = 0; // pointer in result array
        // int[] result = new int[k];
        // for (int j = 0; j < n; j++) {
        //     while (n - j + i > k && i > 0 && result[i - 1] < nums[j]) {
        //         i--;
        //     }
        //     if (i < k) {
        //         result[i] = nums[j];
        //         i++;
        //     }
        // }
        return result;
    }
    // 合并两个数组
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        if (k == 0) {
            return result;
        }
        int i = 0;
        int j = 0;
        // int index = 0;
        // while (index < k && i < nums1.length && j < nums2.length) {
        //     if (nums1[i] >= nums2[j]) {
        //         result[index++] = nums1[i++];
        //     } else {
        //         result[index++] = nums2[j++];
        //     }
        // }
        // while (index < k && i < nums1.length) {
        //     result[index++] = nums1[i++];
        // }
        // while (index < k && j < nums2.length) {
        //     result[index++] = nums2[j++];
        // }

        // 可以用老方法， 也可以调用数组比较的方法
        for (int index = 0; index < k; index++) {
            if (isLarger(nums1, i, nums2, j)) {
                result[index] = nums1[i++];
            } else {
                result[index] = nums2[j++];
            }
        }
        return result;
    }
    // 比较两个数组从 i j 开始哪个更大
    // true if temp > result
    private boolean isLarger(int[] temp, int i, int[] result, int j) {
        // int i = 0;
        // int j = 0;
        while (i < temp.length && j < result.length) {
            if (temp[i] > result[j]) {
                return true;
            }
            if (temp[i] < result[j]) {
                return false;
            }
            i++;
            j++;
        }
        return i != temp.length;
    }
}
