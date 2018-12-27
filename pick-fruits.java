// 1643. Pick Fruits
// Description
// Xiaohong went to the orchard to pick fruit. There are 2 baskets that can hold countless fruits, but each baskert can only hold one type of fruit. Start from the tree at any position and pick it to the right. Stop picking when one of the following two conditions occurs, 1. encountered the third type of fruit, no basket can be put, 2. meet the end. Returns the maximum number of fruits that can be picked.The fruit array is represented by arr.
//
// The length of the array does not exceed 100,000
//
// Have you met this question in a real interview?
// Example
// Given arr=[1,2,1,3,4,3,5,1,2], return 3.
// 
//   Select [1, 2, 1] or [3, 4, 3]. The length is 3.
// Given arr=[1,2,1,2,1,2,1], return 7.
//
//    Select [1, 2, 1, 2, 1, 2, 1]. The length is 7.


public class Solution {
    /**
     * @param arr: the arr
     * @return: the length of the longset subarray
     */
    public int pickFruits(int[] arr) {
        // Write your code here.
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // return mytry(arr);

        // return method1(arr);

        return method2(arr);
    }

    private int method2(int[] nums) {
        // 只有两个数， 那么也可以使用两个 variables 来分别记录. O(N) for 过去就好了 -- 这么解释其实不对
        // 这里的两个 num 并不是指实际的遇到的值， 而可以说是两段， 譬如 [1, 1, 2, 1, 3, ...]
        // [1, 1]: num1 是都是 1 的这一段
        // [1, 1, 2]: num1 变成了只有一个 2 的这段， num2 变成后面两个 1 的这一段
        // [1, 1, 2, 1]: 又遇到了 1， num1 变成了 1， 而 num2 变成后面包含 1 和 2 的三个数这一段
        // [1, 1, 2, 1, 3]: 遇到了新的数， 需要舍弃的是 num2 这段， 而不仅仅是前两个 1 了
        // 所以 num1 和 num2 用来分割最新出现的一段只有相同数的， 以及之前一段， 两段一起也只有两个不同的数字
        int num1 = -1;
        int num2 = -1;
        int count1 = 0;
        int count2 = 0;
        int result = 0;
        for (int num : nums) {
            if (num == num1) {
                count1++;
            } else if (num == num2) {
                num2 = num1;
                count2 += count1; // 注意这里是加起来，因为出现了在后半段的数
                num1 = num;
                count1 = 1;
            } else {
                // 出现了新的一个数， 把后半段舍弃， 把 num1 代表的赋给 num2， 新出现的作为 num1
                num2 = num1;
                count2 = count1;
                num1 = num;
                count1 = 1;
            }
            result = Math.max(result, count1 + count2);
        }
        return result;
    }

    private int method1(int[] nums) {
        // 这题和 Longest Substring with At Most K Distinct Characters 最大的不同就是， 这里相同的字符在满足只有 2 个 distinct 数字情况下， 可以一直走。 所以这里查看 size 为 2 的时候， 比较 ticky， 会走过头， 所以可以考虑每次只加 1 个， 然后查看 length 及 size 条件
        int n = nums.length;
        int result = 0;
        int i = 0;
        int j = 0;
        Map<Integer, Integer> map = new HashMap<>(); // <num, # num>
        while (j < n) {
            // 都先放进去
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            j++;
            // 剔除不满足条件的
            while (i < n && map.size() > 2) {
                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }
                i++;
            }
            result = Math.max(result, j - i);
        }
        return result;
    }


    private int mytry(int[] nums) {
        // brute force: O(N ^ 2) time
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            Set<Integer> set = new HashSet<>();
            while (j < n && set.size() <= 2) {
                set.add(nums[j++]);
            }
            if (set.size() > 2) {
                j--;
            }
            result = Math.max(result, j - i);
        }
        return result;
    }
}
