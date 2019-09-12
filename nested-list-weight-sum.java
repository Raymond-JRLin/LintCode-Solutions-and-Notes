// 551. Nested List Weight Sum
// 中文English
// Given a nested list of integers, return the sum of all integers in the list weighted by their depth. Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Example
// Example 1:
//
// Input: the list [[1,1],2,[1,1]],
// Output: 10.
// Explanation:
// four 1's at depth 2, one 2 at depth 1, 4 * 1 * 2 + 1 * 2 * 1 = 10
// Example 2:
//
// Input: the list [1,[4,[6]]],
// Output: 27.
// Explanation:
// one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        // Write your code here
        int sum = 0;
        if (nestedList == null || nestedList.size() == 0) {
            return sum;
        }

        // method 1: recursion
        // sum = nestedSum(nestedList, 1);
        // return sum;

        // method 2: queue
        Queue<NestedInteger> queue = new LinkedList<>();
        // put all elements into queue first
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }
        int depth = 0;
        // poll one by one from queue
        while (!queue.isEmpty()) {
            depth++; // add one nested layer depth
            int size = queue.size(); // IMPORTANT! to make parallel polling
            for (int i = 0; i < size; i++) {
                // 这样就可以一次性用【相同的】 depth 解决同一 level 的元素
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    sum += ni.getInteger() * depth;
                } else {
                    for (NestedInteger nest : ni.getList()) {
                        queue.offer(nest);
                    }
                }
            }
        }
        return sum;
    }
    private int nestedSum(List<NestedInteger> list, int rank) {
        int sum = 0;
        // int nestRank = rank;
        for (NestedInteger nest : list) {
            if (nest.isInteger()) {
                // sum += nest.getInteger() * nestRank;
                sum += nest.getInteger() * rank;
            } else {
                // sum += nestedSum(nest.getList(), nestRank + 1);
                sum += nestedSum(nest.getList(), rank + 1);
            }
        }
        return sum;
    }
}
