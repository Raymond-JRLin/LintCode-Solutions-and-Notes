// 830. String Sort
// 中文English
// Given a string, sort the string with the first keyword which is the most commonly used characters and the second keyword which is the dictionary order.
//
// Example
// Example1
//
// Input:  str = "bloomberg"
// Output: "bbooeglmr"
// Explanation:
// 'b' and 'o' appear the most frequently, but the dictionary sequence of 'b' is the smaller than 'o', so 'b' is ranked first, followed by 'o', and so on.
// Example2
//
// Input:  str = "lintcode"
// Output: "cdeilnot"
// Explanation:
// All characters appear the same number of times, so directly in accordance with the dictionary order.
// Notice
// The length of string is less than 10000.
// All characters are lowercase


public class Solution {
    /**
     * @param str: the string that needs to be sorted
     * @return: sorted string
     */
    public String stringSort(String str) {
        // Write your code here
        if (str == null || str.length() == 0) {
            return str;
        }

        // return mytry(str);

        return method1(str);
    }

    private String method1(String str) {
        Map<Character, Integer> map = new HashMap<>(); // <char, frequency>
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return Integer.compare(o1.getKey() - 'a', o2.getKey() - 'a');
                } else {
                    return Integer.compare(o2.getValue(), o1.getValue());
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : list) {
            char c = entry.getKey();
            int f = entry.getValue();
            for (int i = 0; i < f; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private String mytry(String str) {
        int[] nums = new int[26];
        for (char c : str.toCharArray()) {
            nums[c - 'a']++;
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.freq == o2.freq) {
                    return Integer.compare(o1.c - 'a', o2.c - 'a');
                } else {
                    return Integer.compare(o2.freq, o1.freq);
                }
            }
        });
        for (int i = 0; i < 26; i++) {
            if (nums[i] != 0) {
                char c = (char) (i + 'a');
                Node node = new Node(c, nums[i]);
                pq.offer(node);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            for (int i = 0; i < curr.freq; i++) {
                sb.append(curr.c);
            }
        }
        return sb.toString();
    }
    private class Node {
        public char c;
        public int freq;
        public Node(char c, int f) {
            this.c = c;
            freq = f;
        }
    }
}
