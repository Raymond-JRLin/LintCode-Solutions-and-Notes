// 5. Kth Largest Element
// Find K-th largest element in an array.
//
// Example
// In array [9,3,2,4,8], the 3rd largest element is 4.
//
// In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.
//
// Challenge
// O(n) time, O(1) extra memory.
//
// Notice
// You can swap elements in the array
//


public class Solution {
    /*
     * @param : An integer
     * @param : An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // return method1(n, nums);

        return method2(n, nums);
    }

    private int method2(int n, int[] nums) {
        return quickSelect(nums, 0, nums.length - 1, n);
    }
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        if (start + k - 1 <= right) {
            return quickSelect(nums, start, right, k);
        }
        if (start + k - 1 >= left) {
            return quickSelect(nums, left, end, k - (left - start));
        }
        return nums[right + 1];
    }

    private int method1(int n, int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(n);
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > n) {
                pq.poll();
            }
        }
        return pq.peek();
    }
};
