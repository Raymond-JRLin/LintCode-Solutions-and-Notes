// 895. Friend Request
// 中文English
// Given an array Ages of length n, where the first i elements represent the age of the individual i. Find total number of friend requests sent by this n person. There are some requirements:
//
// if Age(B) <= (1/2)Age(A) + 7, A will not send a request to B.
// if Age(B) > Age(A), A will not send a request to B.
// if Age(B) < 100 and Age(A) > 100, A will not send a request to B.
// If it does not satisfy 1,2,3, then A will send a request to B
// Example
// Example1
//
// Input: Ages = [10,39,50]
// Output: 1
// Explanation:
// Only people of age 50 will send friend requests to people of age 39.
// Example2
//
// Input: Ages = [101,79,102]
// Output: 1
// Explanation:
// Only people of age 102 will send friend requests to people of age 101.
// Notice
// Ages.length <= 1000。
// Everyone's age is greater than 0, no larger than 150。
// [10,39,50]


public class Solution {
    /**
     * @param ages: The ages
     * @return: The answer
     */
    public int friendRequest(int[] ages) {
        // Write your code here
        if (ages == null || ages.length == 0) {
            return 0;
        }

        // return mytry(ages);

        return method1(ages);
    }

    private int method1(int[] ages) {
        // just use 2 for loops to get rid of those unsatisfied requirements
        int n = ages.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = ages[i];
                int b = ages[j];
                if (i == j || b <= a / 2 + 7 || b > a ||
                    (b < 100 && a > 100)) {
                    continue;
                }
                result++;
            }
        }
        return result;
    }

    private int mytry(int[] ages) {
        // maybe something wrong but anyways it's wrong
        Arrays.sort(ages);
        int n = ages.length;
        int result = 0;
        int count = 0;
        int repeat = 1;
        int sum = 1;
        for (int i = 1; i < n; i++) {
            if (ages[i] == ages[i - 1]) {
                repeat++;
                sum *= repeat;
                continue;
            }
            if (repeat != 1) {
                result += count * (repeat - 1) + sum;
                repeat = 1;
                sum = 1;
            }
            // System.out.println("now check: " + ages[i]);
            int target = ages[i] > 100 ? 100 : ages[i] / 2 + 8;
            int index = equalOrLarger(ages, 0, i - 1, target);
            // System.out.println("and index is: " + index);
            if (index == -1) {
                continue;
            } else {
                count = i - index; // actually (i - 1) - index + 1, start at index and end at i - 1
                result += count;
            }

        }
        if (repeat != 1) {
//            System.out.println("we have last repeat: " + repeat + );
            result += count * (repeat - 1) + sum;
        }
        return result;
    }
    private int equalOrLarger(int[] nums, int start, int end, int target) {
        // if (end < start) {
        //     return -1;
        // }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        // System.out.println("start and end: " + start + ", " + end);
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >= target) {
            return end;
        } else {
            return -1;
        }
    }
}
