// 1634. Secret Word
// Description
// Given a secrect word, and an encoding rule as follows: Transform each letter in the secret, different letters can not be changed to the same letter. Such as banana -> xyzyzy, but banana can not become xyyyyy, because there is no way to decode back.
// Now input a very long string, and it is required to determine whether a substring exists in the string can be transformed by the above encoding rule. If exists, output string "yes", otherwise output "no".
//
// The length of the secret word does not exceed 1010.
//
// The length of the string does not exceed 1000010000.
//
// The string only consists of lowercase.
//
// Have you met this question in a real interview?
// Example
// Give s="abcabcd", word="xyzxyz", return yes
//
// Explaination:
// "x" can transfer to "a", "y" can transfer to "b" and "z" can transfer to "c".
// Give s="abca", word="xyzd", return no
//
// Explaination:
// the word "xyzd" has no way to transfer to "abca"


public class Solution {
    /**
     * @param s: the long string
     * @param word: the secrect word
     * @return: whether a substring exists in the string can be transformed by the above encoding rule
     */
    public String getAns(String s, String word) {
        // Write a code here
        if (s.length() < word.length()) {
            return "no";
        }

        return mytry(s, word);
    }

    private String mytry(String s, String word) {
        // check each substring
        int n = s.length();
        int m = word.length();
        for (int i = 0; i < n - m + 1; i++) {
            String curr = s.substring(i, i + m);
            if (canTrans(curr, word)) {
                return "yes";
            }
        }
        return "no";
    }
    private boolean canTrans(String s, String word) {
        Map<Character, Character> map = new HashMap<>(); // <from char, to char>
        Set<Character> set = new HashSet<>(); // 被匹配过的 to char
        for (int i = 0; i < s.length(); i++) {
            char from = word.charAt(i);
            char to = s.charAt(i);
            if (map.containsKey(from) && map.get(from) != to) {
                // 一对多
                return false;
            } else if (!map.containsKey(from) && set.contains(to)) {
                // 已经被匹配掉了
                return false;
            }
            map.put(from, to);
            set.add(to);
        }
        return true;
    }
}
