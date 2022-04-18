package edu.bloomu.chap9.sect4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates a list of random integers and outputs the contents in unsorted order
 * sorted numerically, and sorted by magnitude.
 *
 * @author Nathaniel Bladen
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        final int size = 20;
        Integer[] a = new Integer[size];

        System.out.printf("%21s", "Unsorted: ");

        for (int i = 0; i < size; i++){
            int num = ThreadLocalRandom.current().nextInt(-99, 100);
            a[i] = num;//Means: a[i] = new Integer(num), this is an example of auto-boxing

            System.out.printf("%+d ", a[i]); //Example of un-boxing
        }
        System.out.println();

        System.out.printf("%21s", "Sorted Numerically: ");
        Arrays.sort(a);
        for (int i : a){
            System.out.printf("%+d ", i);
        }
        System.out.println();
        System.out.printf("%21s", "Sorted by Magnitude: ");
        Arrays.sort(a, new MagnitudeComparator());
        for (int i : a) {
            System.out.printf("%+d ", i);
        }
    }
}

class MagnitudeComparator implements Comparator<Integer> {
    /**
     * Returns a negative number, zero, or a positve number depending on whether the
     * magnitude of i1 is less than, equal to, or greater than the magnitude of i2
     */
    @Override
    public int compare(Integer i1, Integer i2) {
        return Math.abs(i1) - Math.abs(i2);
    }
}