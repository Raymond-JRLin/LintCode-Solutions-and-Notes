// 503. Anagram (Map Reduce)
// 中文English
// Use Map Reduce to find anagrams in a given list of words.
//
// Example
// Example 1:
//
// Input: "lint lint lnit ln"
// Output:
//   ["lint", "lint", "lnit"]
//   ["ln"]
// Example 2:
//
// Input: "ab ba cab"
// Output:
//   ["ab", "ba"]
//   ["cab"]


/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class Anagram {

    public static class Map {
        public void map(String key, String value,
                        OutputCollector<String, String> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, String value);

            // 要牢牢把握住 MapReduce 的根本： mapper 是用来拆分的， 赋予相同的 value 相同的 key， reducer 是用来合并的， 把所有具有相同的 key 的 value 整合在一起

            // mapper 这里就要考虑， 如何让 anagram 有相同的 key？
            // 根据 Anagram 非 MapReduce 题， 我们知道我们用的方法是 String -> char[] -> sort -> String, 那这里我们就可以用 sort 之后的 string 作为 anagram 的 key， 本身的 string 作为 value

            StringTokenizer st = new StringTokenizer(value);
            while (st.hasMoreTokens()) {
                String string = st.nextToken();
                char[] array = string.toCharArray();
                Arrays.sort(array);
                String sortedStr = String.valueOf(array);
                output.collect(sortedStr, string);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values,
                           OutputCollector<String, List<String>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<String> value);

            // reducer 会把所有相同的 anagram 合并在一起
            List<String> result = new ArrayList<String>();
            while (values.hasNext()) {
                result.add(values.next());
            }
            output.collect(key, result);
        }
    }
}
