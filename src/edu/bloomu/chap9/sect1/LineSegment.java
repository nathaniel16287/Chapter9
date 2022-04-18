package edu.bloomu.chap9.sect1;

import javax.sound.sampled.Line;

/**
 * An immutable line segment
 *
 * @author Nathaniel Bladen
 */
public class LineSegment {
    //coordinate of enpoints
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;

    /**
     * Constructs a line segment with enpoints (x1, y1 and x2, y2)
     */
    public LineSegment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Returns slope of this line segment
     */
    public double slope(){
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * Returns the length of this line segment
     */
    public double length(){
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy); // standard distance formula

    }
    /**
     * Returns the state of this line segment
     */
    public String toString(){
        String endPoint = String.format("(%.2f. %.2f)", x1, y1);
        String endPoint2 = String.format("(%.2f. %.2f)", x2, y2);
        String length = String.format("%.2f",length());
        String slope = String.format("%.2f",slope());
        return String.format("[%s; %s length = %s; slope = %s]",
                endPoint, endPoint2, length, slope);

    }

    /**
     * test program
     */
    public static void main(String[] args) {
        LineSegment s = new LineSegment(2.3, 5.8,3.1,9.7);
        System.out.println(s);

        // a vertical line segment has infinite slope
        LineSegment t = new LineSegment(2.3, 5.8, 2.3, 9.7);
        System.out.println(t);
    }

}
