// 898. Leftmost One

// Given a 2D array, and each line has only 0 and 1, the front part is 0, and the latter part is 1. Find the number of columns in the leftmost 1 of all the rows in the array.
//
// The number of rows in the array and the number of columns do not exceed 1000
// In order to limit the time complexity, your program will run 50000 times

// Example
// Given arr = [[0,0,0,1],[1,1,1,1]], return 0.
//
// Explanation:
// Arr[1][0] is the leftmost 1 in all rows and its column is 0.
// Given arr = [[0,0,0,1],[0,1,1,1]], return 1.
//
// Explanation:
// Arr[1][1] is the leftmost 1 in all rows and its column is 1.


public class Solution {
    /**
     * @param arr: The 2-dimension array
     * @return: Return the column the leftmost one is located
     */
    public int getColumn(int[][] arr) {
        // Write your code here
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return -1;
        }

        // return brute(arr);

        // return mytry(arr);

        return method3(arr);
    }

    private int method3(int[][] nums) {
        // the front part is 0, and the latter part is 1. => it's a very important condition, so if we found the index of leftmost 1 in a line, and then we can check if we should check next line by comparing the value in the same column
        // O(max{N, M} + logM)
        int n = nums.length;
        int m = nums[0].length;
        int index = bs(nums[0]); // found the index of leftmost 1 in the 1st line
        for (int i = 1; i < n; i++) {
            if (nums[i][index] == 0) {
                // it's 0, which means the leftmost 1 should be right of index
                continue;
            }
            while (index > 0 && nums[i][index - 1] == 1) {
                index--;
            }
            if (index == 0) {
                return index;
            }
        }
        return index;
    }

    private int mytry(int[][] nums) {
        // O(N * logM)
        int n = nums.length;
        int m = nums[0].length;
        int result = m;
        for (int i = 0; i < n; i++) {
            if (nums[i][0] == 1) {
                return 0;
            }
            if (nums[i][m - 1] == 0) {
                continue;
            }
            int index = bs(nums[i]);
            result = Math.min(result, index);
        }
        return result;
    }
    private int bs(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == 1) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == 1) {
            return start;
        } else if (nums[end] == 1) {
            return end;
        } else {
            return nums.length;
        }
    }

    private int brute(int[][] nums) {
        // O(N * M)
        int n = nums.length;
        int m = nums[0].length;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (nums[i][j] == 1) {
                    return j;
                }
            }
        }
        return -1;
    }
}
