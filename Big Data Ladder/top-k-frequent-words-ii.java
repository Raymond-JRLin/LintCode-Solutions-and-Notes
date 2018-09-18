// 550. Top K Frequent Words II
// Description
// Find top k frequent words in realtime data stream.
//
// Implement three methods for Topk Class:
//
// TopK(k). The constructor.
// add(word). Add a new word.
// topk(). Get the current top k frequent words.
// If two words have the same frequency, rank them by alphabet.
//
// Have you met this question in a real interview?
// Example
// TopK(2)
// add("lint")
// add("code")
// add("code")
// topk()
// >> ["code", "lint"]


public class TopK {
    public int k;
    public HashMap<String, Integer> map;
    // 动态的加入， 修改， 因为要能够支持在数据结构中查找是否存在， 所以不能使用 PQ + Pair 了
    // method 1: based on TreeMap
    // public TreeMap<String, Integer> tree;
    // method 2: based on NavigableSet
    public NavigableSet<String> set;
    private Comparator<String> myComparator = new Comparator<String>() {
        @Override
        public int compare(String a, String b) {
            // if (a.equals(b)) {
            //     return 0;
            // }
            int countA = map.get(a);
            int countB = map.get(b);
            if (countA != countB) {
                return countB - countA; // descending of count order
            } else {
                return a.compareTo(b); // ascending of alphabetic order
            }
        }
    };

    public TopK(int k) {
        // initialize your data structure here
        this.k = k;
        map = new HashMap<String, Integer>();
        // tree = new TreeMap<String, Integer>(myComparator);
        set = new TreeSet<String>(myComparator); // NavigableSet is a abstract class - interface, implementing from TreeSet class
    }

    public void add(String word) {
        // Write your code here
        // method 1: based on TreeMap
        // HashMap
        // if (map.containsKey(word)) {
        //     if (tree.containsKey(word)) {
                    /*****************************************
                    * this if cannot be put outside outer if, and must be ahead of map operation! why???
                    * I think because we use HashMap in Comparator of TreeMap/TreeSet
                    *****************************************/
        //         tree.remove(word);
        //     }
        //     map.put(word, map.get(word) + 1);
        // } else {
        //     map.put(word, 1);
        // }
        // // TreeMap: anyway we put this new into TreeMap
        // tree.put(word, map.get(word));
        // // if its size is over, then remove last one
        // if (tree.size() > k) {
        //     tree.pollLastEntry();
        // }

        // method 2: based on NavigableSet
        if (map.containsKey(word)) {
            if (set.contains(word)) {
                set.remove(word);
            }
            map.put(word, map.get(word) + 1);
        } else {
            map.put(word, 1);
        }
        set.add(word);
        if (set.size() > k) {
            set.pollLast();
        }
    }

    public List<String> topk() {
        // Write your code here
        // method 1: based on TreeMap
        List<String> result = new ArrayList<>();
        // Set<String> set = tree.keySet();
        // method 2: based on NavigableSet
        for (String string : set) {
            result.add(string);
        }
        return result;
    }
}
