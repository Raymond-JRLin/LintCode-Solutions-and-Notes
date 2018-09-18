// 1573. Legal String
// Description
// Given a string S containing only uppercase letters, insert as few '_' as possible in S so that the distance of same kind of letters no less than k, and if there are multiple solutions,just make the lexicographical order of the target string as small as possible. For example, given S = ”AABACCDCD”, k = 3, the target string is “A__AB_AC__CD_CD”. Since the length of target string may be very long, we only need to return the number of '_' inserted before each position of the original string. For example, the previous example returns [0,2,0,1,0,2,0,1,0].
// (The lexicographical order of '_' is greater than'Z')
//
// 1<=`|S|`,`k`<=5000001<=‘∣S∣‘,‘k‘<=500000
//
// Have you met this question in a real interview?
// Example
// GivenS="AABACCDCD",k = 3,return [0,2,0,1,0,2,0,1,0]
//
// Explanation：
// The target string is "A__AB_AC__CD_CD"
// GivenS = "ABBA",k = 2,return[0,0,1,0]
//
// Explanation：
// The target string is”AB_BA”


public class Solution {
    /**
     * @param k: The necessary distance of same kind of letters
     * @param S: The original string
     * @return: Return the number of '_' inserted before each position of the original string
     */
    public int[] getAns(int k, String S) {
        // Write your code here.
        if (S == null || S.length() == 0) {
            return new int[0];
        }
        if (k <= 1) {
            int[] result = new int[S.length()];
            Arrays.fill(result, 0);
            return result;
        }

        // return mytry(k, S);

        return method2(k, S);
    }

    // 我们贪心的考虑这个问题，由于字典序需要最小，所以我们尽量让''的位置靠后，也就是从前往后考虑，当当前位置字母的前一个同类字母离它距离小于k时我们才在当前字母前面插入''.
    // 具体做法如下我们将原字符串扫一遍，维护一个sum[]数组当前位置之前的''的总数，维护一个pre[]数组，pre[i]表示当前位置之前的最靠右的字母'i'的位置，这样每扫到一个位置k,我们就可以利用这两个数组O(1)得到这个位置之前需要插入多少个''了。
    // 总复杂度O(|S|)

    private int[] method2(int k, String S) {
        // Write your code here
        int[] ans;
        int l = S.length();
        int i;
        ans = new int[l]; // result
        int[] pre;
        pre = new int[1000]; // 当前的 char 的上一个的相同 char 的位置
        int[] sum;
        sum = new int[l]; // 当前位置之前的 '-' 总数， 类似 prefix sum
        for (i = 0; i < l; i++)
            sum[i] = 0;
        for (i = 0; i < 500; i++)
            pre[i] = -1;
        for (i = 0; i < l; i++) {
            char c = S.charAt(i);
            if (pre[c] == -1) {
                // 如果前面没有出现过
                ans[i] = 0; // 没必要加 '-'
                pre[c] = i; // 记录当前位置
                if (i != 0)
                    sum[i] = sum[i - 1];
                else
                    sum[i] = 0;
            } else {
                // 前面出现过， 要去查看是否需要加 '-'
                if (i - pre[c] + sum[i - 1] - sum[pre[c]] - 1 <= k - 1) {
                    // 当前位置 - 上一个相同 char 的位置 + 前一个位置 char 加的 '-' - 上一个相同 char 所加的 '-' 少于 k 个
                    ans[i] = k - 1 - (i - pre[c] + sum[i - 1] - sum[pre[c]] - 1);
                    sum[i] = sum[i - 1] + ans[i]; // 始终加 prefix
                } else {
                    // 距离超过 k， 没必要加
                    sum[i] = sum[i - 1];
                    ans[i] = 0;
                }
                pre[c] = i; // 记录当前位置
            }
        }
        return ans;
    }

    private int[] mytry(int k, String s) {
        // similar to skill cooling time
        int n = s.length();
        int[] result = new int[n];
        Map<Character, Integer> map = new HashMap<>(); // <char, distance>
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (i > 0 && c == s.charAt(i - 1)) {
                    // System.out.println("prev repeat");
                    result[i] = k - 1;
                    map = new HashMap(); // don't forget to count the distance when we add '-'; if we add k - 1 distance, then all remained distance should be wiped out
                } else {
                    // System.out.println("dis: " + c + " has " + map.get(c));
                    result[i] = map.get(c);
                    reduce(map, map.get(c)); // don't forget to count the distance when we add '-'
                    map.remove(c);
                }
            } else {
                result[i] = 0;
            }
            map.put(c, k);
            reduce(map, 1);
            // print(map);
        }
        return result;
    }
    private void reduce(Map<Character, Integer> map, int k) {
        Set<Character> set = new HashSet<>();
        for (char key : map.keySet()) {
            map.put(key, map.get(key) - k);
            if (map.get(key) <= 0) {
                set.add(key);
            }
        }
        for (char key : set) {
            map.remove(key);
        }
    }
    private void print(Map<Character, Integer> map) {
        for (char key : map.keySet()) {
            System.out.print(key + "=" + map.get(key) + ",");
        }
        System.out.println();
    }
}


我们贪心的考虑这个问题，由于字典序需要最小，所以我们尽量让''的位置靠后，也就是从前往后考虑，当当前位置字母的前一个同类字母离它距离小于k时我们才在当前字母前面插入''.
具体做法如下我们将原字符串扫一遍，维护一个sum[]数组当前位置之前的''的总数，维护一个pre[]数组，pre[i]表示当前位置之前的最靠右的字母'i'的位置，这样每扫到一个位置k,我们就可以利用这两个数组O(1)得到这个位置之前需要插入多少个''了。
总复杂度O(|S|)

// answer:

public class Solution {
    /**
     * @param k: The necessary distance of same kind of letters
     * @param S: The original string
     * @return: Return the number of '_' inserted before each position of the original string
     */
    public int[] getAns(int k, String S) {
        // Write your code here.=
        int[] ans;
        int l = S.length();
        int i;
        ans = new int[l];
        int[] pre;
        pre = new int[1000];
        int[] sum;
        sum = new int[l];
        for (i = 0; i < l; i++)
            sum[i] = 0;
        for (i = 0; i < 500; i++)
            pre[i] = -1;
        for (i = 0; i < l; i++) {
            char c = S.charAt(i);
            if (pre[c] == -1) {
                ans[i] = 0;
                pre[c] = i;
                if (i != 0)
                    sum[i] = sum[i - 1];
                else
                    sum[i] = 0;
            } else {
                if (i - pre[c] + sum[i - 1] - sum[pre[c]] - 1 <= k - 1) {
                    ans[i] = k - 1 - (i - pre[c] + sum[i - 1] - sum[pre[c]] - 1);
                    sum[i] = sum[i - 1] + ans[i];
                } else {
                    sum[i] = sum[i - 1];
                    ans[i] = 0;

                }
                pre[c] = i;
            }
        }
        return ans;
    }
}
