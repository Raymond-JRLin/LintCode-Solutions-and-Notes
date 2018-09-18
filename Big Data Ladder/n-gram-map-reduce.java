// 537. N-Gram (Map Reduce)
// Description
// 给出若干字符串和数字 N。用 Map Reduce 的方法统计所有 N-Grams 及其出现次数 。以字母为粒度。
//
// Have you met this question in a real interview?
// Example
// Given N = 3
// doc_1: "abcabc"
// doc_2: "abcabc"
// doc_3: "bbcabc"
//
// The final result should be:
//
// [
//   "abc": ５,
//   "bbc": 1,
//   "bca": 3,
//   "cba": 3
// ]


/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class NGram {

    public static class Map {
        public void map(String _, int n, String str,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, Integer value);
            int length = str.length();
            // HashMap<String, Integer> map = new HashMap<String, Integer>();
            for (int i = 0; i <= length - n; i++) {
                String sub = str.substring(i, i + n);
                // if (map.containsKey(sub)) {
                //     map.put(sub, map.get(sub) + 1);
                // } else {
                //     map.put(sub, 1);
                // }
                // we don't need such sum-up operation, then reduce has no meaning, we only care each substring and output this substring with count of 1, then let reduce do addition
                output.collect(sub, 1);
            }
            // don't need following, otherwise we already get the sum
            // for (Map.Entry<String, Integer> entry : map.entrySet()) {
            //     String outputKey = entry.getKey();
            //     Integer outputValue = entry.getValue();
            //     output.collect(outputKey, outputValue);
            // }
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
}a  
