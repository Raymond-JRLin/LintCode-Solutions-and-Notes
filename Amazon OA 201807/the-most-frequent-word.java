// 1484. The Most Frequent word
// Description
// Give a string s witch represents the content of the novel, and then give a list indicating that the words do not participate in statistics, find the most frequent word(return the one with the smallest lexicographic order if there are more than one word)
//
// Have you met this question in a real interview?
// Example
// Input: s = "Jimmy has an apple, it is on the table, he like it"
// excludeWords = ["a","an","the"]
// Output:"it"


public class Solution {
    /**
     * @param s: a string
     * @param excludewords: a dict
     * @return: the most frequent word
     */
    public String frequentWord(String s, Set<String> excludewords) {
        // Write your code here

        return mytry(s, excludewords);
    }

    private String mytry(String para, Set<String> set) {
        // 就和 1369. Most Common Word 一样的， 这里有多个答案
        char[] chars = new char[]{'!', '?', '\'', ',', ';', '.', ' '};
        Set<Character> pun = new HashSet<>(); // punctuations
        for (char c : chars) {
            pun.add(c);
        }

        Map<String, Integer> map = new HashMap<>(); // <string, freq>
        int left = 0;
        int right = 0;
        while (right <= para.length()) {
            if (right == para.length() || pun.contains(para.charAt(right))) {
                String curr = para.substring(left, right).toLowerCase();
                // System.out.println(curr);
                if (!set.contains(curr) && curr.length() > 0) {
                    // 当两个连续的 punctuation 出现的时候， 此时 curr 为 ""
                    map.put(curr, map.getOrDefault(curr, 1) + 1);
                }
                left = right + 1;
            }
            right++;
        }
        int freq = 0;
        String result = "";
        for (String key : map.keySet()) {
            if (map.get(key) > freq || map.get(key) == freq && result.compareTo(key) > 0) {
                // 注意有多个答案， 取 lexicographic order 小的
                result = key;
                freq = map.get(key);
            }
        }
        return result;
    }
}
