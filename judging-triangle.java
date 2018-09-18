// 1487. Judging Triangle
// Description
// Given an array arr, ask if you can find 3 elements from the array as the sides of the three sides, so that the three sides can form a triangle. If yes, return yes, if not, return no
//
// 1 \leq n \leq 1000001≤n≤100000
// 1 \leq arr[i] \leq 10000000001≤arr[i]≤1000000000
// The program will be run 500 times
// Have you met this question in a real interview?
// Example
// Give arr=[2,3,5,8], return no.
//
// Explanation:
// 2, 3, 5 cannot form a triangle
// 2, 3, 8 cannot form a triangle
// 3, 5, 8 cannot form a triangle
// So, return "no"
// Give arr=[3,4,5,8] , return yes.
//
// Explanation:
// 3, 4, 5 can form a triangle
// So return "yes"


public class Solution {
    /**
     * @param arr: The array
     * @return: yes or no
     */
    public String judgingTriangle(int[] arr) {
        // Write your code here
        if (arr == null || arr.length == 0) {
            return "no";
        }

        // return mytry(arr);

        // return mytry2(arr);

        return mytry3(arr);
    }

    private String mytry3(int[] nums) {
        // 2 pointers: O(N ^ 2) time
        // 思考了一下， 如果固定第一个数或第二个数的话， 那么如果 1st 和 2nd 和太小的话， 是没有办法决定移动 2nd/3rd 还是 1st/3rd， 因为都可以使得和变小
        // 所以应该固定 3rd， 1st 和 2nd 和太小的话， 那么就只移动 1st 来使得和变大就行了
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = n - 1; i >= 0; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    return "yes";
                } else {
                    left++;
                }
            }
        }
        return "no";
    }

    private String mytry2(int[] nums) {
        // improved by BS: O(N ^ 2 * logN), find the 3rd number
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int target = nums[i] + nums[j];
                if (hasSmaller(nums, j + 1, n - 1, target)) {
                    return "yes";
                }
            }
        }
        return "no";
    }
    private boolean hasSmaller(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                return true;
            } else {
                end = mid;
            }
        }
        if (nums[start] < target) {
            return true;
        } else if (nums[end] < target) {
            return true;
        } else {
            return false;
        }
    }

    private String mytry(int[] nums) {
        // brute force: O(N ^ 3), find all triples and check
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        return "yes";
                    }
                }
            }
        }
        return "no";
    }
}
