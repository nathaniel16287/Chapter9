package edu.bloomu.chap9.sect1;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A list of line segments sorted by length.
 *
 * @author Nathaniel Bladen
 */
public class SortedListOfLineSegments {
    private final ArrayList<LineSegment> lines = new ArrayList<>();

    /**
     * Return the size of the list
     */
    public int size() {
        return lines.size();
    }

    /**
     * Returns the line segment at a specified index
     */
    public LineSegment get(int i) {
        return lines.get(i);
    }

    /**
     * Adds a line segment to the list in its correct positon in sorted order
     */
    public void add(LineSegment lineSegment){
        int insertionPoint = 0;
        double length = lineSegment.length();

        // When the loop is done, insertionPoint will be the index where we should
        // insert the new line segment
        while (insertionPoint < size()){
            // both of these two lines do the same thing
            double otherLength = get(insertionPoint).length();
            //double otherLength = lines.get(insertionPoint).length();
            if (length <= otherLength){
                break;
            }
            insertionPoint++;
        }

        lines.add(insertionPoint, lineSegment);

    }
    // Just a quick test: Add 10 random line segments to a SortedListOfLineSegments
    // and verify they are in the same order
    public static void main(String[] args) {
        SortedListOfLineSegments lines = new SortedListOfLineSegments();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        final int numSegments = 10000;
        for (int i = 0; i < numSegments; i++){
            double a = rand.nextDouble(0, 10);
            double b = rand.nextDouble(0, 10);
            double c = rand.nextDouble(0, 10);
            double d = rand.nextDouble(0, 10);
            lines.add(new LineSegment(a,b,c,d));
        }

        //verify if the line segments are in order
        for (int i = 0; i < numSegments; i++){
            System.out.println(lines.get(i));
        }
    }

}
