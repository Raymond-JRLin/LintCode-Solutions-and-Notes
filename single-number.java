// 82. Single Number
// Description
// Given 2*n + 1 numbers, every numbers occurs twice except one, find it.
//
// Have you met this question in a real interview?
// Example
// Given [1,2,2,1,3,4,3], return 4
//
// Challenge
// One-pass, constant extra space.


public class Solution {
    /**
      *@param A : an integer array
      *return : a integer
      */
    public int singleNumber(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return A[0];
        }

        // return method1(A);

        // return method2(A);

        return method3(A);
    }

    private int method3(int[] A) {
        // method 3: bitwise operation
        // 又是异或这个小婊砸
        // 我们知道异或是不同为 1， 那自身异或就会为 0， 并且异或满足交换律， 所有把所有的数字拿去异或， 最后孤立的那个就会剩下来
        int result = 0;
        for (int val : A) {
            result ^= val;
        }
        return result;
    }

    private int method2(int[] A) {
        // method 2: based on sorting
        Arrays.sort(A);
        int n = A.length;
        if (A[0] != A[1]) {
            return A[0];
        }
        if (A[n - 1] != A[n - 2]) {
            return A[n - 1];
        }
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] != A[i]) {
                return A[i - 1];
            }
            i++;
        }
        return 0;
    }

    private int method1(int[] A) {
        // method 1: based on Set
        Set<Integer> set = new HashSet<>();
        for (int val : A) {
            if (set.contains(val)) {
                set.remove(val);
            } else {
                set.add(val);
            }
        }
        Iterator<Integer> it = set.iterator();
        return it.next();
    }
}
