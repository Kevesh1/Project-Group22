package budgetapp.model.account;

import java.util.List;

/**
 * @author Johannes
 */
public class Account {

    /**
     * Account creates and saves all the information about the account
     */

    private String username;

    private String password;

    private List<User> users;

    private String id;

    /**
     * Account constructor
     * @param username
     * @param password
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Empty contructor for database to load in accounts
     */
    public Account() {

    }

    /**
     * this method gets username
     * @return username
     */

    public String getUsername() {
        return username;
    }

    /**
     * this method sets username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * this method gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * this method sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * this method gets users
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * this method sets users
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * this method gets id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * this method sets id
     * @param id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * this method is for debugging
     * @return String
     */

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", users=" + users +
                ", id='" + id + '\'' +
                '}';
    }
}
