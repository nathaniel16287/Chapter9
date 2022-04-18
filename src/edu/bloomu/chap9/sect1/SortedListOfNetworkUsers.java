package edu.bloomu.chap9.sect1;

import com.sun.source.tree.NewArrayTree;

import java.util.ArrayList;

/**
 * A list of network users stored in ASCII order by login name
 *
 * @author Nathaniel Bladen
 */
public class SortedListOfNetworkUsers {
    private final ArrayList<NetworkUser> users = new ArrayList<>();
    /**
     * Returns the size of this list.
     */
    public int size(){
        return users.size();
    }

    /**
     * Returns the network user at a specific index.
     */
    public NetworkUser get(int i){
        return users.get(i);
    }

    /**
     * Adds a network user to the list at its correct position in sorted order.
     * (the reason to use void is because it doens't really need to return anything
     * so this would make it void)
     */
    public void add(NetworkUser user){
        int insertionPoint = 0;
        String nameToAdd = user.getLogin();

        // When the loop is done the insertionPoint will be the index where we should
        // insert the new user.
        while (insertionPoint < size()) {
            String nameInList = get(insertionPoint).getLogin();
            if (nameToAdd.compareTo(nameInList) <= 0){
                break;
            }
            insertionPoint++;
        }
        users.add(insertionPoint, user);
    }

    public static void main(String[] args) {
        SortedListOfNetworkUsers list = new SortedListOfNetworkUsers();
        NetworkUser n1 = new NetworkUser("Washington", "usa123");
        NetworkUser n2 = new NetworkUser("Adams", "july4hello");
        NetworkUser n3 = new NetworkUser("Jefferson", "virginia123");
        NetworkUser n4 = new NetworkUser("Madison", "password123");
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);

        // Verify that users are stored and sorted by user login
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
