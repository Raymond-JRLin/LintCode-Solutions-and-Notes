// 371. Print Numbers by Recursion
// 中文English
// Print numbers from 1 to the largest number with N digits by recursion.
//
// Example
// Example 1:
//
// Input : N = 1
// Output :[1,2,3,4,5,6,7,8,9]
// Example 2:
//
// Input : N = 2
// Output :[[1,2,3,4,5,6,7,8,9,10,11,12,...,99]
// Challenge
// Do it in recursion, not for-loop.
//
// Notice
// It's pretty easy to do recursion like:
//
// recursion(i) {
//     if i > largest number:
//         return
//     results.add(i)
//     recursion(i + 1)
// }
// however this cost a lot of recursion memory as the recursion depth maybe very large. Can you do it in another way to recursive with at most N depth?
//


public class Solution {
    /*
     * @param n: An integer
     * @return: An array storing 1 to the largest number with n digits
     */
    public ArrayList<Integer> numbersByRecursion(int n) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }
        // it said do recursion with N depth, so we can infer that one recursion with one digit, 每一层处理一“位”， 然后再处理下一层
        // 可以这么考虑： 一层处理好了 （譬如 1 ～ 9）， 下一层就可以由上一层的数都乘以 10 来得到
        helper(result, n, 0);
        return result;
    }
    private void helper(ArrayList<Integer> result, int n, int prev) {
        if (n == 0) {
            if (prev > 0) {
                result.add(prev);
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            helper(result, n - 1, prev * 10 + i);
        }
    }
};
