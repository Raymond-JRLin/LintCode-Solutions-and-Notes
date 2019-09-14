// 541. Zigzag Iterator II
// 中文English
// Follow up Zigzag Iterator: What if you are given k 1d vectors? How well can your code be extended to such cases? The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
//
// Example
// Example1
//
// Input: k = 3
// vecs = [
//     [1,2,3],
//     [4,5,6,7],
//     [8,9],
// ]
// Output: [1,4,8,2,5,9,3,6,7]
// Example2
//
// Input: k = 3
// vecs = [
//     [1,1,1]
//     [2,2,2]
//     [3,3,3]
// ]
// Output: [1,2,3,1,2,3,1,2,3]


public class ZigzagIterator2 {
    /**
     * @param vecs a list of 1d vectors
     */

    // use a ArrayList to store every iterator for Integer in each ArrayList of List
    ArrayList<Iterator<Integer>> it;
    // int size; // cannot have this definition
    int flag;
    public ZigzagIterator2(ArrayList<ArrayList<Integer>> vecs) {
        // initialize your data structure here.
        it = new ArrayList<>();
        for (ArrayList<Integer> list : vecs) {
            if (list.size() > 0) {
                // only add valid list iterator
                it.add(list.iterator());
            }
        }
        // size = vecs.size(); // we would change the size of ArrayList so we cannot define it here
        flag = 0;
    }
    // k 个 更要注意如何判断是哪个 list
    public int next() {
        // Write your code here
        // 要么在这一开始改变 flag， 要么得到结果之后就改变 flag 供下一次使用
        // get current result
        int result = it.get(flag).next();
        // same reason for size here, we cannot define size strictly
        // move to next line
        if (it.get(flag).hasNext()) {
            // if current line has more element, move to next directly
            flag = (flag + 1) % it.size();
        } else {
            // if it's null, we should remove it
            it.remove(flag);
            // move to next, careful that size has been changed
            if (it.size() > 0) {
                // attention: java.lang.ArithmeticException: / by zero
                flag = flag % it.size();
            }
        }
        return result;
    }

    public boolean hasNext() {
        // Write your code here
        return it.size() > 0;
    }
}

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution = new ZigzagIterator2(vecs);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */
