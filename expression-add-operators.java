// 653. Expression Add Operators
// 中文English
// Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
//
// Example
// Example 1:
//
// Input:
// "123"
// 6
// Output:
// ["1*2*3","1+2+3"]
// Example 2:
//
// Input:
// "232"
// 8
// Output:
// ["2*3+2", "2+3*2"]
// Example 3:
//
// Input:
// "105"
// 5
// Output:
// ["1*0+5","10-5"]
// Example 4:
//
// Input:
// "00"
// 0
// Output:
// ["0+0", "0-0", "0*0"]
// Example 5:
//
// Input:
// "3456237490",
// 9191
// Output:
// []


public class Solution {
    /**
     * @param num a string contains only digits 0-9
     * @param target an integer
     * @return return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        // Write your code here
        List<String> list = new ArrayList<>();
        // 要找出所有可能性： DFS， 关键是如何做， 其中的细节要注意什么
        dfs(num, target, "", 0, 0, list);
        return list;
    }
    private void dfs(String num, int target, String temp, long result, long prev, List<String> list) {
        if (result == target && num.length() == 0) {
            list.add(temp);
            return;
        }
        for (int i = 1; i <= num.length(); i++) {
            String currStr = num.substring(0, i);
            // if the current num string starts with 0, then return
            if (currStr.length() > 1 && currStr.charAt(0) == '0') {
                return;
            }
            long currNum = Long.parseLong(currStr);
            String next = num.substring(i);
            if (temp.length() == 0) {
                // 1st digit
                dfs(next, target, currStr, currNum, currNum, list);
            } else {
                // +
                dfs(next, target, temp + "+" + currStr, result + currNum, currNum, list);
                // -
                dfs(next, target, temp + "-" + currStr, result - currNum, -currNum, list);
                // *
                dfs(next, target, temp + "*" + currStr, result - prev + prev * currNum, prev * currNum, list);
            }
        }
    }
}
