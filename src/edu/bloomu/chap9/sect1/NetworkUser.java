package edu.bloomu.chap9.sect1;

/**
 * An immutable network user with a login and password
 *
 * @author Nathaniel Bladen
 */
public class NetworkUser {
    private final String login;
    private final String password;

    /**
     * Constructs a network user with a login and password.
     */
    public NetworkUser(String login, String password) {
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

    public static void main(String[] args) {
        String x = "bob87";
        String y = "Kjz789";
        NetworkUser user = new NetworkUser(x, y);
        System.out.println(user);
    }
}
