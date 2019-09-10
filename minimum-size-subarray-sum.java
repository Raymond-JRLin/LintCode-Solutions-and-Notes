// 406. Minimum Size Subarray Sum
// 中文English
// Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.
//
// Example
// Example 1:
//
// Input: [2,3,1,2,4,3], s = 7
// Output: 2
// Explanation: The subarray [4,3] has the minimal length under the problem constraint.
// Example 2:
//
// Input: [1, 2, 3, 4, 5], s = 100
// Output: -1
// Challenge
// If you have figured out the O(nlog n) solution, try coding another solution of which the time complexity is O(n).
//


public class Solution {
    /*
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;

        // my try: use prefix sum - I think it's correct but 1st try only passed 82% and TLE, so its time complexity is too large when check prefix sum with 2 for loops; then I tried 2nd time, check prefix[j] as inverse order, then passed. Anyway, it's O(n ^ 2) - not good
        // int[] prefix = new int[n + 1];
        // for (int i = 0; i < n; i++) {
        //     prefix[i + 1] += prefix[i] + nums[i];
        // }
        // int len = Integer.MAX_VALUE;
        // for (int i = n; i >= 0; i--) {
        //     // for (int j = 0; j < i; j++) {
        //     //     if (prefix[i] - prefix[j] >= s) {
        //     //         len = Math.min(len, i - j); // 不加 1 是因为这里是 prefix 的 i 和 j， 所以已经比原数组多一位了
        //     //     }
        //     // }
        //     // 2nd try: check inverse order as i
        //     for (int j = i - 1; j >= 0; j--) {
        //         if (prefix[i] - prefix[j] >= s) {
        //             len = Math.min(len, i - j);
        //             break; // once found then do not need to check longer
        //         }
        //     }
        // }
        // if (len == Integer.MAX_VALUE) {
        //     // do not find - don't forget
        //     return -1;
        // } else {
        //     return len;
        // }

        // method 2: use 2 pointers, right pointer continuously scan to right until find a zone whose sum >= s, then update len and move left pointer one step and keep going to check. O(n) time
        // int left = 0;
        // int right = 0;
        // int sum = 0;
        // int len = Integer.MAX_VALUE;
        // while (right < n) {
        //     while (right < n && sum < s) {
        //         sum += nums[right];
        //         right++;
        //     }
        //     while (sum >= s /*&& left <= right*/) {
        //         // don't need to check left pointer because when left == right, then sum would be 0
        //         len = Math.min(len, right - left); // 同样不能 + 1 是因为上一个 while 退出的时候 right 已经又 ++ 了
        //         sum -= nums[left];
        //         left++;
        //     }
        // }
        // if (len == Integer.MAX_VALUE) {
        //     return -1;
        // } else {
        //     return len;
        // }

        // method 3: challenge said O(nlogn) so we may consider BS
        // 对于原数组其实不能 BS， 因此我们可以参考 method 1, 对 prefix sum 进行二分： 即对每一个 prefix sum 我们来找它相对应的右边界， 使得右边界的 prefix sum >= 左边界的 prefix sum 加上 s
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] += prefix[i] + nums[i];
        }
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int target = prefix[i] + s;
            int right = binarySearchRight(prefix, target, i + 1, n);
            if (right == n + 1) {
                break;
            }
            len = Math.min(len, right - i);
        }
        if (len == Integer.MAX_VALUE) {
            return -1;
        } else {
            return len;
        }
    }
    private int binarySearchRight(int[] prefix, int target, int start, int end) {
        // find some place whose prefix sum >= target
        // if (start == end) {
        //     return prefix.length - 1;
        // }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (prefix[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (prefix[start] >= target) {
            return start;
        } else if (prefix[end] >= target) {
            return end;
        } else {
        	return end + 1;
        }
    }
}
