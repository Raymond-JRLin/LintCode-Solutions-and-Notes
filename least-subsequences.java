// 1645. Least Subsequences
// Description
// Given an array with n integers. Splitting it into subsequences of strictly descending order.
// Output the minimum number of subsequences you can get by splitting.
//
// Have you met this question in a real interview?
// Example
// Input: [5,2,4,3,1,6]
// Output: 3
//
// Explanation:
//  You can split this array into : [5,2,1], [4,3], [6]. And there are 3 subsequences you get.﻿
// Or you can split it into [5, 4, 3],[2,1], [6] also 3 subsequences.
// But [5, 4, 3, 2, 1], [6] is not legal because [5, 4, 3, 2, 1] is not a subsuquence of the original array.
// input: [1, 1, 1]
// Output: 3
// Explanation: Because of the strictly descending order you must split it into 3 subsequences: [1],[1],[1]
// Challenge
// Deal it with the complexity: o(nlogn)


public class Solution {
    /**
     * @param arrayIn: The original array.
     * @return: Count the minimum number of subarrays.
     */
    public int LeastSubsequences(List<Integer> arrayIn) {
        // Write your code here.
        if (arrayIn == null || arrayIn.size() == 0) {
            return 0;
        }


        // return method1(arrayIn);

        // return method2(arrayIn);

        // return method2_2(arrayIn);

        // return method3(arrayIn);

        return method4(arrayIn);
    }

    private int method4(List<Integer> arrayIn) {
        // 和 method2_2 类似， 用 binary search 代替了 for 循环找当前 number 插入的位置
        // 同样地， bucket 数组只记录 i 链上最后一个数， 即最小值， 同时随着 i 增大， bucket[i] 从小到大
        int len = arrayIn.size();
        int[] buckets = new int[len];
        int size = 0;

        for (int i = 0; i < len; i++) {
            int width = arrayIn.get(i);
            // System.out.println("curr check " + width);
            if (size == 0 || buckets[size - 1] <= width) {
                // 最后一位最小值比当前数还大， 那么没有任何一个 subsequence 可以加进去， 直接加在最后
                buckets[size++] = width;
                // System.out.println("add directly ");
            } else {
                int index = bsearch(buckets, size, width);
                buckets[index] = width;
                // System.out.println("insert " + index);
            }

        }
        return size;
    }
    private int bsearch(int[] arr, int len, int target) {
        int left = 0, right = len - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid + 1;
            } else if (arr[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (arr[right] <= target) {
            return right + 1;
        } else if (arr[left] <= target) {
            return right;
        } else {
            return left;
        }
    }

    /*
    private int method3(List<Integer> array) {
        // TreeSet
        // 似乎 Java 中的 TreeSet 并不成功LOL， 这里好像很难写出像 C++ 一样的 comparator 来
        TreeSet<Pair> set = new TreeSet<>((o1, o2) -> {
            if (o1.val == o2.val) {
                return Integer.compare(o2.pos, o1.pos);
            } else {
                return Integer.compare(o1.val, o2.val);
            }
        });
        int result = 1;
        for (int i = 0; i < array.size(); i++) {
            Pair pair = new Pair(array.get(i), i);
            if (set.isEmpty()) {
                set.add(pair);
            } else {
                if (set.ceiling(pair) == null) {
                    result++;
                }
                set.add(pair);
            }
        }
        return result;
    }
    private class Pair {
        private int val;
        private int pos;

        public Pair(int v, int p) {
            this.val = v;
            this.pos = p;
        }
    }
    */

    private int method2_2(List<Integer> array) {
        // greedy
         // 其实我一开始做的是直接加入到从前往后第一个找的能成 subsequence 的位置， 也过了。 想了一下我觉得这个好像不影响， 因为能够选择就意味着有多个 subsequence 可以加入， 那我就选择统一的规则， 即加入最前面的， 其实就是从前往后 for， 那么最前面的末尾总会是最小的， 也就是说， 那些 subsequence link 在 list 中的位置从前往后， 末尾是从小到大的。 其实想一想， 不存在后面的末尾更小然后 block 住新查询的 num， 因为在查询这个后面末尾小的的时候， 它一定会加入前面的某个末尾比它大的 sequence
        List<List<Integer>> list = new ArrayList<>();
        for (int num : array) {
            if (list.isEmpty()) {
                List<Integer> link = new LinkedList<>();
                link.add(num);
                list.add(link);
            } else {
                boolean isLinked = false;
                for (List<Integer> link : list) {
                    if (num < link.get(link.size() - 1)) {
                        link.add(num);
                        isLinked = true;
                        break;
                    }
                }
                if (!isLinked) {
                    List<Integer> link = new LinkedList<>();
                    link.add(num);
                    list.add(link);
                }
            }
        }
        return list.size();
    }

    private int method2(List<Integer> array) {
        // greedy
        List<List<Integer>> list = new ArrayList<>(); // <subseq1, subseq2, ...>, subseq = num1 -> num2 -> ...
        for (int num : array) {
            if (list.isEmpty()) {
                List<Integer> link = new LinkedList<>();
                link.add(num);
                list.add(link);
            } else {
                // 看是否能把当前的数加到前面某个 subsequence 后面
                boolean isLinked = false;
                int pos = 0;
                int last = Integer.MAX_VALUE; // 加入到 tail 最小的的那个 subsequence 后面
                for (int i = 0; i < list.size(); i++) {
                    List<Integer> link = list.get(i);
                    if (num < link.get(link.size() - 1) && link.get(link.size() - 1) < last) {
                        last = link.get(link.size() - 1);
                        pos = i;
                        isLinked = true;
                    }
                }

                if (isLinked) {
                    list.get(pos).add(num);
                } else {
                    // 如果前面的 sequence 都加不进去， 那就自己新开一个
                    List<Integer> link = new LinkedList<>();
                    link.add(num);
                    list.add(link);
                }
            }
        }
        return list.size();
    }

    private int method1(List<Integer> list) {
        // DP
        int n = list.size();
        // definition: dp[i] = list(i) 在第 dp[i]th 条 subsequence上
        int[] dp = new int[n];
        // initialization
        dp[0] = 1;
        int ans = 1;
        // DP
        for(int i = 1; i < n ; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                // 和 method2_2 类似， 从前往后找可以可以连上去的 subsequence， 这里很巧妙地处理如果 >= 前面的数， 就新开一条链
                if(list.get(i) >= list.get(j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        // for (int num : dp) {
        //     System.out.print(num + " ");
        // }
        // result
        return ans;
    }
}
