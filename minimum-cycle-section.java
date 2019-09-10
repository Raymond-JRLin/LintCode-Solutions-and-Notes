// 1365. Minimum Cycle Section
// 中文English
// Given an array of int, find the length of the minimum cycle section.
//
// Example
// Example 1
//
// Input: [1,2,1,2,1,2]
// Output: 2
// Explanation:
// The minimum cycle section is [1,2]，and the length is 2.
// Example 2
//
// Input: [1,2,1,2,1]
// Output: 2
// Explanation:
// The minimum cycle section is [1,2]，and the length is 2, although the last 2 is not given, we still consider the cycle section is [1,2].
// Example 3
//
// Input: [1,2,1,2,1,4]
// Output: 6
// Explanation:
// The minimum cycle section is [1,2,1,2,1,4], and the length is 6.
// Notice
// The length of array do not exceed 100000。
// Each element is in the int range 。


public class Solution {
    /**
     * @param array: an integer array
     * @return: the length of the minimum cycle section
     */
    public int minimumCycleSection(int[] array) {
        // Write your code here

        // return method0(array);

        return method1(array);
    }

    private int method1(int[] array) {
        // 可以考虑使用双指针 (L, R)， 并开一个变量记录 len 当前的循环节， 由于循环节肯定是从第一位开始， 所以用 L 记录当前匹配到的位置， R 一直往后移动， 如果当前位不匹配， 那么 L 就重置， len 就加 1, 这里的操作和 kmp 算法求 next一样， 循环节就是 (i - 1) - (next[i] - 1) = i - next[i]。整体复杂度 O(n) 。
        // 也没有很理解
        int [] next = new int[array.length + 1];
        int left = -1;
        int right = 0;
        next[0] = -1;
        while(right < array.length) {
            if(left == -1 || array[right] == array[left]) {
                right++;
                left++;
                next[right] = left;
            } else {
                left = next[left]; // put left back to starting
            }
        }
        // for (int num : next) {
        //     System.out.print(num + " ");
        // }
        return right - next[right];
    }

    private int method0(int[] nums) {
        // wrong, e.g. [1,1,1,3,2,1,1,1,1,3,2,1]
        int result = 1;
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] == nums[right]) {
                left++;
                right++;
            } else {
                left = 0; // move left back to 0
                result = right; // udpate length of cycle
                if (nums[left] != nums[right]) {
                    right++;
                    result = right;
                }
            }
        }
        return result;
    }
}
