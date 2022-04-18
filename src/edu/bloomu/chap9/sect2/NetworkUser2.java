package edu.bloomu.chap9.sect2;

import edu.bloomu.chap9.sect1.NetworkUser;

/**
 * An immutable network user with a login and password
 *
 * @author Nathaniel Bladen
 */
public class NetworkUser2 {
    private final String login;
    private final String password;

    /**
     * Constructs a network user with a login and password.
     */
    public NetworkUser2(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Returns the login name
     */
    public String getLogin(){
        return login;
    }

    /**
     * Returns the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns this network users login and password
     */
    @Override
    public String toString() {
        return login + " (" + password + ")";
    }

    /**
     * Compares this network user with a given object for order, returns a negative
     * integer, zero, or a positive integer depending on whether this user's login
     * comes before, is the same as, or comes after the login of o
     */
   /** @Override
    public int compareTo(Object o) {
        NetworkUser2 n = (NetworkUser2) o;
        return this.login.compareTo(n.getLogin());
    }*/
}
