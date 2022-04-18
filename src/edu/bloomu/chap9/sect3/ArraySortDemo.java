package edu.bloomu.chap9.sect3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonsrtates use of the Comparable<T> interface to sort primitive type arrays
 * and arrarys of object
 *
 * @author Nathaniel Bladen
 */
public class ArraySortDemo {
    public static void main(String[] args) {
        sortDoubles();
        sortStrings();
        sortBigIntegers();
        sortMilePaces();
    }

    /**
     * Sorts an array of Milepace objects
     */
    private static void sortMilePaces() {
        MilePace[] milePaces = new MilePace[5];
        // pairs of ints representing min-sec combinations
        int[][] minSec = {
                {5,45},{6, 1},{7,59},{4,43},{5,9}
        };

        for (int i = 0; i < milePaces.length; i++){
            milePaces[i] = new MilePace(minSec[i][0], minSec[i][1]);
        }
        Arrays.sort(milePaces);
        for (MilePace mp: milePaces){
            System.out.print(mp + " ");
        }
        System.out.println();
    }

    private static void sortBigIntegers() {
        BigInteger[] BigInts = new BigInteger[5];
        for (int i = 0; i < BigInts.length; i++){
            BigInts[i] = getRandomBigInteger(15);
        }
        // Sort the array
        Arrays.sort(BigInts);
        System.out.println("Sorted Big Integers");
        for (BigInteger bi : BigInts){
            System.out.printf("  %,d %n", bi);
        }
        System.out.println();

    }
    private static BigInteger getRandomBigInteger(int numDigits){
        String digits = "";
        for (int i = 0; i < numDigits; i++){
            digits += ThreadLocalRandom.current().nextInt(10);

        }
        return new BigInteger(digits);
    }

    private static void sortStrings() {
        String[] pets = {"vicuna", "quokka", "okapi","quagga","ibex"};
        Arrays.sort(pets);
        System.out.print("Sorted strings: ");
        for (String pet : pets){
            System.out.print(pet + " ");
        }
        System.out.println();
    }

    /**
     * Sorts an array of random double values and outputs the results
     */
    private static void sortDoubles() {
        double[] number = new double[5];
        for (int i = 0; i < number.length; i++){
            number[i] = ThreadLocalRandom.current().nextDouble();
        }
        //Sort the array and output the results
        Arrays.sort(number);
        System.out.print("Sorted doubles ");
        for (double num : number){
            System.out.printf("%1.3f ", num);
        }
        System.out.println();
    }

}

/**
 * Represents a runners time for the mile in minutes:secs
 */
class MilePace implements Comparable<MilePace>{
    private final int min;
    private final int sec;

    public MilePace(int min, int sec){
        this.min = min;
        this.sec = sec;
    }

    @Override
    public String toString(){
        return String.format("%d:%02d",min,sec);
    }

    @Override
    public int compareTo(MilePace o) {
        if (this.min == o.min){
            return this.sec - o.sec;
        }
        return this.min - o.min;
    }
}
