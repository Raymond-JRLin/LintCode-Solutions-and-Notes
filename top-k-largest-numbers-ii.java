// 545. Top k Largest Numbers II
// 中文English
// Implement a data structure, provide two interfaces:
//
// add(number). Add a new number in the data structure.
// topk(). Return the top k largest numbers in this data structure. k is given when we create the data structure.
// Example
// Example1
//
// Input:
// s = new Solution(3);
// s.add(3)
// s.add(10)
// s.topk()
// s.add(1000)
// s.add(-99)
// s.topk()
// s.add(4)
// s.topk()
// s.add(100)
// s.topk()
//
// Output:
// [10, 3]
// [1000, 10, 3]
// [1000, 10, 4]
// [1000, 100, 10]
//
// Explanation:
// s = new Solution(3);
// >> create a new data structure, and k = 3.
// s.add(3)
// s.add(10)
// s.topk()
// >> return [10, 3]
// s.add(1000)
// s.add(-99)
// s.topk()
// >> return [1000, 10, 3]
// s.add(4)
// s.topk()
// >> return [1000, 10, 4]
// s.add(100)
// s.topk()
// >> return [1000, 100, 10]
// Example2
//
// Input:
// s = new Solution(1);
// s.add(3)
// s.add(10)
// s.topk()
// s.topk()
//
// Output:
// [10]
// [10]
//
// Explanation:
// s = new Solution(1);
// >> create a new data structure, and k = 1.
// s.add(3)
// s.add(10)
// s.topk()
// >> return [10]
// s.topk()
// >> return [10]


public class Solution {
    PriorityQueue<Integer> pq;
    int k; // create a variable k in this data structure so we can use it in different method
    public Solution(int k) {
        // initialize your data structure here.
        this.k = k;
        pq = new PriorityQueue<>(); // min-heap
    }

    public void add(int num) {
        // Write your code here
        if (pq.size() < k) {
            pq.offer(num);
        } else {
            if (num > pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }
    }

    public List<Integer> topk() {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        // because we cannot extract values in PQ, so we should use an iterator
        Iterator it = pq.iterator();
        while (it.hasNext()) {
            int num = (int)it.next(); // remember transform Obeject to int
            result.add(num);
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
};
