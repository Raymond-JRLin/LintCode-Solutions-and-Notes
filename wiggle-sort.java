// 508. Wiggle Sort
// Given an unsorted array nums, reorder it in-place such that
//
// nums[0] <= nums[1] >= nums[2] <= nums[3]....
// Example
// Given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
//
// Notice
// Please complete the problem in-place.


public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        // Write your code here
        if (nums == null || nums.length < 2) {
            return;
        }

        // there are several final result would meet the requirement, example just gave one possible answer

        // method1(nums);

        // method2(nums);

        method3(nums);
    }
    private void method3(int[] nums) {
        // method 3: just traverse and use principle after observation, O(n) time and O(1) space
        // for odd position: nums[i] >= nums[i - 1], case that needs swap: nums[i] < nums[i - 1]
        // for even position: nums[i] <= nums[i - 1], case that needs swap: nums[i] > nums[i - 1]
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if ((i % 2 != 0 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
        }
    }

    private void method2(int[] nums) {
        // method 2: based on Arrays.sort(), o(nlogn) time but O(1) space
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 1; i < n - 1; i += 2) {
            int temp = nums[i + 1];
            nums[i + 1] = nums[i];
            nums[i] = temp;
        }
    }

    private void method1(int[] nums) {
        // method 1: based on Arrays.sort(), O(nlogn) time and O(n) space
        int n = nums.length;
        Arrays.sort(nums);
        int[] copy = Arrays.copyOf(nums, n);
        int j = 0;
        int k = n - 1;
        // smallest, largest, 2nd smallest, 2nd largest, ...
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = copy[j];
                j++;
            } else {
                nums[i] = copy[k];
                k--;
            }
        }
    }
}
