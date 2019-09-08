// 133. Longest Word
// 中文English
// Given a dictionary, find all of the longest words in the dictionary.
//
// Example
// Example 1:
// 	Input: {
// 		"dog",
// 		"google",
// 		"facebook",
// 		"internationalization",
// 		"blabla"
// 		}
// 	Output: ["internationalization"]
//
//
// Example 2:
// 	Input:  {
// 		"like",
// 		"love",
// 		"hate",
// 		"yes"
// 		}
// 	Output: ["like", "love", "hate"]
//
//
// Challenge
// It's easy to solve it in two passes, can you do it in one pass?
//


class Solution {
    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    ArrayList<String> longestWords(String[] dictionary) {
        // write your code here
        ArrayList<String> result = new ArrayList<String>();
        if (dictionary == null || dictionary.length == 0) {
            return result;
        }

        // method 1: 2 passes
        // 1: find the max length first
        int max = 0;
        for (String str : dictionary) {
            max = Math.max(max, str.length());
        }
        for (String str : dictionary) {
            if (str.length() == max) {
                result.add(str);
            }
        }
        return result;

        // method 2: 1 pass for loop based on HashMap
        // HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        // int longest = 0;
        // for (String str : dictionary) {
        //     int length = str.length();
        //     if (map.containsKey(length)) {
        //         ArrayList<String> list = map.get(length);
        //         list.add(str);
        //         map.put(length, list);
        //     } else {
        //         ArrayList<String> list = new ArrayList<String>();
        //         list.add(str);
        //         map.put(length, list);
        //     }
        //     longest = Math.max(longest, length);
        // }
        // return map.get(longest);
    }
};
