// 171. Anagrams
// 中文English
// Given an array of strings, return all groups of strings that are anagrams.
//
// Example
// Example 1:
//
// Input:["lint", "intl", "inlt", "code"]
// Output:["lint", "inlt", "intl"]
// Example 2:
//
// Input:["ab", "ba", "cd", "dc", "e"]
// Output: ["ab", "ba", "cd", "dc"]
// Challenge
// What is Anagram?
//
// Two strings are anagram if they can be the same after change the order of characters.
// Notice
// All inputs will be in lower-case
//


public class Solution {
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        // write your code here
        if (strs == null) {
            return null;
        }
        List<String> results = new ArrayList<>();
        if (strs.length == 0) {
            return results;
        }
        Map<String, ArrayList<String>> hash = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] array = strs[i].toCharArray();
            Arrays.sort(array);
            String string = String.valueOf(array);
            if (!hash.containsKey(string)) {
                hash.put(string, new ArrayList<String>());
            }
            hash.get(string).add(strs[i]);
        }
        for (Map.Entry<String, ArrayList<String>> entry : hash.entrySet()) {
            if (entry.getValue().size() > 1) {
                results.addAll(entry.getValue());
            }
        }
        return results;
    }
}
