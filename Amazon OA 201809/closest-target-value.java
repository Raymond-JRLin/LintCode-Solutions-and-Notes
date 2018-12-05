// 1478. Closest Target Value
// Description
// Given an array, find two numbers in the array and their sum is closest to the target value but does not exceed the target value, return their sum.
//
// if there is no result meet the requirement, return -1.
//
// Have you met this question in a real interview?
// Example
// Input:target = 15
// array = [1,3,5,11,7]
// Output:14


public class Solution {
    /**
     * @param target: the target
     * @param array: an array
     * @return: the closest value
     */
    public int closestTargetValue(int target, int[] array) {
        // Write your code here
        if (array == null || array.length == 0) {
            return -1;
        }

        // return mytry(array, target);

        return mytry2(array, target);
    }

    private int mytry2(int[] nums, int target) {
        // BS: O(NlogN) time
        int n = nums.length;
        Arrays.sort(nums); // sort
        int result = Integer.MIN_VALUE;
        boolean found = false;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] >= target) {
                break;
            }
            int index = equalOrLessBS(nums, i + 1, n - 1, target - nums[i]);
            // System.out.println("check " + nums[i] + " and my bs index: " + index);
            if (index == -1) {
                // cannot find 2nd value
                continue;
            }
            result = Math.max(result,  nums[i] + nums[index]);
            found = true;

        }
        return found ? result : -1;
    }
    private int equalOrLessBS(int[] nums, int start, int end, int target) {
        // find the qual or less than target by BS
        while (start + 1 < end) {
            int mid  = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] <= target) {
            return end;
        } else if (nums[start] <= target) {
            return start;
        } else {
            return -1;
        }
    }


    private int mytry(int[] nums, int target) {
        // 2 pointers
        // O(NlogN + N) time and O(1) space
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int result = Integer.MIN_VALUE;
        boolean found = false;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return target;
            } else if (sum > target) {
                j--;
            }  else {
                result = Math.max(result, sum);
                i++;
                found = true;
            }
        }
        return found ? result : -1;
    }

}
