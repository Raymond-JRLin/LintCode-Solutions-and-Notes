// 600. Smallest Rectangle Enclosing Black Pixels
// 中文English
// An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
//
// Example
// Example 1:
//
// Input：["0010","0110","0100"]，x=0，y=2
// Output：6
// Explanation：
// The upper left coordinate of the matrix is (0,1), and the lower right coordinate is (2,2).
// Example 2:
//
// Input：["1110","1100","0000","0000"], x = 0, y = 1
// Output：6
// Explanation：
// The upper left coordinate of the matrix is (0, 0), and the lower right coordinate is (1,2).


public class Solution {
    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    public int minArea(char[][] image, int x, int y) {
        // Write your code here
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int left = leftEdge(image, 0, y);
        int right = rightEdge(image, y, image[0].length - 1);
        int top = topEdge(image, 0, x);
        int bottom = bottomEdge(image, x, image.length - 1);
        return (bottom - top + 1) * (right - left + 1);
    }

    private int rightEdge(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyCol(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
            // be careful to the start & end moving
        }
        if (isEmptyCol(image, end)) {
            return start;
        } else {
            return end;
        }
    }

    private int leftEdge(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyCol(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isEmptyCol(image, start)) {
            return end;
        } else {
            return start;
        }
    }

    private int topEdge(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isEmptyRow(image, start)) {
            return end;
        } else {
            return start;
        }
    }

    private int bottomEdge(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isEmptyRow(image, end)) {
            return start;
        } else {
            return end;
        }
    }

    private boolean isEmptyCol(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyRow(char[][] image, int row) {
        for (int i = 0; i < image[0].length; i++) {
            if (image[row][i] == '1') {
                return false;
            }
        }
        return true;
    }
}
