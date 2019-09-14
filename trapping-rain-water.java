// 363. Trapping Rain Water
// 中文English
// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
// Trapping Rain Water
//
// Example
// Example 1:
//
// Input: [0,1,0]
// Output: 0
// Example 2:
//
// Input: [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Challenge
// O(n) time and O(1) memory
//
// O(n) time and O(n) memory is also acceptable.
//

public class Solution {
    /*
     * @param : a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        if (heights == null || heights.length <= 1) {
            return 0;
        }
        int n = heights.length;
        int result = 0;

        // method 1: based on forward-backward traversal, O(n) time and O(n) space
        // reference: http://fisherlei.blogspot.com/2013/01/leetcode-trapping-rain-water.html
        // http://bangbingsyb.blogspot.com/2014/11/leetcode-trapping-rain-water.html

        // forward traversal to calculate max heights in left of current index
        // int[] leftMax = new int[n];
        // leftMax[0] = heights[0];
        // for (int i = 1; i < n; i++) {
        //     leftMax[i] = Math.max(heights[i], leftMax[i - 1]);
        // }
        // // backward traversal to calculate max heights in right of current index
        // int[] rightMax = new int[n];
        // rightMax[n - 1] = heights[n - 1];
        // for (int i = n - 2; i >= 0; i--) {
        //     rightMax[i] = Math.max(heights[i], rightMax[i + 1]);
        // }
        // // of course we can combine 2nd and 3rd for loop
        // for (int i = 1; i < n; i++) {
        //     int minHeight = Math.min(leftMax[i], rightMax[i]);
        //     if (heights[i] < minHeight) {
        //         result += minHeight - heights[i];
        //     }
        // }
        // return result;

        // method 1 - 2: we can also use just one array to record max height
        // int[] maxHeight = new int[n];
        // maxHeight[0] = heights[0];
        // // get max height from left
        // for (int i = 1; i < n; i++) {
        //     maxHeight[i] = Math.max(maxHeight[i - 1], heights[i]);
        // }
        // int max = heights[n - 1];
        // for (int i = n - 2; i >= 0; i--) {
        //     // get the min max height comparing left and right
        //     maxHeight[i] = Math.min(maxHeight[i], max);
        //     // update max height from right
        //     max = Math.max(max, heights[i]);
        //     // get the result
        //     result += Math.max(maxHeight[i] - heights[i], 0);
        // }
        // return result;

        // method 2: based on 2 pointers, O(n) time and O(1) space
        // idea: 用双指针一前一后往中间扫，要 O(1) space， 就得只用 2 variables 记下数据；那么要记的是在往中间扫的过程中左边更高的和右边更高的， 可以这么理解， 能够装水的是左右两边高起来的地方， 如果左边比右边高， 那么右边往中间走， 若是走到比右边刚刚的位置低， 那么可以装水； 如果右边比左边高， 那么左边往中间走， 若是走到比左边刚刚的位置低， 那么可以装水； 所以就是不断以凹的形状向中间夹逼
        int left = 0;
        int right = n - 1;
        int leftMax = heights[left];
        int rightMax = heights[right];
        while (left < right) {
            if (leftMax < rightMax) {
                // 右边更高， 所以左边向中间走
                left++;
                if (heights[left] < leftMax) {
                    // 走到了更低的地方， 因为此时右边更高， 所以不论中间怎样， 这个坑一定能够装水
                    result += leftMax - heights[left];
                } else {
                    // 走到了更高或者平级的地方， 那么更新一下最大值
                    leftMax = heights[left];
                }
            } else {
                // 左边更高， 那么右边向中间走
                right--;
                if (heights[right] < rightMax) {
                    // 走到了更低的地方， 因为此时左边更高， 所以不论中间怎样， 这个坑一定能够装水
                    result += rightMax - heights[right];
                } else {
                    // 走到了更高或者平级的地方， 那么更新一下最大值
                    rightMax = heights[right];
                }
            }
        }
        return result;
    }
};
