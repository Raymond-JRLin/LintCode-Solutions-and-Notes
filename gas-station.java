// 187. Gas Station
// 中文English
// There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
// You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
//
// Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
//
// Example
// Example 1:
//
// Input:gas[i]=[1,1,3,1],cost[i]=[2,2,1,1]
// Output:2
// Example 2:
//
// Input:gas[i]=[1,1,3,1],cost[i]=[2,2,10,1]
// Output:-1
// Challenge
// O(n) time and O(1) extra space
//
// Notice
// The solution is guaranteed to be unique.


public class Solution {
    /*
     * @param gas: An array of integers
     * @param cost: An array of integers
     * @return: An integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // write your code here
        if (gas == null || gas.length == 0) {
            return -1;
        }
        // int n = gas.length;
        // int gas_sum = 0;
        // int cost_sum = 0;
        // // 如果加油的总数还不及花费的， 则不能完成
        // for (int i = 0; i < n; i++) {
        //     gas_sum += gas[i];
        //     cost_sum += cost[i];
        // }
        // if (gas_sum < cost_sum) {
        //     return -1;
        // }
        // // 从 0 开始
        // int index = 0;
        // // 设 diff 为剩下的油量
        // int diff = 0;
        // for (int i = 0; i < n; i++) {
        //     // 从某一站出发， 加的油加上剩下的还不够走这个站到下个站
        //     if (gas[i] + diff < cost[i]) {
        //         // 则从下一个站从新开始
        //         index = i + 1;
        //         diff = 0;
        //     } else {
        //         // 可以走到， 就把剩下的油量加入 diff
        //         // 注意是 gas - cost 才是剩下的
        //         diff += gas[i] - cost[i];
        //     }
        // }
        // if (diff >= 0) {
        //     // if (index == n) {
        //     //     // 因为是个环， 所以如果到了 n， 表示又到原点了
        //     //     return 0;
        //     // } else {
        //     // 但其实我们是从头开始的， 这个错误是因为之前 diff 计算错误
        //         return index;
        //     // }
        // } else {
        //     return -1;
        // }

        return mytry(gas, cost);
    }
    private int mytry(int[] gas, int[] cost) {
        // O(N ^ 2) time => TLE
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int has = 0;
            int next = i;
            while (has >= 0) {
                if (next != i && next % n == i) {
                    return i;
                }
                has += gas[next % n] - cost[next % n];
                next++;
            }

        }
        return -1;
    }
}
