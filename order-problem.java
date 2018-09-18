// 1442. Order Problem
// Description
// There is now an order with demand for n items, and the demand for the i-th item is order[i]. The factory has m production modes. Each production mode is shaped like [p[1],p[2],...p[n]], that is, produce p[1] first items, p[2] second items... You can use multiple production modes. Please tell me how many items do not meet the demand at least in the case of not exceeding the demand of any kind of items?
//
// 1 \leq n, m \leq 71≤n,m≤7
// 1 \leq order[i] \leq 101≤order[i]≤10
// 0 \leq pattern[i][j] \leq 100≤pattern[i][j]≤10
// Have you met this question in a real interview?
// Example
// Given order=[2,3,1], pattern=[[2,2,0],[0,1,1],[1,1,0]] , return 0.
//
// Explanation:
// Use [0,1,1] once, [1,1,0] twice, remaining [0,0,0].
// Given order=[2,3,1], pattern=[[2,2,0]] , return 2.
//
// Explanation:
// Use [2,2,0] once, remaining [0,1,1].


public class Solution {
    /**
     * @param order: The order
     * @param pattern: The pattern
     * @return: Return the number of items do not meet the demand at least
     */
    public int getMinRemaining(int[] order, int[][] pattern) {
        // Write your code here
        if (order == null || order.length == 0) {
            return 0;
        }

        return method1(order, pattern);

        // return method2(order, pattern);
    }

    private int method2(int[] order, int[][] pattern) {
        // BFS
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : order) {
            sum += num;
            list.add(num);
        }
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(list);
        while (!queue.isEmpty()) {
            List<Integer> curr = queue.poll();
            sum = Math.min(sum, getSum(curr));
            for (int i = 0; i < pattern.length; i++) {
                // try each valid pattern
                if (!isValidPattern(pattern[i])) {
                    // pattern should > 0 totally
                    continue;
                }
                List<Integer> next = new ArrayList<>();
                boolean isValid = true;
                for (int j = 0; j < order.length; j++) {
                    // update order numbers in this pattern
                    int num = curr.get(j) - pattern[i][j];
                    if (num < 0) {
                        isValid = false;
                        break;
                    }
                    next.add(num);
                }
                if (isValid) {
                    queue.offer(next);
                }
            }
        }
        return sum;
    }
    private boolean isValidPattern(int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        return sum > 0;
    }
    private int getSum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    private int result;
    private int method1(int[] order, int[][] pattern) {
        // DFS
        result = 0;
        // 总共的 items 数量
        for (int num : order) {
            result += num;
        }

        dfs(order, pattern, 0);
        return result;
    }

    private void dfs(int[] order, int[][] pattern, int index) {
        if (index >= pattern.length) {
            // go through all patterns
            int sum = 0;
            for (int num : order) {
                sum += num;
            }
            result = Math.min(result, sum);
            return;
        }
        // 当前 pattern 最多能执行多少次而不超过任意一种 item 的数量
        int count = getTimes(order, pattern, index);
        for (int i = 0; i <= count; i++) {
            // 对当前 pattern， 从 order 中减掉 pattern 生产的数量
            for (int k = 0; k < order.length; k++) {
                order[k] -= i * pattern[index][k];
            }
            dfs(order, pattern, index + 1);
            // backtracking
            for (int k = 0; k < order.length; k++) {
                order[k] += i * pattern[index][k];
            }
        }
    }
    private int getTimes(int[] order, int[][] pattern, int index) {
        // we cannot exceed the demand of any kind of items
        int count = 10; // 题目限制是最大为 10， 设为 MAX 则 TLE
        for (int i = 0; i < order.length; i++) {
            if (pattern[index][i] != 0) {
                // index coult be 0
                count = Math.min(count, order[i] / pattern[index][i]);
            }
        }
        return count;
    }
}
