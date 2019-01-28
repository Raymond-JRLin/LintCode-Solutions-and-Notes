// 839. Merge Two Sorted Interval Lists
// Description
// Notes
// Testcase
// Judge
// Discuss
// Merge two sorted (ascending) lists of interval and return it as a new sorted list. The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.
//
//  Notice
// The intervals in the given list do not overlap.
// The intervals in different lists may overlap.
// Have you met this question in a real interview?
// Example
// Example1
//
// Input: list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
// Output: [(1,4),(5,6)]
// Explanation:
// (1,2),(2,3),(3,4) --> (1,4)
// (5,6) --> (5,6)
// Example2
//
// Input: list1 = [(1,2),(3,4)] and list2 = [(4,5),(6,7)]
// Output: [(1,2),(3,5),(6,7)]
// Explanation:
// (1,2) --> (1,2)
// (3,4),(4,5) --> (3,5)
// (6,7) --> (6,7)



/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        if (list1 == null || list1.size() == 0 || list2 == null || list2.size() == 0) {
            return Collections.emptyList();
        }

        return method1(list1, list2);
    }

    private List<Interval> method1(List<Interval> list1, List<Interval> list2) {
        // similiar to merge 2 sorted array
        List<Interval> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        Interval prev = null, curr = null;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).start < list2.get(j).start) {
                curr = list1.get(i++);
            } else {
                curr = list2.get(j++);
            }
            prev = merge(prev, curr, result);
        }
        while (i < list1.size()) {
            curr = list1.get(i++);
            prev = merge(prev, curr, result);
        }
        while (j < list2.size()) {
            curr = list2.get(j++);
            prev = merge(prev, curr, result);
        }
        // last one
        if (prev != null) {
            result.add(prev);
        }
        return result;
    }
    private Interval merge(Interval prev, Interval curr, List<Interval> result) {
        // 1st time
        if (prev == null) {
            return curr;
        }
        // no overlap
        if (prev.end < curr.start) {
            result.add(prev);
            return curr;
        }
        // overlap
        prev.end = Math.max(curr.end, prev.end);
        return prev;
    }
}
