// 445. Cosine Similarity
// 中文English
// Cosine similarity is a measure of similarity between two vectors of an inner product space that measures the cosine of the angle between them. The cosine of 0° is 1, and it is less than 1 for any other angle.
//
// See Wiki: Cosine Similarity
//
// Here is the formula:
//
// cosine-similarity
//
// Given two vectors A and B with the same size, calculate the cosine similarity.
//
// Return 2.0000 if cosine similarity is invalid (for example A = [0] and B = [0]).
//
// Example
// Example 1:
//
// Input: A = [1,4,0], B = [1,2,3]
// Output: 0.5834
// Example 2:
//
// Input: A = [1], B = [2]
// Output: 1.0000


class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: Cosine similarity.
     */
    public double cosineSimilarity(int[] A, int[] B) {
        // write your code here
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return (double) 2;
        }
        double sum = 0;
        double sumA = 0;
        double sumB = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] * B[i];
            sumA += A[i] * A[i];
            sumB += B[i] * B[i];
        }
        if (sumA == 0 && sumB == 0) {
            return (double) 2;
        }
        sumA = Math.sqrt(sumA);
        sumB = Math.sqrt(sumB);
        return sum / (sumA * sumB);
    }
}
