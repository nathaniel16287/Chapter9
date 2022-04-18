package edu.bloomu.chap9.sect2;

//import edu.bloomu.chap9.sect1.LineSegment;

import edu.bloomu.chap9.sect1.NetworkUser;
import edu.bloomu.chap9.sect1.SortedListOfNetworkUsers;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Test program for the sorted list class
 *
 * @author Nathaniel Bladen
 */
public class SortedListTester {
    public static void main(String[] args) {
        SortedList list = new SortedList();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        final int numSegments = 10;
        for (int i = 0; i < numSegments; i++){
            double a = rand.nextDouble(0, 10);
            double b = rand.nextDouble(0, 10);
            double c = rand.nextDouble(0, 10);
            double d = rand.nextDouble(0, 10);
            list.add(new LineSegment2(a,b,c,d));
        }

        //verify if the line segments are in order
        for (int i = 0; i < numSegments; i++){
            System.out.println(list.get(i));
        }
        System.out.println();

        // insert 4 network users into an empty
        /**
        list = new SortedList();
        NetworkUser2 n1 = new NetworkUser2("Washington", "usa123");
        NetworkUser2 n2 = new NetworkUser2("Adams", "july4hello");
        NetworkUser2 n3 = new NetworkUser2("Jefferson", "virginia123");
        NetworkUser2 n4 = new NetworkUser2("Madison", "password123");
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
         */

        // Verify that users are stored and sorted by user login
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        /**
        list = new SortedList();
        MyClass m1 = new MyClass("yesterday");
        MyClass m2 = new MyClass("tomorrow");
        MyClass m3 = new MyClass("now");
        list.add(m1);
        list.add(m2);
        list.add(m3);
        System.out.println(list.get(0);
        System.out.println(list.get(1);
        System.out.println(list.get(2);
         */
    }
}
