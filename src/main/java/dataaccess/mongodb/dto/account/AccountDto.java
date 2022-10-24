package dataaccess.mongodb.dto.account;

import dataaccess.mongodb.dto.Dto;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Johannes
 */
public final class AccountDto implements Dto {

    private ObjectId _id;

    private String username;

    private String password;

    private List<UserDto> users;

    public ObjectId getId() {
        return _id;
    }

    public AccountDto setId(ObjectId id) {
        this._id = id;
        return this;
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
                "_id=" + _id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", users=" + users +
                '}';
    }
}
