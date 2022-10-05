package DAO.MongoDB.DTO;

import budgetapp.model.User;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

public final class AccountDto {
    private ObjectId id;

    private String username;

    private String password;

    private List<UserDto> users;

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public AccountDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public AccountDto setUsers(List<UserDto> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", users=" + users +
                '}';
    }
}
