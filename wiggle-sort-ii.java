// 507. Wiggle Sort II
// 中文English
// Given an unsorted array nums, reorder it such that
//
// nums[0] < nums[1] > nums[2] < nums[3]....
// Example
// Example 1
//
// Input: nums = [1, 5, 1, 1, 6, 4]
// Output: [1, 4, 1, 5, 1, 6]
// Example 2
//
// Input: nums = [1, 3, 2, 2, 3, 1]
// Output: [2, 3, 1, 3, 1, 2]
// Challenge
// Can you do it in O(n) time and/or in-place with O(1) extra space?
//
// Notice
// You may assume all input has valid answer.
// You just need to give one vaild answer.


public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */

    int n;
    public void wiggleSort(int[] nums) {
        // Write your code here
        if (nums == null || nums.length < 2) {
            return;
        }
        n = nums.length;
        // because there would be duplicates, we cannot use Arrays.sort() and swap neighbors, for example: [1, 3, 2, 2, 3, 1]
        // but if we want pick one smaller and one bigger, we should use another strategy, instead of picking 2 from head and tail and move opposite, we cut half of the sorted array, that is, pick 0, median, 1, median + 1, 2, median + 2, ... but it would be wrong either, e.g. [4, 5, 5, 6]

        // method 1: O(nlogn) time and O(n) space
        // so we should do: n / 2, n, n / 2 - 1, n - 1, ..., 0, median + 1, which means [<------|<------], 要让中间远离，一边中间出发，另一边最后到达中间, i.e. [1 2 3 4 5 6] => [3 6 2 5 1 4] (number means index)
        // cannot [---->|---->], because we should let values around median away from each other when array is short
        // Arrays.sort(nums);
        // int[] copy = Arrays.copyOf(nums, n);
        // int j;
        // if (n % 2 == 0) {
        //     j = n / 2 - 1;
        // } else {
        //     j = n / 2;
        // }
        // int k = n - 1;
        // for (int i = 0; i < n; i++) {
        //     if (i % 2 == 0) {
        //         nums[i] = copy[j];
        //         j--;
        //     } else {
        //         nums[i] = copy[k];
        //         k--;
        //     }
        // }

        // method 2: answer use O(n) time and O(n) place

        // method 3: if we should do in O(n) time and/or in-place O(1) space, there is only one possible strategy to do: quick select (3 way partition) and a trick:
        // [0 1 2 3 4 5] => [1 3 5 0 2 4]
        // references:
        // https://discuss.leetcode.com/topic/32929/o-n-o-1-after-median-virtual-indexing
        // https://segmentfault.com/a/1190000008229335
        n = nums.length;
        int mid = quickSelect(nums, 0, nums.length - 1, nums.length / 2);
        partition(nums, 0, nums.length - 1, mid);
    }
    private void partition(int[] nums, int l, int r, int mid) {
        int i = l;
        while (i <= r) {
            if (nums[mapping(i)] > mid) {
                swap(nums, mapping(i++), mapping(l++));
            } else if (nums[mapping(i)] < mid) {
                swap(nums, mapping(i), mapping(r--));
            } else {
                i++;
            }
        }
    }
    private int mapping(int i) {
        return (2 * i + 1) % (n | 1); // (n | 1) calculates the nearest odd that is not less than n
    }
    private int quickSelect(int[] nums, int l, int r, int k) {
        // 通过这个 quick select, 可以以 O(n) time + O(1) space 完成中间是 median， 左边比 median 小， 右边比 median 大， 并返回 median 值
        if (l >= r) {
            return nums[l];
        }
        int pivot = nums[r];
        int index = l;
        for (int i = l; i < r; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, index++);
            }
        }
        // swap the pivot to the correct position
        swap(nums, index, r);
        if (index == k) {
            return nums[index];
        } else if (index < k) {
            return quickSelect(nums, index + 1, r, k);
        } else  {
            return quickSelect(nums, l, index - 1, k);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
