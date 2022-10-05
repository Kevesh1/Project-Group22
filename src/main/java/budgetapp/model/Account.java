package budgetapp.model;

import java.util.List;

public class Account {

    private String username;
    private String password;
    private List<User> users;

    public Account(String username, String password, List<User> users) {
        this.username = username;
        this.password = password;
        this.users = users;
    }

    public Account() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
