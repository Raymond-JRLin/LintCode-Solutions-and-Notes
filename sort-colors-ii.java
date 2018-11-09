// 143. Sort Colors II
// Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.
//
// Example
// Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
//
// Challenge
// A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?
//
// Notice
// You are not suppose to use the library's sort function for this problem.
//
// k <= n


class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {

        // method1(colors, k);

        method2(colors, k);
    }


    private void method2(int[] colors, int k) {
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
    private void rainbowSort(int[] colors, int left, int right, int from, int to) {
        if (from == to) {
            return;
        }
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = (from + to) / 2;
        while (l <= r) {
            while (l <= r && colors[l] <= pivot) {
                l++;
            }
            while (l <= r && colors[r] > pivot) {
                r--;
            }
            if (l <= r) {
                swap(colors, l, r);
                l++;
                r--;
            }
        }
        // left part
        rainbowSort(colors, left, r, from, pivot);
        // right part
        rainbowSort(colors, l, right, pivot + 1, to);
    }
    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    private void method1(int[] colors, int k) {
        int left = 0;
        int right = colors.length - 1;
        int count = 0;
        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int index = left;
            while (index <= right) {
                if (colors[index] == min) {
                    swap(colors, index, left);
                    index++;
                    left++;
                } else if (colors[index] > min && colors[index] < max) {
                    index++;
                } else {
                    swap(colors, index, right);
                    right--;
                }
            }
            count += 2;
        }
    }
}
