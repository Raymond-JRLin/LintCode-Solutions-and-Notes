// 540. Zigzag Iterator
// 中文English
// Given two 1d vectors, implement an iterator to return their elements alternately.
//
// Example
// Example1
//
// Input: v1 = [1, 2] and v2 = [3, 4, 5, 6]
// Output: [1, 3, 2, 4, 5, 6]
// Explanation:
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
// Example2
//
// Input: v1 = [1, 1, 1, 1] and v2 = [3, 4, 5, 6]
// Output: [1, 3, 1, 4, 1, 5, 1, 6]


public class ZigzagIterator {
    /**
     * @param v1 v2 two 1d vectors
     */

    Iterator<Integer> it1 = null;
    Iterator<Integer> it2 = null;
    int flag;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        // initialize your data structure here.
        it1 = v1.iterator();
        it2 = v2.iterator();
        flag = 1;
    }

    // 感觉这道题难点就在于如何用一个 flag 判定该到 v1 还是 v2
    public int next() {
        // Write your code here
        if (flag == 1) {
            if (it1.hasNext()) {
                flag++;
                return it1.next();
            } else {
                flag++;
                return it2.next();
            }
        } else {
            if (it2.hasNext()) {
                flag--;
                return it2.next();
            } else {
                flag--;
                return it1.next();
            }
        }
    }

    public boolean hasNext() {
        // Write your code here
        return it1.hasNext() || it2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution = new ZigzagIterator(v1, v2);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */
