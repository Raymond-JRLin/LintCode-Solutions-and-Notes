// 1486. Function Runtime
// Description
// Given a series of descriptions of when the function enters and exits, ask how long each function will run.
//
// The function string length does not exceed 1010, the description number does not exceed 1000010000, and the time does not exceed 1e91e9
// All descriptions are in chronological order and the input is guaranteed to be correct. Each function first enters and then exits
// Ascending output according to function name dictionary order
// Have you met this question in a real interview?
// Example
// Give s=["F1 Enter 10","F2 Enter 18","F2 Exit 19","F1 Exit 20"]，return["F1|10","F2|1"].
//
// Explanation:
// F1 enters from time 10, exits at time 20, and the running time is 10,
// F2 enters from time 18, exits at time 19, and the running time is 1.
// Give s=["F1 Enter 10","F1 Exit 18","F1 Enter 19","F1 Exit 20"]，return["F1|9"].
//
// Explanation:
// F1 enters from time 10, exits at time 18 and enters from time 19,
// exits at time 20, and the total running time is 9.


public class Solution {
    /**
     * @param a: The Descriptions
     * @return: Every functions' runtime
     */
    public String[] getRuntime(String[] a) {
        // Write your code here
        if (a == null || a.length == 0) {
            return new String[0];
        }

        return mytry(a);
    }

    private String[] mytry(String[] a) {
        int n = a.length;
        Map<String, Integer> map = new HashMap<>(); // <name, time>
        for (String s : a) {
            String[] arr = s.split(" ");
            String name = arr[0];
            String ope = arr[1];
            int t = Integer.valueOf(arr[2]);
            int prev = map.getOrDefault(name, 0);
            if (ope.equals("Enter")) {
                prev -= t;
            } else {
                prev += t;
            }
            map.put(name, prev);
        }

        List<String> names = new ArrayList<>(map.keySet());
        Collections.sort(names);

        int len = names.size();
        String[] result = new String[len];
        for (int i = 0; i < len; i++) {
            result[i] = names.get(i) + "|" + map.get(names.get(i));
        }
        return result;
    }
}
