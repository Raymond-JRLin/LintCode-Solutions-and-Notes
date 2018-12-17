// 30. Insert Interval
// Description
// Given a non-overlapping interval list which is sorted by start point.
//
// Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).
//
// Have you met this question in a real interview?
// Example
// Insert (2, 5) into [(1,2), (5,9)], we get [(1,9)].
//
// Insert (3, 4) into [(1,2), (5,9)], we get [(1,2), (3,4), (5,9)].


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
    /*
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // write your code here
        if (intervals == null || intervals.size() == 0) {
            List<Interval> result = new ArrayList<>();
            result.add(newInterval);
            return result;
        }

        // return mytry(intervals, newInterval);

        // return method1(intervals, newInterval);

        return method2(intervals, newInterval);
    }


    private List<Interval> method2(List<Interval> intervals, Interval newInterval) {
        // method 2: for loop intervals list, but just to find the position where this newInterval should be inserted
        // reference: NC solution
        List<Interval> result = new ArrayList<>();
        int pos = 0;
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                result.add(interval);
                pos++;
            } else if (newInterval.end < interval.start) {
                result.add(interval);
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }
        result.add(pos, newInterval);
        return result;
    }

    private List<Interval> method1(List<Interval> intervals, Interval newInterval) {
        // method 1: for loop list and insert one by one into result list
        // reference: http://bangbingsyb.blogspot.com/2014/11/leetcode-insert-interval.html
        List<Interval> result = new ArrayList<>();
        boolean isInserted = false;
        for (Interval interval : intervals) {
            if (isInserted) {
                // inserted this newInterval already
                result.add(interval);
                continue;
            }
            // do not insert newInterval yet
            // current end < new start, don't need to combine
            if (interval.end < newInterval.start) {
                result.add(interval);
                continue;
            }
            // new end < current start, found a non-overlap interval, insert it first and then current interval
            if (newInterval.end < interval.start) {
                result.add(newInterval);
                result.add(interval);
                isInserted = true;
                continue;
            }
            // case need to be combined, changed newInterval
            newInterval.start = Math.min(interval.start, newInterval.start);
            newInterval.end = Math.max(interval.end, newInterval.end);
        }
        // after for loop, we should check if it's inserted, if not, it should be inserted in the back
        if (!isInserted) {
            result.add(newInterval);
        }
        return result;
    }

    private List<Interval> mytry(List<Interval> intervals, Interval newInterval) {
        // 1st try: looks complicated and does not have a loop to check whether should merge continuously, which can only merge once so it's wrong
        // lessons:
        // 1 - we should use a loop to check whether result needs more merging operation after one merging;
        // 2 - cased needed to be merged are too much, so we can use cased that do not need merge to do if;
        // 3 - 应该思考合并后的情况是怎样的 [min{start1, start2}, max{end1, end2}]
        int start = newInterval.start;
        int n = intervals.size();
        int point = n;
        for (int i = 0; i < n; i++) {
            if (intervals.get(i).start > start) {
                point = i;
                break;
            }
        }
        intervals.add(point, newInterval);
        if (point == n) {
            merge(intervals.get(point - 1), newInterval, intervals, point);
            return intervals;
        } else if (point == 0) {
            merge(newInterval, intervals.get(point + 1), intervals, point + 1);
            return intervals;
        } else {
            int prev = point - 1;
            int next = point + 1;
            merge(intervals.get(prev), newInterval, intervals, point);
            merge(newInterval, intervals.get(next), intervals, point + 1);
            return intervals;
        }
    }
    private void merge(Interval prev, Interval curr, List<Interval> intervals, int pos) {
        // prev.start must < prev.end
        // but prev.end can be several positions:
        // -------------------|------------------|------------------
        //         x     curr.start    x     curr.end      x
        if (prev.end < curr.start) {
            // no need to merge
            return;
        }
        if (prev.end >= curr.start && prev.end <= curr.end) {
            // case: [[1, 2], [2, 5]]; [[1, 5], [2, 7]]
            prev.end = curr.end;
            intervals.remove(pos);
            return;
        }
        if (prev.end > curr.end) {
            // case: [[1, 5], [2, 3]]
            intervals.remove(pos);
        }
    }
};
