// 499. Word Count (Map Reduce)
// Description
// Using map reduce to count word frequency.
//
// https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html#Example%3A+WordCount+v1.0
//
// Example
// chunk1: "Google Bye GoodBye Hadoop code"
// chunk2: "lintcode code Bye"
//
//
// Get MapReduce result:
//     Bye: 2
//     GoodBye: 1
//     Google: 1
//     Hadoop: 1
//     code: 2
//     lintcode: 1


/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class WordCount {

    public static class Map {
        public void map(String key, String value, OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            StringTokenizer tokenizer = new StringTokenizer(value);
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                output.collect(word, 1);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            output.collect(key, sum);
        }
    }
}
