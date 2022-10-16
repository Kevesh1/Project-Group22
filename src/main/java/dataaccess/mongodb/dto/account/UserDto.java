package dataaccess.mongodb.dto.account;

import dataaccess.mongodb.dto.Dto;
import org.bson.types.ObjectId;

public final class UserDto implements Dto {

    private ObjectId id;

    private String account;

    private String username;

    private String password;

    public ObjectId getId() {
        return id;
    }

    public UserDto setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getAccount() {
        return account;
    }

    public UserDto setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
