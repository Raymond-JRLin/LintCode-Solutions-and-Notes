// 554. Sort Integers (Map Reduce)
// Description
// Sort integers by Map Reduce framework.
//
// Have you met this question in a real interview?
// Example
// In the mapper, key is the document id which can be ignored, value is the integers.
//
// In the reducer, your can specify what the key / value is (this depends on how you implement your mapper class). For the output of the reducer class, the key can be anything you want, the value should be ordered. (the order is depending on when you output it)


/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class SortIntegers {

    public static class Map {
        public void map(int _, List<Integer> value,
                        OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);

            // value 会一直一个一个进来， 所以看到 reducer 里面是 List<List<Integer>> 类型
            // 所以对于 value 这个 list 内部， 我们可以先 sort 一遍， 赋予所有的 value 相同的 key， 然后不同的 list 放入 reducer 中处理
            Collections.sort(value);
            output.collect("key", value);
        }
    }

    public static class Reduce {
        public void reduce(String key, List<List<Integer>> values,
                           OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);

            // 因为 key 一样， 所以会拿到 list of list， 我们可以使用 PQ 来 sort

            if (values == null) {
                return;
            }
            List<Integer> result = new ArrayList<>();
            if (values.size() == 0) {
                output.collect(key, result);
                return;
            }
            Comparator<ListType> listComparator = new Comparator<ListType>() {
                @Override
                public int compare(ListType left, ListType right) {
                    return left.list.get(left.index) - right.list.get(right.index);
                }
            };
            int n = values.size();
            PriorityQueue<ListType> pq = new PriorityQueue<>(n, listComparator);
            //
            for (int i = 0; i < n; i++) {
                if (values.get(i).size() > 0) {
                    List<Integer> list = values.get(i);
                    ListType lt = new ListType(list, 0);
                    pq.offer(lt);
                }
            }
            //
            while (!pq.isEmpty()) {
                ListType lt = pq.poll();
                result.add(lt.list.get(lt.index));

                if (lt.index + 1 < lt.list.size()) {
                    lt.index++;
                    pq.offer(lt);
                }
            }
            output.collect(key, result);
        }
    }
}
class ListType{
    public List<Integer> list;
    public int index;
    public ListType(List<Integer> l, int i) {
        list = l;
        index = i;
    }
}
