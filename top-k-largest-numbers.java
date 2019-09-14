// 544. Top k Largest Numbers
// 中文English
// Given an integer array, find the top k largest numbers in it.
//
// Example
// Example1
//
// Input: [3, 10, 1000, -99, 4, 100] and k = 3
// Output: [1000, 100, 10]
// Example2
//
// Input: [8, 7, 6, 5, 4, 3, 2, 1] and k = 5
// Output: [8, 7, 6, 5, 4]


class Solution {
    /*
     * @param nums an integer array
     * @param k an integer
     * @return the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // Write your code here
        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // // (k, new Comparator<Integer>() {
        // //     public int compare(Integer a, Integer b) {
        // //         return a - b;
        // //     }
        // // });
        // for (int i = 0; i < nums.length; i++) {
        //     pq.offer(nums[i]);
        //     if (pq.size() > k) {
        //         pq.poll();
        //     }
        // }
        // int[] results = new int[k];
        // for (int i = k - 1; i >= 0; i--) {
        //     results[i] = pq.poll();
        // }
        // return results;
        quickSort(nums, 0, nums.length - 1);
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            results[i] = nums[i];
        }
        return results;
    }
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int pivot = nums[left + (right - left) / 2];
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
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
};
