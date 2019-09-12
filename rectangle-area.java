// 454. Rectangle Area
// 中文English
// Implement a Rectangle class which include the following attributes and methods:
//
// Two public attributes width and height.
// A constructor which expects two parameters width and height of type int.
// A method getArea which would calculate the size of the rectangle and return.
// Example
// Example1:
// Java:
//     Rectangle rec = new Rectangle(3, 4);
//     rec.getArea(); // should get 12，3*4=12
//
// Python:
//     rec = Rectangle(3, 4)
//     rec.getArea()
//
// Example2:
// Java:
//     Rectangle rec = new Rectangle(4, 4);
//     rec.getArea(); // should get 16，4*4=16
//
// Python:
//     rec = Rectangle(4, 4)
//     rec.getArea()


public class Rectangle {
    /*
     * Define two public attributes width and height of type int.
     */
    // write your code here
    public int width;
    public int height;
    /*
     * Define a constructor which expects two parameters width and height here.
     */
    // write your code here
    public Rectangle(int w, int h) {
        width = w;
        height = h;
    }
    /*
     * Define a public method `getArea` which can calculate the area of the
     * rectangle and return.
     */
    // write your code here
    public int getArea() {
        if (width >= 0 && height >= 0) {
            return width * height;
        } else {
            return -1;
        }
    }
}
