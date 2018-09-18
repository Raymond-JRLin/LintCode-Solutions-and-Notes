// 1471. Set Operation
// Description
// Given two sets A, B, output the size of the union, intersection, and difference of A and B
//
// The size of set does not exceed 1e6
// The value in the set does not exceed 1e6
// Have you met this question in a real interview?
// Example
// Given A = [1,3,4,6], B = [1,5,10]，return [6,1,3].
//
// Explanation:
// The union, intersection and difference of A and B are: [1,3,4,5,6,10], [1], [3,4,6]
// The corresponding set size is: 6, 1, 3
// Given A = [1,2,3], B = [4,5,6]，return [6,0,3].
//
// Explanation:
// The union, intersection and difference of A and B are: [1,2,3,4,5,6]、[]、[1,2,3]
// The corresponding set size is: 6, 0, 3


public class Solution {
    /**
     * @param A: The set A
     * @param B: The set B
     * @return: Return the size of three sets
     */
    public int[] getAnswer(int[] A, int[] B) {
        // Write your code here
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new int[3];
        }
        if (A == null || A.length == 0) {
            int m = B.length;
            return new int[]{0, m, m};
        }
        if (B == null || B.length == 0) {
            return new int[]{0, A.length, A.length};
        }

        return mytry(A, B);
    }

    private int[] mytry(int[] A, int[] B) {
        Set<Integer> union = new HashSet<>();
        // Set<Integer> inter = new HashSet<>();

        for (int num : A) {
            union.add(num);
        }
        int inter = 0;
        for (int num : B) {
            if (!union.add(num)) {
                // System.out.print("//" + num + "// ");
                inter++; // add failed, meaning it's a duplicate - intersection
            }
        }

        int[] result = new int[3];
        result[0] = union.size();
        result[1] = inter;
        result[2] = A.length - inter; // attention problem description
        return result;
    }
}

answer:

public class Solution {
    /**
     * @param A: The set A
     * @param B: The set B
     * @return: Return the size of three sets
     */
    public int[] getAnswer(int[] A, int[] B) {
        // Write your code here
        // Set<int> set = new HashSet<int>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < A.length; i++)
            set1.add(A[i]);
        for (int i = 0; i < B.length; i++)
            set2.add(B[i]);
        int[] c;
        c = new int[3];

        Set<Integer> result = new HashSet<>();
        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        c[0] = result.size();

        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        c[1] = result.size();

        result.clear();
        result.addAll(set1);
        result.removeAll(set2);
        c[2] = result.size();
        return c;
    }
}
