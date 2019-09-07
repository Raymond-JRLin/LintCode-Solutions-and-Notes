// 570. Find the Missing Number II
// 中文English
// Giving a string with number from 1-n in random order, but miss 1 number.Find that number.
//
// Example
// Example1
//
// Input: n = 20 and str = 19201234567891011121314151618
// Output: 17
// Explanation:
// 19'20'1'2'3'4'5'6'7'8'9'10'11'12'13'14'15'16'18
// Example2
//
// Input: n = 6 and str = 56412
// Output: 3
// Explanation:
// 5'6'4'1'2
// Notice
// n <= 30
// Data guarantees have only one solution
//


public class Solution {
    /**
     * @param n an integer
     * @param str a string with number from 1-n
     *            in random order and miss one number
     * @return an integer
     */
    public int result = -1;
    public int findMissing2(int n, String str) {
        // Write your code here
        // compared to I, it's more complicated, since we should decide, for example "19201234", it's 19 20 1 2 3 4 or 19 2 0 12 3 4 - how to split numbers
        // so we should search and traverse so we can think DFS

        boolean[] nums = new boolean[n + 1]; // default values are false
        dfs(nums, 0, n, str);
        return result;
    }
    private void dfs(boolean[] nums, int index, int n, String str) {
        if (index > str.length() - 1) {
            int count = 0;
            int first = 0;
            for (int i = 1; i < n + 1; i++) {
                // start from 1 so we can set original result as 0
                if (!nums[i]) {
                    count++;
                    first = i;
                }
            }
            if (count == 1) {
                result = first;
            }
            return;
        }
        int val = str.charAt(index) - '0'; // get the first digit
        if (val <= n && !nums[val]) {
            nums[val] = true;
            dfs(nums, index + 1, n, str);
            nums[val] = false;
        }
        // keep checking for 2 digtis
        if (index + 1 >= str.length() || val > n / 10) {
            return;
        }
        val = str.charAt(index + 1) - '0' + val * 10;
        if (val <= n && !nums[val]) {
            nums[val] = true;
            dfs(nums, index + 2, n, str);
            nums[val] = false;
        }

    }
}
