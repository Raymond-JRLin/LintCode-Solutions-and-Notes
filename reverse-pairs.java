// 532. Reverse Pairs
// For an array A, if i < j, and A [i] > A [j], called (A [i], A [j]) is a reverse pair.
// return total of reverse pairs in A.
//
// Example
// Given A = [2, 4, 1, 3, 5] , (2, 1), (4, 1), (4, 3) are reverse pairs. return 3


public class Solution {
    /**
     * @param A an array
     * @return total of reverse pairs
     */
    public long reversePairs(int[] A) {
        // Write your code here
        if (A == null || A.length < 2) {
            return 0;
        }

        // return method1(A);

        // return method2(A);

        return method3(A);
    }

    private long method3(int[] A) {
        // based on BST
        int result = 0;
        Node root = null;
        for (int i = 0; i < A.length; i++) {
            result += search(root, A[i] + 1);
            root = insert(root, A[i]);
        }
        return result;
    }
    private Node insert(Node root, int num) {
        if (root == null) {
            root = new Node(num);
        } else if (num == root.val) {
            root.count++;
        } else if (num < root.val) {
            root.left = insert(root.left, num);
        } else {
            root.count++;
            root.right = insert(root.right, num);
        }
        return root;
    }
    private int search(Node root, int num) {
        if (root == null) {
            return 0;
        } else if (num == root.val) {
            return root.count;
        } else if (num < root.val) {
            return root.count + search(root.left, num);
        } else {
            return search(root.right, num);
        }
    }
    private class Node {
        int val;
        int count;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
            this.count = 1;
        }
    }

    private long method2(int[] A) {
        // based on merge sort
        return mergeSort(A, 0, A.length - 1);
    }
    private int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int sum = 0;
        sum += mergeSort(nums, start, mid);
        sum += mergeSort(nums, mid + 1, end);
        sum += merge(nums, start, mid, end);
        return sum;
    }
    private int merge(int[] nums, int start, int mid, int end) {
        int left = start;
        int right = mid + 1;
        int index = start;
        int sum = 0;
        int[] temp = new int[nums.length];
        while (left <= mid && right <= end) {
            if (nums[left] > nums[right]) {
                temp[index++] = nums[right++];
                sum += mid - left + 1;
            } else {
                temp[index++] = nums[left++];
            }
        }
        while (left <= mid) {
            temp[index++] = nums[left++];
        }
        while (right <= end) {
            temp[index++] = nums[right++];
        }
        for (int i = start; i <= end; i++) {
            nums[i] = temp[i];
        }
        return sum;
    }


    private long method1(int[] A) {
        // brute force
        long result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] > A[i]) {
                    result++;
                }
            }
        }
        return result;
    }
}
