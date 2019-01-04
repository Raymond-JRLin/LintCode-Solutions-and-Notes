// 1646. CheckWords
// Description
// Given a word s, and a string set str. This word removes one letter at a time until there is only one letter in the word. Is there a sort of deletion in which all words are in str. For example, the word is 'abc', the string set is {'a', 'ab', 'abc'}, if the order of deletion is 'c', 'b', and 'abc', 'ab', 'a' are all in the collection, so it is eligible.
// Output whether this combination meets the condition.
//
// 1<=|str[i]|,|s|<=30
// 1<=the number of strings in str<=100
//
// Have you met this question in a real interview?
// Example
// Given s="abc",str=["abc","ac","c"], returns true
//
// Explanation:
// First "abc" is in `str`
// Delete 'b', "ac" is  in `str`
// Delete 'a', "c" is in `str`
// Given s="abc",str=["abc","ab","c"], returns false
//
// Explanation:
// "abc" in `str`
// Next you can only delete 'c', "ab" in `str`
// Since "a" and "b" are not in `str`, return false


public class Solution {
    /**
     * @param s:
     * @param str:
     * @return: Output whether this combination meets the condition
     */
    public boolean checkWord(String s, String[] str) {
        // Write your code here
        if (str == null || str.length == 0) {
            return false;
        }

        // return mytry(s, str);


        return method2(s, str);
    }

    private boolean method2(String s, String[] strings) {
        // DFS + memorization to faster
        // record the substring with result (True of False), then if we met a substring we already checked, we can know if it can be removed correctly
        Set<String> set = new HashSet<>(Arrays.asList(strings));
        if (!set.contains(s)) {
            return false;
        }
        Map<String, Boolean> map = new HashMap<>();
        return dfs2(s, set, map);
    }
    private boolean dfs2(String s, Set<String> set, Map<String, Boolean> map) {
        if (s.length() == 0) {
            return true;
        }
        // already checked
        if (map.containsKey(s)) {
            return map.get(s);
        }
        if (!set.contains(s)) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            // delete s.charAt(i)
            String next = s.substring(0, i) + s.substring(i + 1, s.length());
            if (dfs2(next, set, map)) {
                map.put(next, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }

    private boolean mytry(String s, String[] strings) {
        // DFS
        Set<String> set = new HashSet<>(Arrays.asList(strings));
        if (!set.contains(s)) {
            return false;
        }
        // return dfs(s, set);
        return dfs1_2(s, set);
    }
    private boolean dfs1_2(String s, Set<String> set) {
        // 这种想法可能更符合通常的 recursion 写法， 逻辑也更加清晰一些， 把判断全放在开头的退出这里
        if (s.length() == 0) {
            return true;
        }
        if (!set.contains(s)) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            // delete s.charAt(i)
            String next = s.substring(0, i) + s.substring(i + 1, s.length());
            if (dfs1_2(next, set)) {
                return true;
            }
        }
        return false;
    }
    private boolean dfs(String s, Set<String> set) {
        if (s.length() == 1) {
            if (set.contains(s)) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            // delete s.charAt(i)
            String curr = s.substring(0, i) + s.substring(i + 1, s.length());
            if (!set.contains(curr)) {
                continue;
            }
            if (dfs(curr, set)) {
                return true;
            }
        }
        return false;
    }
}
