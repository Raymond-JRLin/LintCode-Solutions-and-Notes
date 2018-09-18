// 204. Singleton
// Description
// Singleton is a most widely used design pattern. If a class has and only has one instance at every moment, we call this design as singleton. For example, for class Mouse (not a animal mouse), we should design it in singleton.
//
// You job is to implement a getInstance method for given class, return the same instance of this class every time you call this method.
//
// Have you met this question in a real interview?
// Example
// In Java:
//
// A a = A.getInstance();
// A b = A.getInstance();
// a should equal to b.
//
// Challenge
// If we call getInstance concurrently, can you make sure your code could run correctly?


class Solution {
    /**
     * @return: The same instance of this class every time
     */

    public static Solution s = null;
    public static Solution getInstance() {
        // write your code here
        if (s == null) {
            synchronized (Solution.class) {
                if (s == null) {
                    s = new Solution();
                }
            }
        }
        return s;
    }
};


answer:

class Solution {
    /**
     * @return: The same instance of this class every time
     */
    public static Solution instance = null;
    public static Solution getInstance() {
        if (instance == null) {
            instance = new Solution();
        }
        return instance;
    }
}



ref: 线程安全的方式
class Solution {

    private static Solution solution;
    private final static Object lock = new Object();

    private Solution() {

    }

    /**
     * @return: The same instance of this class every time
     */
    public static Solution getInstance() {
        if (null == solution) {
            synchronized (lock) {
                if (null == solution) {
                    solution = new Solution();
                }
            }
        }
        return solution;
    }
};
