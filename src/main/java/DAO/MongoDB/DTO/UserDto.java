package DAO.MongoDB.DTO;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public final class UserDto implements Dto {

    private ObjectId id;

    private String firstName;

    private String lastName;

    private String password;

    public ObjectId getId() {
        return id;
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
}
