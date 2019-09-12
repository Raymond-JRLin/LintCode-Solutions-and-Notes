// 957. Radar Detection
// 中文English
// There is a bunch of radars on a 2D plane(Radar has x, y coordinates, and a radius r which is the range can be detected). Now, there is a car that passes through the range of y = 0 and y = 1 and cannot be detected by the radar. If the car is detected, return YES, otherwise NO.(You can consider that the car is a line segment of length 1 and goes straight from x = 0 to the right)
//
// Example
// Example 1:
//
// input:coordinates = [[0,2]], radius = [1]
// outut:"NO"
// Explanation:There is a radar at (0,2) that can detect a circle with a radius of 1 centered on (0,2) and the car will not be detected.
// Example 2:
//
// input: str = coordinates = [[0,2],[1,2]], radius = [1,2],
// outut:"YES"
// Explanation:There is a radar at (0,2) that can detect a circular area with a radius of 2 with a center of (0,2). Radars at (1,2) can detect (1,2) as Center, circular area with 2 radius. The No. 2 radar can detect the passing of the car.
// Notice
// The number of radars is n，n <= 1000。
// The radar's coordinate x is a non-negative integer, y is an integer, and r is a positive integer.


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param coordinates: The radars' coordinate
     * @param radius: Detection radius of radars
     * @return: The car was detected or not
     */
    public String radarDetection(Point[] coordinates, int[] radius) {
        // Write your code here

        return mytry(coordinates, radius);
    }

    private String mytry(Point[] coor, int[] rad) {
        for (int i = 0; i < coor.length; i++) {
            int y = coor[i].y;
            if (y - rad[i] <= 0 && y + rad[i] >= 1) {
                return "YES";
            }
        }
        return "NO";
    }
}
