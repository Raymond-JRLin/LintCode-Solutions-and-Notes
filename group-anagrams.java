// 772. Group Anagrams
// 中文English
// Given an array of strings, group anagrams together.
//
// Example
// Example 1:
//
// Input:
// ["eat","tea","tan","ate","nat","bat"]
// Output:
// [["ate","eat","tea"],
//  ["bat"],
//  ["nat","tan"]]
// Example 2:
//
// Input:
// ["eat","nowhere"]
// Output:
// [["eat"],
//  ["nowhere"]]
// Notice
// All inputs will be in lower-case.


public class Solution {
    /**
     * @param strs: the given array of strings
     * @return: The anagrams which have been divided into groups
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // write your code here
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }

        return mytry(strs);
    }
    private List<List<String>> mytry(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String ori = String.valueOf(arr);
            // if (map.containsKey(ori)) {
            //     List<String> list = map.get(ori);
            //     list.add(s);
            //     map.put(ori, list);
            // } else {
            //     List<String> list = new ArrayList<>();
            //     list.add(s);
            //     map.put(ori, list);
            // }
            List<String> list = map.getOrDefault(ori, new ArrayList<String>());
            list.add(s);
            map.put(ori, list);
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
