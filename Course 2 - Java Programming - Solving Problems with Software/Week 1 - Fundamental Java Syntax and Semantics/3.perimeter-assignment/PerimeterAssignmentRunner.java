import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        int numPoints = 0;
        for (Point currPoint : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }
    
    public double getAverageLength(Shape s) {
        double avgLength = 0;
        double perimeter = getPerimeter(s);
        double sides = (double)getNumPoints(s); // The number of sides is equal to the number of points in a 2D shape
        avgLength = perimeter / sides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currSide = prevPt.distance(currPt);
            // System.out.println(currSide);
            if (currSide > largestSide) {
                largestSide = currSide;
            }
            prevPt = currPt;
        }
        return largestSide;
    }


    public double getLargestX(Shape s) {
        double largestX = Double.NEGATIVE_INFINITY;
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            // System.out.println(currX);
            if (currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource currFileResource = new FileResource(f);
            Shape currShape = new Shape(currFileResource);
            double currPerimeter = getPerimeter(currShape);
            // System.out.println(currPerimeter);
            if (currPerimeter > largestPerimeter) {
                largestPerimeter = currPerimeter;
            }
        }
        return largestPerimeter;
    }
    
    public String getFileWithLargestPerimeter() {
        // Put code here
        String largestFileName = "";
        double largestPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource currFileResource = new FileResource(f);
            Shape currShape = new Shape(currFileResource);
            String currFileName = f.getName();
            double currPerimeter = getPerimeter(currShape);
            System.out.println(currFileName + " " + currPerimeter);
            if (currPerimeter > largestPerimeter) {
                largestPerimeter = currPerimeter;
                largestFileName = currFileName;
            }
        }
        return largestFileName;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        // Test the getPerimeter() method
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        // Test the getNumPoints() method
        int points = getNumPoints(s);
        System.out.println("number of points = " + points);
        // Test the getAverageLength() method
        double avg = getAverageLength(s);
        System.out.println("average length = " + avg);
        // Test the getLargestSide() method
        double largest = getLargestSide(s);
        System.out.println("largest side = " + largest);
        // Test the getLargestX() method
        double largestX = getLargestX(s);
        System.out.println("largest x = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Test the getLargestPerimeterMultipleFiles() method
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Test the getFileWithLargestPerimeter() method
        String largestFileName = getFileWithLargestPerimeter();
        System.out.println("File With Largest Perimeter = " + largestFileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.printFileNames();
        pr.testFileWithLargestPerimeter();
    }
}
