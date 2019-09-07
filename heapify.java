// 130. Heapify
// 中文English
// Given an integer array, heapify it into a min-heap array.
//
// For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
// Example
// Example 1
//
// Input : [3,2,1,4,5]
// Output : [1,2,3,4,5]
// Explanation : return any one of the legitimate heap arrays
// Challenge
// O(n) time complexity
//
// Clarification
// What is heap? What is heapify? What if there is a lot of solutions?
//
// Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.
// Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
// Return any of them.


public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
        // write your code here
        if (A == null || A.length <= 1) {
            return;
        }
        // siftup - O(nlogn)
        // for (int i = 0; i < A.length; i++) {
        //     siftup(A, i);
        // }

        // siftdown - O(n)
        for (int i = A.length / 2; i>= 0; i--) {
            siftdown(A, i);
        }
    }
    private void siftup(int[] num, int k) {
        while (k > 0) {
            int father = (k - 1) / 2;
            if (num[k] > num[father]) {
                break;
            }
            int temp = num[k];
            num[k] = num[father];
            num[father] = temp;
            k = father;
        }
    }
    private void siftdown(int[] num, int k) {
        while (k < num.length) {
            int son = k;
            if (k * 2 + 1 < num.length && num[k * 2 + 1] < num[son]) {
                son = k * 2 + 1;
            }
            if (k * 2 + 2 < num.length && num[k * 2 + 2] < num[son]) {
                son = k * 2 + 2;
            }
            if (son == k) {
                break;
            }
            int temp = num[k];
            num[k] = num[son];
            num[son] = temp;
            k = son;
        }
    }
}
