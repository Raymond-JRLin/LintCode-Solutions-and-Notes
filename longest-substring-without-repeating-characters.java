// 384. Longest Substring Without Repeating Characters
// Description
// Given a string, find the length of the longest substring without repeating characters.
//
// Have you met this question in a real interview?
// Example
// For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
//
// For "bbbbb" the longest substring is "b", with the length of 1.
//
// Challenge
// O(n) time


public class Solution {
    /*
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        // return method1(s);

        // return method2_2(s);

        return method3(s);
    }

    private int method3(String s) {
        // method 3: use HashSet
        int n = s.length();
        Set<Character> set = new HashSet<Character>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < n) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                // new char
                set.add(c);
                // max = Math.max(max, set.size());
                max = Math.max(max, right - left + 1);
                right++;
            } else {
                // repeat char occurs
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
    }

    private int method2_2(String s) {
        // method 2 - 2:
        // ref: http://www.cnblogs.com/grandyang/p/4480780.html
        int n = s.length();
        int[] count = new int[256];
        Arrays.fill(count, -1);
        int max = 0;
        int left = -1;
        for (int i = 0; i < n; i++) {
            left = Math.max(left, count[s.charAt(i)]);
            count[s.charAt(i)] = i;
            max = Math.max(max, i - left);
        }
        return max;
    }

    // private int method2_1(String s) {
        // method 2 - 1: use array
        // ref: http://fisherlei.blogspot.com/2012/12/leetcode-longest-substring-without.html
        // but it's wrong, because 在 if 里面想要更新下一个开始的位置， 可是在 if 外面又要对数组赋值， 这个时候就会出错， 哪怕用上 start 我也没改对
        // int n = s.length();
        // int[] count = new int[256];
        // // count records the index of char, so initialize array as -1
        // Arrays.fill(count, -1);
        // int max = 0;
        // int currLen = 0;
        // int start = 0;
        // for (int i = 0; i < n; i++) {
        //     char c = s.charAt(i);
        //     if (count[c] >= 0) {
        //         // repeat char occurs
        //         max = Math.max(max, currLen); // update
        //         currLen = 0; // reset currLen
        //         start = count[c] + 1; // start again from next index of previous repeat char
        //         Arrays.fill(count, -1); // reset array with -1
        //     }
        //     // if it's not repeat char, set array as its occuring index
        //     count[c] = i;
        //     currLen++;

        // }
        // // updating of max is inside of if condition, so we should compare last time
        // return Math.max(max, currLen);
    // }

    private int method1(String s) {
        // method 1: use HashMap
        // ref: http://bangbingsyb.blogspot.com/2014/11/leetcode-longest-substring-without.html
        int n = s.length();
        int start = 0;
        int max = 0;
        int currLen = 0;
        Map<Character, Integer> map = new HashMap<>(); // <char, index>
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // if this char already occured before, delete all previous chars and update start index and currLen
                // delete char from last start index to current repeat char
                for (int j = start; j < map.get(c); j++) {
                    map.remove(s.charAt(j));
                }
                // update start as next index of previous repeat char, because we need to use previous start index in above for loop, so we should update start index following for loop
                start = map.get(c) + 1;
                // uppdate current length of new substring
                currLen = i - map.get(c);
            } else {
                // if this char never occur, then increase current length
                currLen++;
            }
            // use map to record char and its appearing index
            map.put(c, i);
            // update max length
            max = Math.max(max, currLen);
        }
        return max;
    }
}
