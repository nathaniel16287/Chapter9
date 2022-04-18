package edu.bloomu.chap9.sect5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Prompts the user for a line of text and outputs the words in sorted order without
 * duplicates
 *
 *          Enter a line of text: I AM HE AS YOU ARE HE AS YOU ARE ME
 *          Your words: AM ARE AS HE I ME YOU
 * @author Nathaniel Bladen
 */
public class SortingWithoutDuplicates {
    public static void main(String[] args) {
        System.out.print("Enter a line of text: ");
        String[] words = new Scanner(System.in).nextLine().split(" ");
        Set<String> set = new TreeSet<>(Arrays.asList(words));

        System.out.print("Your words: ");
        for (String word : set){
            System.out.print(word + " ");
        }

    }
}
