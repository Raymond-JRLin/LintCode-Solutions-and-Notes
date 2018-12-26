// 1633. Strings That Satisfies The Condition
// Description
// Given a string target and a string array s, output all strings containing target(ie target is a subsequence of s[i]) in their original order in the array s
//
// s.length<=1000
// 1<=the sum of strings’ length in s,target<=100000
//
// Have you met this question in a real interview?
// Example
// Given target="google",s=["goooogle","abc","google","higoogle","googlg","gowogwle","gogle"],Return ["goooogle","google","higoogle","gowogwle"]
//
// Explanation:
// goooogle
// google
// higoogle
// gowogwle


public class Solution {
    /**
     * @param target: the target string
     * @param s:
     * @return: output all strings containing target  in s
     */
    public String[] getAns(String target, String[] s) {
        // Write your code here
        if (s == null || s.length == 0) {
            return new String[0];
        }

        return mytry(target, s);
    }

    private String[] mytry(String target, String[] strings) {
        // 2 pointers to check each string
        List<String> list = new ArrayList<>();
        for (String s : strings) {
            if (isSubseq(target, s)) {
                list.add(s);
            }
        }

        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    private boolean isSubseq(String target, String s) {
        int i = 0;
        int j = 0;
        while (i < target.length() && j < s.length()) {
            if (target.charAt(i) == s.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == target.length();
    }
}
