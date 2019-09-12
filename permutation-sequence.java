// 388. Permutation Sequence
// 中文English
// Given n and k, find the kth permutation of the dictionary order in the full permutation of n.
//
// Example
// Example 1:
//
// Input: n = 3, k = 4
// Output: "231"
// Explanation:
// For n = 3, all permutations are listed as follows:
// "123", "132", "213", "231", "312", "321"
// Example 2:
//
// Input: n = 1, k = 1
// Output: "1"
// Challenge
// O(n*k) in time complexity is easy, can you do it in O(n^2) or less?
//
// Notice
// 1 ≤ n ≤ 9
//


public class Solution {
    /*
     * @param n: n
     * @param k: the k th permutation
     * @return: return the k-th permutation
     */
    public String getPermutation(int n, int k) {
        // write your code here

        // method 1: based on Math
        // int[] fac = new int[n];
        // int[] array = new int[n];
        // // initialization
        // fac[0] = 1;
        // array[0] = 1;
        // for (int i = 1; i < n; i++) {
        //     fac[i] = fac[i - 1] * i;
        //     array[i] = i + 1;
        // }
        // String result = "";
        // // 把 k 从第几个换成 index 的关系
        // k = k - 1;
        // for (int i = 0; i < n; i++) {
        //     int num = k / fac[n - i - 1]; // 找到 num index 的数
        //     result += String.valueOf(array[num]); // 当前位
        //     for (int j = num; j < n - 1; j++) {
        //         array[j] = array[j + 1]; // 选走了一个数， 把后面往前挪
        //     }
        //     // 剩下的当中是第 k' 个
        //     k %= fac[n - i - 1];
        // }
        // return result;

        // method 2: DFS
        int[] dic = new int[n];
        for (int i = 0; i < n; i++) {
            dic[i] = i + 1;
        }
        boolean[] visited = new boolean[n];

        dfs(n, k, dic, 0, "", visited);
        return result;
    }
    public int count = 0;
    public String result = "";
    private void dfs(int n, int k, int[] dic, int digit, String temp, boolean[] visited) {
        if (digit == n) {
            count++;
            if (count == k) {
                result = temp;
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                temp += String.valueOf(dic[i]);
                visited[i] = true;
                dfs(n, k, dic, digit + 1, temp, visited);
                temp = temp.substring(0, temp.length() - 1);
                visited[i] = false;
            }
        }
    }
}
