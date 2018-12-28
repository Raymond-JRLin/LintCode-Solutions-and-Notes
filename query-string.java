// 1642. Query String
// Description
// Give a binary string str and an integer n to check if the substring of the string contains all binary representations of non-negative integers less than or equal to the given integer.
//
// String length does not exceed 100,000
// n does not exceed 100,000
// Binary starts at 0 and does not require leading zeros
// 
// Have you met this question in a real interview?
// Example
// Given str="0110",n=3, return yes.
//
// The substring of str contains "0", "1", "10", "11".
// Given str="0110",n=4, return no.
//
// The substring of str does not contain "100",


public class Solution {
    /**
     * @param str: the string
     * @param n: the n
     * @return: yes or no
     */
    public String queryString(String str, int n) {
        // Write your code here.

        // return mytry(str, n);

        // return method2(str, n);

        return method3(str, n);
    }

    private String method3(String str, int n) {
        // 也是查看每个 0 - n 的数是否能够得到
        boolean[] f = new boolean[n + 1];
        int m = str.length();
        for (int i = 0; i < m; i++) {
            char ch = str.charAt(i);
            if (ch == '0') {
                // 0 开头的就跳过， 因为可以去查 0 后面第一个 1 开头的 substring
                f[0] = true;
            } else {
                int val = 0;
                // 查 j - m 的所有的 substring
                for (int j = i; j < m; j++) {
                    val = (val << 1) | (str.charAt(j) - '0');
                    System.out.print(val + ", ");
                    if (val > n) {
                        break;
                    }
                    f[val] = true;
                }
                System.out.println();
            }
        }

        for (boolean flag : f) {
            if (!flag) {
                return "no";
            }
        }
        return "yes";
    }

    private String method2(String s, int n) {
        // 更快的方法是把数字快速转成二进制， 然后去 s 中查是否存在该 substring
        for (int i = n; i >= 0; i--) {
            // String bit = Integer.toBinaryString(i);
            String bit = toBinaryString(i);
            if (s.indexOf(bit) < 0) {
                return "no";
            }
        }
        return "yes";
    }
    // 如果我们要自己写数字转 bit
    private String toBinaryString(int num) {
        String result = "";
        while (num != 0) {
            result = (num & 1) + result;
            num >>>= 1; // 正负都可
        }
        return result;
    }

    private String mytry(String s, int n) {
        // brute force
        // 找出所有 substring，  转换成数字， 然后 check
        Set<Integer> subs = getSubstringNum(s);
        int count = 0;
        for (int num : subs) {
            if (num <= n) {
                count++;
            }
        }
        return count == n + 1 ? "yes" : "no";
    }
    private Set<Integer> getSubstringNum(String s) {
        int n = s.length();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < Math.min(n + 1, i + 32); j++) {
                String sub = s.substring(i , j);
                int num = Integer.parseInt(sub, 2);
                set.add(num);
            }
        }
        return set;
    }
}
