package edu.bloomu.chap9.sect2;


import java.util.ArrayList;

/** Sorted list of arbitrary comparable objects.
 *
 * @author Nathaniel Bladen
 */
public class SortedList {
    private ArrayList<Comparable> list = new ArrayList<>();

    public int size() { return list.size();}

    /**
     * returns the network user at a specified index
     */
    public Comparable get (int i){
        return list.get(i);

    }
    /**
     * Adds an item to the list at correct position in sorted order
     */
    public void add(Comparable itemToAdd) {
        int insertionPoint = 0;
        while (insertionPoint < size()){
            Comparable listItem = get(insertionPoint);
            if(itemToAdd.compareTo(listItem) <= 0) { //late binding
                break;
            }
            insertionPoint++;
        }
        list.add(insertionPoint, itemToAdd);
    }
}
