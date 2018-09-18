// 1468. Two Numbers That Are Not Repeated
// Description
// Given an array a[], except for 2 numbers, all other numbers appear 2 times, find the 2 numbers that are not repeated.
//
// The length of the array does not exceed 80000008000000, and the number in the array is a positive integer and does not exceed 1e91e9
// Results will be sorted
// Your program will be executed multiple times in a loop, please solve the problem under the time complexity of O(n)O(n)
// Have you met this question in a real interview?
// Example
// Give a =[1,2,5,5,6,6]，return [1,2]。
//
// Explanation:
// In addition to the 1 and 2 other numbers appear twice, so return [1, 2]
// Give a=[3,2,7,5,5,7]，return [2,3]。
//
// Explanation:
// In addition to the 2 and 3 other numbers appear twice, so return [2, 3]


public class Solution {
    /**
     * @param a: the array
     * @return: the two numbers that are not repeated
     */
    public List<Integer> theTwoNumbers(List<Integer> a) {
        // Write your code here
        if (a == null || a.size() == 0) {
            return Collections.emptyList();
        }

        return method1(a);
    }

    private List<Integer> method1(List<Integer> list) {
        // 我觉得思路是这样的： 肯定要用到异或， 全部异或之后是答案要的两个数的异或， 重点应该在于如何在异或的过程中把这两个数区分出来。 既然这是两个不同的数， 那么异或的结果一定不是 0， 也就意味着二进制一定有 1， 这个 1 就是由于这两个数的不同而异或得到的。 那就可以先异或所有的数， 然后找这个结果二进制中的某位 1 （这里是最低位， 可以最快找到）， 利用这个 1 来区分那两个数， 而对于其他的数无所谓， 反正都有重复可以异或掉
        int sum = 0;
        // 异或所有的数
        for (int num : list) {
            sum ^= num;
        }
        // 找一个 1
        int count = 0;
        while ((sum & (1 << count)) == 0) {
            count++;
        }
        int res1 = 0;
        int res2 = 0;
        for (int num : list) {
            if ((num & (1 << count)) == 0) {
                res1 ^= num;
            } else {
                res2 ^= num;
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(res1);
        result.add(res2);
        return result;
    }
}
