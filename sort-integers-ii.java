// 464. Sort Integers II
// Given an integer array, sort it in ascending order. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.
//
// Example
// Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].


public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
    public void sortIntegers2(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) {
            return;
        }


        // method1(A);

        // method2(A);

        method3(A);
    }

    private void method3(int[] A) {
        // merge sort
        int n = A.length;
        int[] temp = new int[n];
        mergeSort(A, 0, n - 1, temp);
    }
    private void mergeSort(int[] nums, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        // int left = start;
        // int right = end;
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid, temp);
        mergeSort(nums, mid + 1, end, temp);
        merge(nums, start, mid, end, temp);
    }
    private void merge(int[] nums, int start, int mid, int end, int[] temp) {
        // see as two arrays starting from start and mid + 1 respectively, and use 2 pointers to do merge, smaller would be in the temp first
        int left = start;
        int right = mid + 1;
        int index = start;
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right]) {
                temp[index] = nums[left];
                index++;
                left++;
            } else {
                temp[index] = nums[right];
                index++;
                right++;
            }
        }
        while (left <= mid) {
            temp[index] = nums[left];
            index++;
            left++;
        }
        while (right <= end) {
            temp[index] = nums[right];
            index++;
            right++;
        }
        // copy new sorted array back to original array
        for (int i = start; i <= end; i++) {
            nums[i] = temp[i];
        }
    }

    private void method2(int[] A) {
        // quick sort
        quickSort(A, 0, A.length - 1);
    }
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
                right--;
            }
        }
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }

    private void method1(int[] A) {
        // heap sort
        int n = A.length;
        PriorityQueue<Integer> heap = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            heap.offer(A[i]);
        }
        for (int i = 0; i < n; i++) {
            A[i] = heap.poll();
        }
    }
}
