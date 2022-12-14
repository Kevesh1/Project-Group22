package dataaccess.mongodb.dto.account;

import dataaccess.mongodb.dto.Dto;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * @author Johannes
 */
public final class UserDto implements Dto {

    private ObjectId _id;

    private String account;

    private String username;

    private String password;

    private String profilePicture;

    public ObjectId getId() {
        return _id;
    }

    public UserDto setId(ObjectId _id) {
        this._id = _id;
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

    public String getProfilePictures() {
        return profilePicture;
    }

    public UserDto setProfilePictures(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + _id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
