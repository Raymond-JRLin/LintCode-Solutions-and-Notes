// 204. Singleton
// 中文English
// Singleton is a most widely used design pattern. If a class has and only has one instance at every moment, we call this design as singleton. For example, for class Mouse (not a animal mouse), we should design it in singleton.
//
// You job is to implement a getInstance method for given class, return the same instance of this class every time you call this method.
//
// Example
//
// In Java:
//
// 	A a = A.getInstance();
// 	A b = A.getInstance();
//
// a should equal to b.
//
// Challenge
// If we call getInstance concurrently, can you make sure your code could run correctly?
//


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
