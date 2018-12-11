// 1369. Most Common Word
// Description
// Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words. It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
//
// Words in the list of banned words are given in lowercase, and free of punctuation. Words in the paragraph are not case sensitive. The answer is in lowercase.
//
// 1 <= paragraph.length <= 1000.
// 1 <= banned.length <= 100.
// 1 <= banned[i].length <= 10.
// The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
// paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
// Different words in paragraph are always separated by a space.
// There are no hyphens or hyphenated words.
// Words only consist of letters, never apostrophes or other punctuation symbols.
// Have you met this question in a real interview?
// Example
// Input:
// paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
// banned = ["hit"]
//
// Output:"ball"
//
// Explanation:
// "hit" occurs 3 times, but it is a banned word.
// "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
// Note that words in the paragraph are not case sensitive,
// that punctuation is ignored (even if adjacent to words, such as "ball,"),
// and that "hit" isn't the answer even though it occurs more because it is banned.


public class Solution {
    /**
     * @param paragraph:
     * @param banned:
     * @return: nothing
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        //
        return mytry(paragraph, banned);
    }

    private String mytry(String para, String[] banned) {
        char[] chars = new char[]{'!', '?', '\'', ',', ';', '.', ' '};
        Set<Character> pun = new HashSet<>(); // punctuations
        for (char c : chars) {
            pun.add(c);
        }
        Set<String> set = new HashSet<>();
        for (String ban : banned) {
            String curr = ban.toLowerCase();
            set.add(curr);
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
            if (map.get(key) > freq) {
                result = key;
                freq = map.get(key);
            }
        }
        return result;
    }
}
