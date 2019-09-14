// 582. Word Break II
// 中文English
// Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
//
// Return all such possible sentences.
//
// Example
// Example 1:
//
// Input："lintcode"，["de","ding","co","code","lint"]
// Output：["lint code", "lint co de"]
// Explanation：
// insert a space is "lint code"，insert two spaces is "lint co de".
// Example 2:
//
// Input："a"，[]
// Output：[]
// Explanation：dict is null.


public class Solution {
    /**
     * @param s a string
     * @param wordDict a set of words
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        if (wordDict == null || wordDict.size() == 0) {
            return result;
        }
        int n = s.length();
        String string = new String();

        // method 0: simple "naked" DFS, it would TLE starting from following case:
        // "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
        // pureDFS(s, wordDict, 0, string, result);
        // return result;

        // method 1: optimized DFS by prunning useless calculation
        // reference: http://fisherlei.blogspot.com/2013/11/leetcode-wordbreak-ii-solution.html
        // boolean[] possible = new boolean[n + 1]; // record [i,n] 这个区间上是否有解
        // // initialization
        // for (int i = 0; i < n + 1; i++) {
        //     possible[i] = true;
        // }
        // prunningDFS(s, wordDict, 0, string, result, possible);
        // return result;

        // method 2: use DP to get the possible array in advance
        boolean[] possible = new boolean[n + 1];
        possible[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                possible[i] = false;
                if (possible[j + 1] && wordDict.contains(s.substring(i, j + 1))) {
                    possible[i] = true;
                    break;
                }
            }
        }
        // prunningDFSwithDP(s, wordDict, 0, string, result, possible);
        dpDFS(s, wordDict, 0, string, result, possible);
        return result;
    }
    private void pureDFS(String s, Set<String> dict, int index, String string, List<String> result) {
        if (index == s.length()) {
            result.add(string.trim());
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1); // 从当前 index 往后一位位查找是否在 dict 里面存在，如果有就加上
            if (dict.contains(sub)) {
                string += sub + " ";
                pureDFS(s, dict, i + 1, string, result); // 从下一位继续往后找
                string = string.substring(0, string.trim().length() - sub.length()); // attention we should do trim() here to string then we can get correct substring
            }
        }
    }
    private void prunningDFS(String s, Set<String> dict, int index, String string, List<String> result, boolean[] possible) {
        if (index == s.length()) {
            result.add(string.trim());
            return;
        }
        if (!possible[index]) {
            return; // if there is no possible word-break, then just return directly
            // reference: http://www.cnblogs.com/yuzhangcmu/p/4037299.html
        }
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1); // 从当前 index 往后一位位查找是否在 dict 里面存在，如果有就加上
            if (dict.contains(sub) && possible[i + 1]) {
                string += sub + " ";
                // int originalSize = result.size(); // 先计算继续 DFS 之前的最终结果 List 的 size
                prunningDFS(s, dict, i + 1, string, result, possible); // 从下一位继续往后找
                // if (originalSize == result.size()) {
                //     // 如果 size 没变， 表示后面没有可能的结果了， 下一次就无需往下查询
                //     possible[i + 1] = false;
                // }
                string = string.substring(0, string.trim().length() - sub.length()); // attention we should do trim() here to string then we can get correct substring
            }
        }
    }
    private void prunningDFSwithDP(String s, Set<String> dict, int index, String string, List<String> result, boolean[] possible) {
        if (index == s.length()) {
            result.add(string.trim());
            return;
        }
        if (!possible[index]) {
            return; // if there is no possible word-break, then just return directly
            // reference: http://www.cnblogs.com/yuzhangcmu/p/4037299.html
        }
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1); // 从当前 index 往后一位位查找是否在 dict 里面存在，如果有就加上
            if (dict.contains(sub) && possible[i + 1]) {
                string += sub + " ";
                // int originalSize = result.size(); // 先计算继续 DFS 之前的最终结果 List 的 size
                prunningDFSwithDP(s, dict, i + 1, string, result, possible); // 从下一位继续往后找
                string = string.substring(0, string.trim().length() - sub.length()); // attention we should do trim() here to string then we can get correct substring
            }
        }
    }
    private void dpDFS(String s, Set<String> dict, int index, String string, List<String> result, boolean[] possible) {
        if (index == s.length()) {
            result.add(string.trim());
            return;
        }
        if (!possible[index]) {
            return; // if there is no possible word-break, then just return directly
            // reference: http://www.cnblogs.com/yuzhangcmu/p/4037299.html
        }
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1); // 从当前 index 往后一位位查找是否在 dict 里面存在，如果有就加上
            if (!dict.contains(sub)) {
                continue;
            }
            if (!possible[i + 1]) {
                continue;
            }
            string += sub + " ";
            dpDFS(s, dict, i + 1, string, result, possible); // 从下一位继续往后找
            string = string.substring(0, string.trim().length() - sub.length()); // attention we should do trim() here to string then we can get correct substring
        }
    }
}
