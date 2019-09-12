// 197. Permutation Index
// 中文English
// Given a permutation which contains no repeated number, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.
//
// Example
// Example 1:
//
// Input:[1,2,4]
// Output:1
// Example 2:
//
// Input:[3,2,1]
// Output:6


public class Solution {
    /**
     * @param A an integer array
     * @return a long integer
     */
    public long permutationIndex(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return 1;
        }
        int n = A.length;

        // method 1:
        // 扫描每一位数，找出在它之后有几个数比它小，再算出它后面还有几位数，那么它之前的排列就有： 比它小的个数 * 几位数的阶乘， 阶乘意味着它之前可以有几种不同的排列方式，每个比它小的数都可以做这么多种排列
        // reference: https://segmentfault.com/a/1190000004683277
        // Map<Integer, Integer> map = new HashMap<>();
        // // step 1: find how many numbers are smaller than current digit
        // for (int i = 0; i < n; i++) {
        //     int count = 0;
        //     for (int j = i + 1; j < n; j++) {
        //         if (A[j] < A[i]) {
        //             count++;
        //         }
        //     }
        //     map.put(A[i], count);
        // }
        // // step 2: find the how many digits are behind current number, and calculate result
        // long result = 0;
        // for (int i = 0; i < n; i++) {
        //     long fact = 1;
        //     for (int j = n - i - 1; j > 0; j--) {
        //         fact *= j;
        //     }
        //     result += fact * map.get(A[i]);
        // }
        // // step 3: result calculated above means how many permutation are occuring ahead of giving permutation, so we should + 1 to show what the index of current permutation
        // return result + 1;

        // method 2:
        // reference 中还提供了一种不需要使用 HashMap 的方法，就是初始化 fact 为1， 然后从后往前扫描，这样可以一直保持 fact 随着 index 变化来计算 （因为其实我们观察阶乘可以发现， 阶乘也只和 index 有关）， 然后内嵌 for loop 计算有多少比当前数小的数， 实时计算， 实时更新 result； 并且如果我们初始化 result 为 1， 那么最后结果则不需要 + 1
        long fact = 1;
        long result = 1;
        for (int i = n - 1; i >= 0; i--) {
            // calculate the count of smaller numbers
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (A[j] < A[i]) {
                    count++;
                }
            }
            // update permutation result
            result += count * fact;
            // update fact
            fact *= n - i;
        }
        return result;
    }
}
