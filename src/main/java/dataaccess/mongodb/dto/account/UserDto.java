package dataaccess.mongodb.dto.account;

import dataaccess.mongodb.dto.Dto;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public final class UserDto implements Dto {

    @BsonId
    private ObjectId _id;

    private String firstName;

    private String lastName;

    private String password;

    public ObjectId getId() {
        return _id;
    }

    public UserDto setId(ObjectId id) {
        this._id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + _id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
