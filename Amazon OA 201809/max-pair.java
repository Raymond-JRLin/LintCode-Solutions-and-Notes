// 1635. Max Pair
// Description
// Give two Lists, give a maximum value, find a bunch in each of the two lists, and find all the pairs that are closest to the maximum but not larger than the maximum.
//
// The length of the two lists do not exceed 100000100000.
// Each element do not exceed 10000000001000000000.
//
// Have you met this question in a real interview?
// Example
// Give a=[2,3,4,5,6], b=[4,5,7], x=8', return [[3,5],[4,4]].
//
// Explanation:
// the sum of [3,5] or [4,4] is 8, which is no larger than 8.
// Give a=[2,3,4,5,6], b=[4,5,7], x=10', return [[3,7],[5,5],[6,4]].
//
// Explanation:
// the sum of [3,7],[5,5],[6,4] is 10, which is no larger than 10. 


public class Solution {
    /**
     * @param a: the first list
     * @param b: the second list
     * @param x: the max sum
     * @return: the pairs whose sum are not exceed x
     */
    public int[][] getAns(int[] a, int[] b, int x) {
        // Write your code here.

        // return mytry(a, b, x);

        // return mytry2(a, b, x);

        return mytry3(a, b, x);
    }

    private int[][] mytry3(int[] a, int[] b, int x) {
        // BS: O(NlogM)
        // if (a.length > b.length) {
        //     return mytry3(b, a, x);
        // }
        // a.length < b.length;
        // 题目似乎要求按照 a[] 的顺序来表示答案
        Arrays.sort(a);
        Arrays.sort(b);
        int max = 0;
        List<int[]> list = new ArrayList<>();
        int right = b.length - 1;
        for (int i = 0; i < a.length; i++) {
            int target = x - a[i];
            right = bs(b, 0, right, target);
            if (right == -1) {
                break;
            }
            int curr = a[i] + b[right];
            if (curr < max) {
                continue;
            }
            if (curr > max) {
                // 当 sum 大于当前最大值的时候， 抹掉之前的答案， 并更新最大值
                list = new ArrayList<>();
                max = curr;
            }
            // 加入当前的 pair
            list.add(new int[]{a[i], b[right]});
        }

        int[][] result = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }
        return result;
    }
    private int bs(int[] nums, int start, int end, int target) {
        // equal or small than
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] <= target) {
            return end;
        } else if (nums[start] <= target) {
            return start;
        } else {
            return -1;
        }
    }

    private int[][] mytry2(int[] a, int[] b, int x) {
        // 2 pointers: O(N + M)
        Arrays.sort(a);
        Arrays.sort(b);
        int max = 0;
        List<int[]> list = new ArrayList<>();
        int i = 0; // go a[] from left to right
        int j = b.length - 1; // go b[] from right to left
        while (i < a.length && j >= 0) {
            int curr = a[i] + b[j];
            if (curr > x) {
                // sum 太大了
                j--;
            } else {
                // sum 能够满足条件， 但是注意本题重复答案不算， 需要去重， 所以要注意查看 sum 和 当前最大值之间的关系
                if (curr >= max) {
                    // 只有当 sum 大于或等于当前最大值的时候， 才有可能加入当前 pair
                    if (curr > max) {
                        // 当 sum 大于当前最大值的时候， 抹掉之前的答案， 并更新最大值
                        list = new ArrayList<>();
                        max = curr;
                    }
                    // 加入当前的 pair
                    list.add(new int[]{a[i], b[j]});
                }
                // 无论怎样， i 向右移动， 因为当前 sum 比 x 来的小， 我们去找更大一些的 pair
                i++;
            }
        }
        int[][] result = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }
        return result;
    }

    private int[][] mytry(int[] a, int[] b, int x) {
        // brute force: TLE
        // O(NM)
        Arrays.sort(a);
        Arrays.sort(b);
        int max = 0;
        List<int[]> list = new ArrayList<>();
        for (int aVal : a) {
            if (aVal + b[0] > x) {
                break;
            }
            for (int j = b.length - 1; j >= 0; j--) {
                int curr = aVal + b[j];
                if (curr <= x && curr >= max) {
                    if (aVal + b[j] > max) {
                        list = new ArrayList<>();
                        max = curr;
                    }
                    list.add(new int[]{aVal, b[j]});
                    break;
                }
            }
        }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
