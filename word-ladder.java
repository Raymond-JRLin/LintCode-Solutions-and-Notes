// 120. Word Ladder
// 中文English
// Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, output the length of the sequence.
// Transformation rule such that:
//
// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary. (Start and end words do not need to appear in the dictionary )
// Example
// Example 1:
//
// Input：start = "a"，end = "c"，dict =["a","b","c"]
// Output：2
// Explanation：
// "a"->"c"
// Example 2:
//
// Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
// Output：5
// Explanation：
// "hit"->"hot"->"dot"->"dog"->"cog"
// Notice
// Return 0 if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.


public class Solution {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return an integer
      */
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        if (dict == null) {
            return 0;
        }
        if (start.equals(end)) {
            return 1;
        }
        // dict.add(start);
        dict.add(end);
        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(start);
        hash.add(start);
        int steps = 1;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String next : nextWord(word, dict)) {
                    if (hash.contains(next)) {
                        continue;
                    }
                    if (next.equals(end)) {
                        return steps;
                    }
                    queue.offer(next);
                    hash.add(next);
                }
            }
        }
        return  0;
    }
    private String replace(String s, int index, char c) {
        char[] newChar = s.toCharArray();
        newChar[index] = c;
        return new String(newChar);
    }
    private ArrayList<String> nextWord(String s, Set<String> dict) {
        ArrayList<String> newList = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                // if(s.charAt(i) == c) {
                //     continue;
                // }
                String newWord = replace(s, i, c);
                if (dict.contains(newWord)) {
                    newList.add(newWord);
                }
            }
        }
        return newList;
    }
}
