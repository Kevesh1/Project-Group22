package budgetapp.model.account;

import java.util.regex.Pattern;


public class UserManager {

    public UserManager() {
    }

    public User createNewUser(String firstName, String lastName, String password, String profilePicture) {
        return new User(firstName + " " + lastName, password, profilePicture);
    }

    public User createNewUserWithoutPassword(String firstName, String lastName, String profilePicture) {
        return new User(firstName + " " + lastName, null, profilePicture);
    }

    public boolean samePassword(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }

    public boolean passwordComplexity(String password){
        return passwordLength(password) && passwordCharacterTest(password);
    }

    public boolean passwordLength(String password){
        return password.length() >= 8;
    }

    public boolean passwordCharacterTest(String password) {
        return Pattern.matches("[a-zA-Z]+", password) && Pattern.matches("[0-9]+", password);
    }



}
