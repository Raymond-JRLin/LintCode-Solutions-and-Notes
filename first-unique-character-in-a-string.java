// 209. First Unique Character in a String
// Description
// Find the first unique character in a given string. You can assume that there is at least one unique character in the string.
//
// Have you met this question in a real interview?
// Example
// For "abaccdeff", return 'b'.
//
// Challenge
// 不使用额外的存储空间。


public class Solution {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here

        // return mytry(str);

        return method2(str);
    }

    private char method2(String s) {
        // data streaming version
        DataStream ds = new DataStream();
        for (char c : s.toCharArray()) {
            ds.add(c);
        }
        ListCharNode curr = ds.dummy;
        // while (curr != null) {
        //     System.out.print(curr.val + " -> ");
        //     curr = curr.next;
        // }
        return ds.getFirstUnique();
    }
    private class ListCharNode {
        private char val;
        private ListCharNode next;

        public ListCharNode(char c) {
            this.val = c;
            this.next = null;
        }
    }
    private class DataStream {
        // 测试了一下， 我认为 map 用来控制第二次遇到， set 控制第三次以及第三次以上
        // ListCharNode 有 next， 这里 map 就是指向上一个， 这样便于删除遇到的重复数， 用 map 得到 prev 节点， 用 node 中的 next 连接下一个
        private Map<Character, ListCharNode> map; // <char, previous ListCharNode>
        private Set<Character> duplicated;
        private ListCharNode dummy; // 最终 dummy 的这条链连接的是依次遇到的 unique char
        private ListCharNode tail;

        public DataStream() {
            map = new HashMap<>();
            duplicated = new HashSet<>();
            dummy = new ListCharNode('.');
            tail = dummy;
        }

        public void add(char c) {
            // 如果已经是第三次及以上遇到， 直接跳过。 在第一次遇到的时候并没有加入 set
            if (duplicated.contains(c)) {
                return;
            }
            // 第一次遇到， 连 map 中都没有
            if (!map.containsKey(c)) {
                ListCharNode node = new ListCharNode(c);
                map.put(c, tail);
                tail.next = node;
                tail = node;
                return;
            }
            // map 之前存过， 又遇到了， 这是第二次
            ListCharNode prev = map.get(c); // 对应的上一个 node
            prev.next = prev.next.next; // 在链表中 remove 当前的又遇到的 node
            if (prev.next == null) {
                // 刚刚 remove 掉的是 tail
                tail = prev;
            } else {
                // 是中间的某个 node， 那么 next 的 map 对应的 prev 要重新设置一下
                map.put(prev.next.val, prev);
            }
            map.remove(c); // 移除 map 中的 pair
            duplicated.add(c); // 加入 set 中， 下一次再遇到直接略过
        }

        public char getFirstUnique() {
            return dummy.next.val;
        }
    }

    private char mytry(String s) {
        Map<Character, Integer> map = new HashMap<>(); // <char, freq>
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return 'a';
    }
}
