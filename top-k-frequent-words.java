// 471. Top K Frequent Words
// 中文English
// Given a list of words and an integer k, return the top k frequent words in the list.
//
// Example
// Example 1:
//
// Input:
//   [
//     "yes", "lint", "code",
//     "yes", "code", "baby",
//     "you", "baby", "chrome",
//     "safari", "lint", "code",
//     "body", "lint", "code"
//   ]
//   k = 3
// Output: ["code", "lint", "baby"]
// Example 2:
//
// Input:
//   [
//     "yes", "lint", "code",
//     "yes", "code", "baby",
//     "you", "baby", "chrome",
//     "safari", "lint", "code",
//     "body", "lint", "code"
//   ]
//   k = 4
// Output: ["code", "lint", "baby", "yes"]
// Challenge
// Do it in O(nlogk) time and O(n) extra space.
//
// Notice
// You should order the words by the frequency of them in the return list, the most frequent one comes first. If two words has the same frequency, the one with lower alphabetical order come first.
//


public class Solution {
    /**
     * @param words an array of string
     * @param k an integer
     * @return an array of string
     */
    public String[] topKFrequentWords(String[] words, int k) {
        // Write your code here
        if (words == null || words.length == 0) {
            return null;
        }
        if (k <= 0) {
            return new String[0];
        }
        String[] result = new String[k];
        // calculate each word's frequency and make a map
        int n = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], 1);
            } else {
                map.put(words[i], map.get(words[i]) + 1);
            }
        }
        // create a PQ as min heap based on Pair class
        // override Comparator seperately
        Comparator<Pair> pairComparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                if (a.freq == b.freq) {
                    // when frequencies are equal, order lexicographically
                    return b.word.compareTo(a.word);
                } else {
                    return a.freq - b.freq;
                }
            }
        };
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(k, pairComparator);
        // put frequency in map into PQ to do a min-heap
        for (String word : map.keySet()) {
            Pair pair = new Pair(word, map.get(word));
            if (pq.size() < k) {
                pq.offer(pair);
            } else {
                // if (pair.freq > pq.peek().freq) {
                //     pq.poll();
                //     pq.offer(pair);
                // }
                // this if condition is not correct, since when we add elements int o PQ, we should also compare their alphabetical order if their frequencies are equal
                // so it's more convenient to write Comparator seperately and thus we can use it here too
                if (pairComparator.compare(pair, pq.peek()) > 0) {
                    pq.poll();
                    pq.offer(pair);
                }
            }
        }
        // extrac k element from PQ and put them into result as descending of freq
        for (int i = pq.size() - 1; i >= 0; i--) {
            result[i] = pq.poll().word;
        }
        return result;
    }
    // private Comparator<Pair> pairComparator = new Comparator<Pair>() {
    //     @Override
    //     public int compare(Pair a, Pair b) {
    //         if (a.freq == b.freq) {
    //             // when frequencies are equal, order lexicographically
    //             return b.word.compareTo(a.word);
    //         } else {
    //             return a.freq - b.freq;
    //         }
    //     }
    // };
}
class Pair {
    String word;
    int freq;
    Pair(String w, int f) {
        word = w;
        freq = f;
    }
}
