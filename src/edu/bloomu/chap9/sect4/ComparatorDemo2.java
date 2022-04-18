package edu.bloomu.chap9.sect4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Prompts the user for a sentence and outputs the words in ASCII order and
 * case-insensitive ASCII order
 *
 * @author Nathaniel Bladen
 */
public class ComparatorDemo2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String [] words = in.nextLine().split(" ");
        System.out.println();

        // Remove punctuation from words.
        for (int i = 0; i < words.length; i++){
            words[i] = words[i].replaceAll("[,.?!]","");
        }

        // Sort in ASCII order and output
        System.out.print("Words in ASCII order: ");
        Arrays.sort(words);
        for (String w : words){
            System.out.print(w + " ");
        }
        // Sort in case-insensitve ASCII order, now does different ordering
        System.out.print("Words in ASCII order: ");
        Arrays.sort(words, new MyComparator());
        for (String w : words){
            System.out.print(w + " ");
        }
        // you cannot instantiate an interface
        // This would not work or return anything
        //Comparator<String> c = new Comparator<String>();
        //c.compareTo();
    }
}
class MyComparator implements Comparator<String>{
    /**
     * Returns a negative number, zero, or a positive number depending on whether
     * s1 is less than, equal to, or greater than s2 in case-insensitive ASCII order
     */
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
    private int VowelCount(String s) {
        int count = 0;
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == 'A' || c == 'E' || c == 'I'|| c == 'O' || c == 'U'){
                count++;
            }
        }

        return count;
    }
}
