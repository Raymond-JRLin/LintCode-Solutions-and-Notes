// 497. Shape Factory
// Description
// Factory is a design pattern in common usage. Implement a ShapeFactory that can generate correct shape.
//
// You can assume that we have only tree different shapes: Triangle, Square and Rectangle.
//
// Have you met this question in a real interview?
// Example
// ShapeFactory sf = new ShapeFactory();
// Shape shape = sf.getShape("Square");
// shape.draw();
// >>  ----
// >> |    |
// >> |    |
// >>  ----
//
// shape = sf.getShape("Triangle");
// shape.draw();
// >>   /\
// >>  /  \
// >> /____\
//
// shape = sf.getShape("Rectangle");
// shape.draw();
// >>  ----
// >> |    |
// >>  ----


/**
 * Your object will be instantiated and called as such:
 * ShapeFactory sf = new ShapeFactory();
 * Shape shape = sf.getShape(shapeType);
 * shape.draw();
 */
interface Shape {
    void draw();
}

class Rectangle implements Shape {
    // Write your code here
    @Override
    public void draw() {
        System.out.println(" ---- ");
        System.out.println("|    |");
        System.out.println(" ---- ");
   }
}

class Square implements Shape {
    // Write your code here
   @Override
   public void draw() {
        System.out.println(" ---- ");
        System.out.println("|    |");
        System.out.println("|    |");
        System.out.println(" ---- ");
   }
}

class Triangle implements Shape {
    // Write your code here
   @Override
   public void draw() {
        System.out.println("  /\\");
        System.out.println(" /  \\");
        System.out.println("/____\\");
   }
}

public class ShapeFactory {
    /**
     * @param shapeType a string
     * @return Get object of type Shape
     */
    public Shape getShape(String shapeType) {
        // Write your code here
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("Triangle")) {
            return new Triangle();
        } else if(shapeType.equalsIgnoreCase("Rectangle")) {
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("Square")) {
            return new Square();
        }
        return null;
    }
}

answer:

/**
 * Your object will be instantiated and called as such:
 * ShapeFactory sf = new ShapeFactory();
 * Shape shape = sf.getShape(shapeType);
 * shape.draw();
 */
interface Shape {
    void draw();
}

class Rectangle implements Shape {
    // Write your code here
    @Override
    public void draw() {
        System.out.println(" ---- ");
        System.out.println("|    |");
        System.out.println(" ---- ");
   }
}

class Square implements Shape {
    // Write your code here
   @Override
   public void draw() {
        System.out.println(" ---- ");
        System.out.println("|    |");
        System.out.println("|    |");
        System.out.println(" ---- ");
   }
}

class Triangle implements Shape {
    // Write your code here
   @Override
   public void draw() {
        System.out.println("  /\\");
        System.out.println(" /  \\");
        System.out.println("/____\\");
   }
}

public class ShapeFactory {
    /**
     * @param shapeType a string
     * @return Get object of type Shape
     */
    public Shape getShape(String shapeType) {
        // Write your code here
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("Triangle")) {
            return new Triangle();
        } else if(shapeType.equalsIgnoreCase("Rectangle")) {
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("Square")) {
            return new Square();
        }
        return null;
    }
}
