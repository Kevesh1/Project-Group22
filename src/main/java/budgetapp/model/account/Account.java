package budgetapp.model.account;

import org.bson.types.ObjectId;

import java.util.List;

public class Account {

    private String username;
    private String password;
    private List<User> users;
    private ObjectId id;

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

    public ObjectId getId() {
        return id;
    }

    public Account setId(ObjectId id) {
        this.id = id;
        return this;
    }
}
