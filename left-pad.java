// 524. Left Pad
// 中文English
// You know what, left pad is javascript package and referenced by React:
// Github link: https://github.com/azer/left-pad
//
// One day his author unpublished it, then a lot of javascript projects in the world broken.
//
// You can see from github it's only 11 lines.
//
// You job is to implement the left pad function. If you do not know what left pad does, see examples below and guess.
//
// Example
// Example 1:
//
// Input:
// leftpad("foo", 5)
// Output:
// "  foo"
// Example 2:
//
// Input:
// leftpad("foobar", 6)
// Output:
// "foobar"
// Example 3:
//
// Input:
// leftpad("1", 2, "0")
// Output:
// "01"
// Challenge
// Use as little memory as possible
//

public class StringUtils {
    /**
     * @param originalStr the string we want to append to with spaces
     * @param size the target length of the string
     * @return a string
     */
    static public String leftPad(String originalStr, int size) {
        // Write your code here
        int n = originalStr.length();
        if (n >= size) {
            return originalStr;
        } else {
            // n < size
            String result = new String("");
            for (int i = 1; i <= size - n; i++) {
                result += " ";
            }
            result += originalStr;
            return result;
        }
    }

    /**
     * @param originalStr the string we want to append to
     * @param size the target length of the string
     * @param padChar the character to pad to the left side of the string
     * @return a string
     */
    static public String leftPad(String originalStr, int size, char padChar) {
        // Write your code here
        int n = originalStr.length();
        if (n >= size) {
            return originalStr;
        } else {
            // n < size
            String result = new String("");
            for (int i = 1; i <= size - n; i++) {
                result += padChar;
            }
            result += originalStr;
            return result;
        }
    }
}
