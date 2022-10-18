package budgetapp.model.account;

import java.util.List;
import java.util.regex.Pattern;
import dataaccess.mongodb.dao.account.UserDao;


public class UserManagement {

    public UserManagement() {
    }

    public void createNewUser(String firstName, String lastName, String password, String profilePicture) {
        User user = new User(firstName + " " + lastName, password, profilePicture);
    }

    public void createNewUserWithoutPassword(String firstName, String lastName, String profilePicture) {
        User user = new User(firstName + " " + lastName, null, profilePicture);
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
