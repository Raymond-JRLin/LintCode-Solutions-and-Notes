// 683. Word Break III
// 中文English
// Give a dictionary of words and a sentence with all whitespace removed, return the number of sentences you can form by inserting whitespaces to the sentence so that each word can be found in the dictionary.
//
// Example
// Example1
//
// Input:
// "CatMat"
// ["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
// Output: 3
// Explanation:
// we can form 3 sentences, as follows:
// "CatMat" = "Cat" + "Mat"
// "CatMat" = "Ca" + "tM" + "at"
// "CatMat" = "C" + "at" + "Mat"
// Example1
//
// Input:
// "a"
// []
// Output: 0
// Notice
// Ignore case


public class Solution {
    /*
     * @param : A string
     * @param : A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (dict == null || dict.size() == 0) {
            return 0;
        }
        // List<String> result = new ArrayList<>();
        dfs(s, dict, 0);
        return count;
    }
    public int count = 0;
    private void dfs(String s, Set<String> dict, int index) {
        if (index == s.length()) {
            count++;
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (dict.contains(sub)) {
                dfs(s, dict, i + 1);
            }
        }
    }
}
