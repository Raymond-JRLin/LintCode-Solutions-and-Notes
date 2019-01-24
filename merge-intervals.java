// 156. Merge Intervals
// Description
// Given a collection of intervals, merge all overlapping intervals.
//
// Have you met this question in a real interview?
// Example
// Given intervals => merged intervals:
//
// [                     [
//   (1, 3),               (1, 6),
//   (2, 6),      =>       (8, 10),
//   (8, 10),              (15, 18)
//   (15, 18)            ]
// ]
// Challenge
// O(n log n) time and O(1) extra space.


/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        List<Interval> result = new ArrayList<>();
        // it cannot ensure that given list is sorted, so we should sort it
        // but it's Interval type, so we need to override compare method
        Collections.sort(intervals, new Comparator<Interval>() {
           @Override
           public int compare (Interval left, Interval right) {
               return left.start - right.start;
           }
        });
        // interval 我感觉比较特殊， 就是如果有重叠， 要更改的是前一个， 加入的也是前一个， 所以要同时拿到当前的和下一个去比较
        // Interval curr = intervals.get(0);
        // for (int i = 1; i < intervals.size(); i++) {
        //     Interval next = intervals.get(i);
        //     if (next.start <= curr.end) {
        //         // 有重叠
        //         curr.end = Math.max(curr.end, next.end); // 更改一下 curr, next 就直接废弃, curr 等待下一轮比较是否重叠， 只有在不重叠的时候才可以加入
        //     } else {
        //         // 没重叠
        //         result.add(curr); // 直接加入
        //         curr = next; // 切换 curr 到下一个
        //     }
        // }
        // // 此时注意还有最后一个要 double check
        // // 有两种可能： 1 - 最后一个 - next 和倒数第二个 - curr 有重叠， 更改了 curr， 废弃最后一个； 2 - 最后一个没有重叠， 倒数第二个 - curr 已经被加入了， curr 换成了 next， 即最后一个； 所以只要把 curr 加入就可以了
        // result.add(curr);
        // return result;

        // 或者也可以采用 prev curr 的形式， 这样就不用再单独考虑最后一个
        Interval prev = null;
        for (Interval curr : intervals) {
            if (prev == null || prev.end < curr.start) {
                // 前一个是空， 即第一个； 或者前一个和现在的不重叠
                result.add(curr); // 加入
                prev = curr; // 移动指针
            } else {
                // 重叠
                prev.end = Math.max(prev.end, curr.end); // 更改 prev 的 end， 废弃 curr
            }
        }
        return result;
    }

}
