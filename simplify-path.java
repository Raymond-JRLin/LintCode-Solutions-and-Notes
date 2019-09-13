// 421. Simplify Path
// 中文English
// Given an absolute path for a file (Unix-style), simplify it.
//
// In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.
//
// The result must always begin with /, and there must be only a single / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the result must be the shortest string representing the absolute path.
//
// Example
// Example 1:
//
// Input: "/home/"
// Output: "/home"
// Example 2:
//
// Input: "/a/./../../c/"
// Output: "/c"
// Explanation: "/" has no parent directory, so "/../" equals "/".
// Notice
// Did you consider the case where path is "/../"?
//
// In this case, you should return "/".
//
// Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
//
// In this case, you should ignore redundant slashes and return "/home/foo".
//


public class Solution {
    /*
     * @param path: the original path
     * @return: the simplified path
     */
    public String simplifyPath(String path) {
        // write your code here
        if (path == null || path.length() == 0) {
            return "/";
        }
        String result = "";
        int n = path.length();
        // Unix的path规则可以在这里了解：http://en.wikipedia.org/wiki/Path_(computing)

        // 归下类的话，有四种字符串：
        // 1. "/"：为目录分隔符，用来分隔两个目录。
        // 2. "."：当前目录
        // 3. ".."：上层目录
        // 4. 其他字符串：目录名

        // 所以会碰到几种情况：
        // 1: "/",  跳过， 直接看下一个
        // 2: "." , 什么都不需要干，直接开始寻找下一个element
        // 3: "..", 弹出栈顶元素，寻找下一个element
        // 4: 其他, 插入当前elemnt为新的栈顶，寻找下一个element
        Stack<String> stack = new Stack<>();
        String[] array = path.split("/+");
        for (int i = 0; i < array.length; i++) {
            String curr = array[i];
            if (curr.isEmpty()) {
                continue;
            } else if (curr.equals(".")) {
                continue;
            } else if (curr.equals("..")) {
                if (!stack.isEmpty()) {
                    // ATTENTION: if we wanna pop we need to make sure the stack is not empty first
                    stack.pop();
                }
            } else {
                stack.push(curr);
            }
        }
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        if (result.isEmpty()) {
            result += "/";
        }
        return result;
    }
}
