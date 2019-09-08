// 575. Decode String
// 中文English
// Given an expression s contains numbers, letters and brackets. Number represents the number of repetitions inside the brackets(can be a string or another expression)．Please expand expression to be a string.
//
// Example
// Example1
//
// Input: S = abc3[a]
// Output: "abcaaa"
// Example2
//
// Input: S = 3[2[ad]3[pf]]xyz
// Output: "adadpfpfpfadadpfpfpfadadpfpfpfxyz"
// Challenge
// Can you do it without recursion?
//
// Notice
// Numbers can only appear in front of “[]”.
//


public class Solution {
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        // Write your code here
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return new String("");
        }

        // method 1: based on stack by myself, which I think is not very robust compared to solution, it seems I corrected it as cases were testing and don't have a big picture
        // e.g. in solution, it creates a Stack of Object, so we can put Integer, Chaaracter and etc; 并且，参考答案也是从左到右扫描给的 string， 但是是直接拿到数字
        // Stack<Character> stack = new Stack<>();
        // for (int i = 0; i < s.length(); i++) {
        //     Character c = s.charAt(i);
        //     stack.push(c);
        //     // as long as we met ']', then do flatten and push into stack
        //     if (c == ']') {
        //         stack.pop(); // remove ']'
        //         String nest = new String("");
        //         while (stack.peek() != '[') {
        //             nest += stack.pop(); // get string that should be replicated
        //             // but s would be in reversed sequence
        //         }
        //         stack.pop(); // remove '['
        //         int times = 0;
        //         int digit = 1;
        //         // 注意'[' 前面出现两位数怎么处理
        //         while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
        //             times += (stack.pop() - '0') * digit;
        //             // get number: since we use char, we should convert it to int type
        //             digit *= 10;
        //         }
        //         flatten(stack, nest, times);
        //     }
        // }
        // // use another stack to reverse the final sequence
        // Stack<Character> backStack = new Stack<>();
        // while (!stack.isEmpty()) {
        //     backStack.push(stack.pop());
        // }
        // String result = new String("");
        // while (!backStack.isEmpty()) {
        //     result += backStack.pop();
        // }
        // return result;

        // method 2: recursion
        // we can also use recursion to solve this problem, but it would be more complicated
        // 把括号里的当成子问题去递归括号里的 substring 再返回， 这时要注意： 当前是嵌套的第几层， 层数的不同前面的数字也相应的是直接算重复几次或是和 substring 放在一起被拿去递归
        StringBuilder sb = new StringBuilder();
        String substr = new String("");
        int times = 0;
        int nest = 0; // to record what layer current substring is
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c >= '0' && c <='9') {
                if (nest > 0) {
                    substr += c;
                } else {
                    times = c - '0' + times * 10;
                }
            } else if (c == '[') {
                if (nest > 0) {
                    substr += c;
                }
                nest++;
            } else if (c == ']') {
                nest--;
                if (nest > 0) {
                    substr += c;
                } else {
                    // nest == 0, then we can expand substring by recursion and push to sb
                    String result = expressionExpand(substr);
                    for (int j = 1; j <= times; j++) {
                        sb.append(result); // replication
                    }
                    // re-initialize
                    times = 0;
                    substr = "";
                }
            } else {
                if (nest > 0) {
                    substr += c;
                } else {
                    // if nest == 0 here, means it's not in any bracket, so we can add it into final result directly
                    sb.append(String.valueOf(c));
                }
            }
        }
        return sb.toString();
    }
    private void flatten(Stack<Character> stack, String nest, int times) {
        String result = new String("");
        for (int i = 1; i <= times; i++) {
            result += nest; // replication
        }
        for (int i = result.length() - 1; i >= 0; i--) {
            stack.push(result.charAt(i));
        }
    }
}
