// 549. Top K Frequent Words (Map Reduce)
// Description
// Find top k frequent words with map reduce framework.
//
// The mapper's key is the document id, value is the content of the document, words in a document are split by spaces.
//
// For reducer, the output should be at most k key-value pairs, which are the top k words and their frequencies in this reducer. The judge will take care about how to merge different reducers' results to get the global top k frequent words, so you don't need to care about that part.
//
// The k is given in the constructor of TopK class.
//
// For the words with same frequency, rank them with alphabet.
//
// Have you met this question in a real interview?
// Example
// Given document A =
//
// lintcode is the best online judge
// I love lintcode
// and document B =
//
// lintcode is an online judge for coding interview
// you can test your code online at lintcode
// The top 2 words and their frequencies should be
//
// lintcode, 4
// online, 3


/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
class Pair {
    String key;
    int freq;
    Pair(String k, int f) {
        key = k;
        freq = f;
    }
}
public class TopKFrequentWords {

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            String content = value.content;
            String[] array = content.split(" ");
            for (String word : array) {
                // only collect non-empty string, there would be added space into input on purpose, e.g. [{"id":1,"content":"This is  the content of document1"}, {"id":2,"content":"This is the       content of document3"}]
                if (word.length() > 0) {
                    output.collect(word, 1);
                }
            }
        }
    }

    public static class Reduce {
        PriorityQueue<Pair> queue = null;
        int k;
        private Comparator<Pair> pairComparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                if (a.freq != b.freq) {
                    return a.freq - b.freq; // min-heap
                } else {
                    return b.key.compareTo(a.key);
                }
            }
        };

        public void setup(int k) {
            // initialize your data structure here
            this.k = k;
            queue = new PriorityQueue<Pair>(k, pairComparator);
        }

        public void reduce(String key, Iterator<Integer> values) {
            // Write your code here
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            Pair pair = new Pair(key, sum);
            if (queue.size() < k) {
                queue.offer(pair);
            } else {
                Pair node = queue.peek();
                // compare peek node and this new pair, bigger one would be offered into queue
                if (pairComparator.compare(pair, node) > 0) {
                    // means pair > node(e.g. peek)
                    queue.poll();
                    queue.add(pair);
                }
            }
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
            List<Pair> list = new ArrayList<>();
            while (!queue.isEmpty()) {
                list.add(queue.poll());
            }
            // because we use min-heap, so we should poll Pair from queue reversedly
            for (int i = list.size() - 1; i >= 0; i--) {
                String key = list.get(i).key;
                int value = list.get(i).freq;
                output.collect(key, value);
            }
        }
    }
}


answer:

/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
class Pair {
    String key;
    int value;

    Pair(String key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class TopKFrequentWords {

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int id = value.id;
            String content = value.content;
            String[] words = content.split(" ");
            for (String word : words)
                if (word.length() > 0) {
                    output.collect(word, 1);
                }
        }
    }

    public static class Reduce {
        private PriorityQueue<Pair> Q = null;
        private int k;

        private Comparator<Pair> pairComparator = new Comparator<Pair>() {
            public int compare(Pair left, Pair right) {
                if (left.value != right.value) {
                    return left.value - right.value;
                }
                return right.key.compareTo(left.key);
            }
        };

        public void setup(int k) {
            // initialize your data structure here
            this.k = k;
            Q = new PriorityQueue<Pair>(k, pairComparator);
        }

        public void reduce(String key, Iterator<Integer> values) {
            // Write your code here
            int sum = 0;
            while (values.hasNext()) {
                    sum += values.next();
            }

            Pair pair = new Pair(key, sum);
            if (Q.size() < k) {
                Q.add(pair);
            } else {
                Pair peak = Q.peek();
                if (pairComparator.compare(pair, peak) > 0) {
                    Q.poll();
                    Q.add(pair);
                }
            }
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
            List<Pair> pairs = new ArrayList<Pair>();
            while (!Q.isEmpty()) {
                pairs.add(Q.poll());
            }

            // reverse result
            int n = pairs.size();
            for (int i = n - 1; i >= 0; --i) {
                Pair pair = pairs.get(i);
                output.collect(pair.key, pair.value);
            }
        }
    }
}
