// 32. Minimum Window Substring
// 中文English
// Given two strings source and target. Return the minimum substring of source which contains each char of target.
//
// Example
// Example 1:
//
// Input: source = "abc", target = "ac"
// Output: "abc"
// Example 2:
//
// Input: source = "adobecodebanc", target = "abc"
// Output: "banc"
// Explanation: "banc" is the minimum substring of source string which contains each char of target "abc".
// Example 3:
//
// Input: source = "abc", target = "aa"
// Output: ""
// Explanation: No substring contains two 'a'.
// Challenge
// O(n) time
//
// Notice
// If there is no answer, return "".
// You are guaranteed that the answer is unique.
// target may contain duplicate char, while the answer need to contain at least the same number of that char.


public class Solution {
    /*
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        // write your code here
        if (source == null || source.length() == 0) {
            return "";
        }
        int n = source.length();
        int t_len = target.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";
        // 在想 source 的一段 substring 是否包含了 target 的时候， 如果每个 substring 都 loop 一遍 target 那么 time complexity 就会有 target 的长度， 可以把 target 的每一个 char 放入 set 中， 查看是否 contains
        // but! set cannot contains duplicates while target may have duplicates
        // so we can use a array to record

        // my try: 2 pointers
        // ref: http://fisherlei.blogspot.com/2012/12/leetcode-minimum-window-substring.html
        // 尾指针不断向后扫， 找到一段 substring 包含 target 中所有的 char 后， 更新长度与答案， 然后如果 target 中的 char 在 substring 中重复了， 头指针向后移， 不然尾指针继续向后扫， 直到找到下一个重复的 char， 然后头指针消除掉这个重复的， 再看看能否有更短
        // record target chars
        // int[] targetChar = new int[256];
        // for (int i = 0; i < t_len; i++) {
        //     targetChar[target.charAt(i)]++;
        // }
        // // scan source
        // int[] sourceChar = new int[256];
        // int visit = 0;
        // int start = 0;
        // int minLeft = 0;
        // for (int i = 0; i < n; i++) {
        //     int c = (int) source.charAt(i);
        //     // it is in target
        //     if (targetChar[c] > 0) {
        //         sourceChar[c]++;
        //         if (sourceChar[c] <= targetChar[c]) {
        //             // 若是更多的重复 char 则不算进去
        //             visit++;
        //         }
        //     }
        //     // found a sustring of source with all chars in target, but this substring may have more than target
        //     if (visit == t_len) {
        //         // erase duplicates char of target and move start pointer
        //         while (targetChar[source.charAt(start)] == 0 || sourceChar[source.charAt(start)] > targetChar[source.charAt(start)]) {
        //             // 注意这里的 start 和 i 都是 string 的 index 而不是数组的
        //             // 若是不是 target 中的 char || 重复的多余 char
        //             // 删去， 然后更新头指针
        //             sourceChar[source.charAt(start)]--;
        //             start++;
        //         }
        //         // update min length
        //         if (i - start + 1 < minLen) {
        //             minLen = i - start + 1;
        //             // 设置 minLeft 是因为有可能已经找到了最短的答案， 可是 source 中可能后面还有不是 target 中 char 的 char， 未避免更多无关的字符出现
        //             minLeft = start;
        //             // update result string
        //             result = source.substring(start, i + 1);
        //         }

        //     }

        // }
        // if (minLen == Integer.MAX_VALUE) {
        //     return "";
        // } else {
        //     return result;
        // }

        // method 2: use 2 arrays to compare source and target
        // 思路与上面的可能略有不同： 右指针不断向右扫知道找到一段包含了所有 target char， 同样可能不仅仅只有 target， 然后更新记录， 左指针向右移一位， 如果此时不全包含 target char 那就右指针向右直到找到， 左指针每次向右移一位， 如果找到的 substring 中有重复的， 那么右指针不会动， 左指针一个个向右缩进， 所以这个方法会保持着一个 window 总是包含所有 target 的 char， 然后左指针不断缩进去找最短的
        // ref: https://aaronice.gitbooks.io/lintcode/content/two_pointers/minimum_window_substring.html
        // 首先同样初始化记录 target 的数组
        int[] targetChar = new int[256];
        for (int i = 0; i < t_len; i++) {
            targetChar[target.charAt(i)]++;
        }
        int[] sourceChar = new int[256]; // record source chars
        int right = 0;
        for (int left = 0; left < n; left++) {
            // keep moving right pointer to find a susbtring containing all chars in target
            while (right < n && !isValid(targetChar, sourceChar)) {
                sourceChar[source.charAt(right)]++;
                right++;
            }
            // found a possible substring which may be more than target
            if (isValid(targetChar, sourceChar)) {
                if (right - left + 1 < minLen) {
                    // update min length and result string
                    minLen = right - left + 1;
                    result = source.substring(left, right); // right can not + 1 because in above while loop,
                }
            }
            // move left pointer one step
            sourceChar[source.charAt(left)]--;
        }
        return result;
    }
    private boolean isValid(int[] target, int[] source) {
        for (int i = 0; i < 256; i++) {
            if (target[i] > source[i]) {
                return false;
            }
        }
        return true;
    }
}
