// 1481. Unique Substring
// Description
// Given a string s, find all the unique substring with the length of k and sort the results in lexicographic order.
//
// Have you met this question in a real interview?
// Example
// Input: s = "caaab"
// k = 2
// Output:["aa","ab","ca"]


public class Solution {
    /**
     * @param s: a string
     * @param k: an integer
     * @return: all unique substring
     */
    public List<String> uniqueSubstring(String s, int k) {
        // Write your code here
        if (s == null || s.length() < k) {
            return Collections.emptyList();
        }

        // return mytry(s, k);

        return method2(s, k);
    }

    private List<String> method2(String s, int k) {
        // use TreeSet to set by itself
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < s.length() - k + 1; i++) {
            String curr = s.substring(i, i + k);
            set.add(curr);
        }
        List<String> result = new ArrayList<>(set);
        return result;
    }

    private List<String> mytry(String s, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length() - k + 1; i++) {
            String curr = s.substring(i, i + k);
            set.add(curr);
        }
        List<String> result = new ArrayList<>(set);
        Collections.sort(result, (o1, o2) -> {
            return o1.compareTo(o2);
        });
        return result;
    }
}
