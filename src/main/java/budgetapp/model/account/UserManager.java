package budgetapp.model.account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserManager {
    /**
     * UserManager class. Manages the creation of new users and a password validation
     */

    private final String passwordPattern = "^(?=.*[0-9])(?=.*[a-zåäö])(?=.*[A-ZÅÄÖ]).*$";
    private final String altPasswordPattern = "^(?=.*[0-9])(?=.*[a-zåäö])(?=.*[A-ZÅÄÖ])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$";
    private final Pattern pattern = Pattern.compile(passwordPattern);
    private final Pattern altPattern = Pattern.compile(altPasswordPattern);

    /**
     * UserManager constructor.
     */
    public UserManager() {
    }

    /**
     * creates new user
     * @param firstName
     * @param lastName
     * @param password
     * @param profilePicture
     * @return
     */
    public User createNewUser(String firstName, String lastName, String password, String profilePicture) {
        return new User(firstName + " " + lastName, password, profilePicture);
    }

    /**
     * creates new user without password
     * @param firstName
     * @param lastName
     * @param profilePicture
     * @return
     */
    public User createNewUserWithoutPassword(String firstName, String lastName, String profilePicture) {
        return new User(firstName + " " + lastName, null, profilePicture);
    }

    /**
     * checks if the two entered password matches
     * @param password
     * @param repeatPassword
     * @return boolean
     */

    public boolean samePassword(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }

    /**
     * checks the passwords complexity. Checks length and characters
     * @param password
     * @return boolean
     */
    public boolean passwordComplexity(String password){
        return passwordLength(password) && passwordCharacter(password);
    }

    /**
     * checks if password length is 8 or more
     * @param password
     * @return boolean
     */
    public boolean passwordLength(String password){
        return password.length() >= 8;
    }

    /**
     * checks if password contains valid characters between two options
     * @param password
     * @return boolean
     */

    public boolean passwordCharacter(String password) {
        Matcher matcher = pattern.matcher(password);
        Matcher altMatcher = altPattern.matcher(password);
        return matcher.matches() || altMatcher.matches();
    }

}
