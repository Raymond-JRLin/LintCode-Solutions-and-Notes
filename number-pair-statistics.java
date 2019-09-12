// 844. Number Pair Statistics
// 中文English
// Given a List <Point> p, find the number of (i,j) pairs that satisfy both p[i].x + p[j].x and p[i].y + p[j].y(i < j) can be divisible by 2.
//
// Example
// Example1
//
// Input: p = [[1,2],[3,4],[5,6]]
// Output: 3
// Explanation:
// p[0],p[1],p[2] Pairwise Covering, the sum of their x and y can be divided by 2
// Example2
//
// Input: p = [[0,3],[1,1],[3,4],[5,6]]
// Output: 1
// Explanation:
// Only when p [2] and p [3] are combined, their sum of x and y can be divided by two.
// Notice
// The length of given list len <= 10000.


public class Solution {
    /**
     * @param p: the point List
     * @return: the numbers of pairs which meet the requirements
     */
    public int pairNumbers(Point[] p) {
        // Write your code here
        if (p == null || p.length == 0) {
            return 0;
        }

        // return mytry(p);

        return method2(p);
    }

    private int method2(Point[] p) {
        // if we wanna satisfy the condition, then we can find some math rule:
        // to get even number: odd + odd, even + even, so we can count # [odd, odd], [odd, even], [even, odd], [even, even], then they can ONLY pair with Point with same type
        // O(N)
        int oddOdd = 0, oddEven = 0, evenOdd = 0, evenEven = 0;
        for (Point point : p) {
            if (point.x % 2 == 0) {
                if (point.y % 2 == 0) {
                    evenEven++;
                } else {
                    evenOdd++;
                }
            } else {
                if (point.y % 2 == 0) {
                    oddEven++;
                } else {
                    oddOdd++;
                }
            }
        }
        return ((oddOdd - 1) * oddOdd + (oddEven - 1) * oddEven + (evenOdd - 1) * evenOdd + (evenEven - 1) * evenEven) / 2;
    }

    private int mytry(Point[] p) {
        // O(N ^ 2) time
        int n = p.length;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isValid(p[i].x, p[j].x) && isValid(p[i].y, p[j].y)) {
                    count++;
                }
            }
        }
        return count;
    }
    private boolean isValid (int a, int b) {
        int sum = a + b;
        return sum % 2 == 0;
    }
}
