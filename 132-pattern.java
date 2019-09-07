// 636. 132 Pattern
// 中文English
// Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
//
// n will be less than 20,000.
//
// Example
// Example 1:
//
// Input: nums = [1, 2, 3, 4]
// Output: False
// Explanation:
// There is no 132 pattern in the sequence.
// Example 2:
//
// Input: nums = [3, 1, 4, 2]
// Output: True
// Explanation:
// There is a 132 pattern in the sequence: [1, 4, 2].


public class Solution {
    /**
     * @param nums a list of n integers
     * @return true if there is a 132 pattern or false
     */
    public boolean find132pattern(int[] nums) {
        // Write your code here
        if (nums == null || nums.length < 3) {
            return false;
        }
        int n = nums.length;
        // for (int i = 1; i < n - 1; i++) {
        //     if (nums[i - 1] < nums[i + 1] && nums[i + 1] < nums[i]) {
        //         return true;
        //     }
        // }
        // return false;
        // above is wrong, because this "132" can be non-adjacent value

        // method 1: comes from definition, to find 1-3-2 respectively and compare
        // int i = 0; // 1 - min
        // int j = 0; // 2 - max
        // int k = 0; // 3 - middle
        // while (i < n - 1) {
        //     while (i < n - 1 && nums[i] > nums[i + 1]) {
        //         i++;
        //     }
        //     j = i + 1; // 第二个数从第一个数下一个开始往后找
        //     while (j < n - 1 && nums[j] < nums[j + 1]) {
        //         j++;
        //     }
        //     k = j + 1; // 第三个数从第二个数下一个开始往后找
        //     while (k < n) {
        //         if (nums[i] < nums[k] && nums[k] < nums[j]) {
        //             return true;
        //         }
        //         k++;
        //     }
        //     // do not find, continue and repeat
        //     i = j + 1; // 第一个数重新赋值为第二个数下一个
        // }
        // return false;
        // we can see it's not very efficient

        // method 2: more efficient based on Stack
        // 我们用 third 表示 "132" 中的 3 (a(k))， 就是中间的那个数， stack 里放的都是比它大的数， 即"132" 中的 2 (a(j))， 这样我们需要从后往前遍历， 找到一个比栈顶更大的数， 那就把栈顶作为新的 third （这里循环查找）， 然后把更大的数放进栈里， 因为这样可以保证 1: 栈里的元素总是大于 third 的; 2: third 在顺序上总是后面的， 即 j < k; 3: 可以找到更大的 a(j) 和 较大的 a(k)， 这样的组合是更优的， 因为可以给 a(i) 更大空间去找到 a(i)
        Stack<Integer> stack = new Stack<>();
        int third = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < third) {
                return true; // found result
            } else {
                // if we don't find, check whether we should change third
                while (!stack.isEmpty() && nums[i] > stack.peek()) {
                    third = stack.pop();
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }
}
