// 184. Largest Number
// 中文English
// Given a list of non negative integers, arrange them such that they form the largest number.
//
// Example
// Example 1:
//
// Input:[1, 20, 23, 4, 8]
// Output:"8423201"
// Example 2:
//
// Input:[4, 6, 65]
// Output:"6654"
// Challenge
// Do it in O(nlogn) time complexity.
//
// Notice
// The result may be very large, so you need to return a string instead of an integer.
//


public class Solution {
    /*
     * @param nums: A list of non negative integers
     * @return: A string
     */
    public String largestNumber(int[] nums) {
        // write your code here
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return "0";
        }
        // 要拼成最大数， 我们只要让较大的数排在前面， 较小的数排在后面就行。 然而如何对原数组排序呢？ 当比较一位数时， 直接比较大小就行了， 但对于多位数呢？
        // 如果从最高位开始一个个比较， 其实挺麻烦的， 因此我们可以采取一种很巧妙的方法： 就是我们把两个数拼在一起， 然后再把这两个数换个顺序再拼在一起， 这时候就可以直接比较了。
        // 别忘了此时排序需要重载 compare 方法

        Comparator<String> strComparator = new Comparator<String>() {
            @Override
            // 重载的时候要注意： Comparator 是 reference 引用类， 所以不能直接 sort 题目给的 int[] 或者重载为 int 类， 要么把题目给的 int[] 转成 Integer 类， 要么转成 String 类
            public int compare(String left, String right) {
                return (right + left).compareTo(left + right); // 注意这里的顺序
                // 我们想要的是降序 （从大到小）， 也就意味着 right - left > 0, 也就是 (r + l) - (l + r) > 0, 也就是在字母顺序上 (r + l) 要在 (l + r) 的后面， 而 string.compareTo(arg) > 0 就是 string follows arg
            }
        };
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, strComparator);
        if (strs[0].equals("0")) {
            return "0";
        }
        String result = "";
        for (String str : strs) {
            result += String.valueOf(str);
        }
        return result;
    }
}
// class myComparator implements Comparator<Integer> {
//             @Override
//             public int compare(int left, int right) {
//                 String l = String.valueOf(left);
//                 String r = String.valueOf(right);
//                 return (r + l).compareTo(l + r); // 注意这里的顺序
//                 // 我们想要的是降序 （从大到小）， 也就意味着 right - left > 0, 也就是 (r + l) - (l + r) > 0, 也就是在字母顺序上 (r + l) 要在 (l + r) 的后面， 而 string.compareTo(arg) > 0 就是 string follows arg
//             }
//         }
