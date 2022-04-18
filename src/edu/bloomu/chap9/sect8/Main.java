package edu.bloomu.chap9.sect8;

import javax.naming.PartialResultException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Nathaniel Bladen
 */
public class Main {
    public static void main(String[] args) {
        String[] fruits = {"PEACH","PINEAPPLE", "PLUM", "GUAVA", "KIWI"};

//
//        class MyComparator implements Comparator<String>{
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.length() - s2.length();
//
//            }
//        }

        Arrays.sort(fruits, (s1, s2) -> s1.length() - s2.length());

        for (String f : fruits){

            System.out.println(f);
        }

    }
}
