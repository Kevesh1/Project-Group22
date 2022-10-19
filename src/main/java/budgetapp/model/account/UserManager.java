package budgetapp.model.account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserManager {

    //"^(?=.*[0-9])(?=.*[a-ö])(?=.*[A-Ö])"
    private final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-ZåäöÅÄÖ])$";
    private final String altPasswordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-ZåäöÅÄÖ])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).$";
    private final Pattern pattern = Pattern.compile(passwordPattern);
    private final Pattern altPattern = Pattern.compile(altPasswordPattern);

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
        return passwordLength(password) && passwordCharacter(password);
    }

    public boolean passwordLength(String password){
        return password.length() >= 8;
    }

    /*
    public boolean passwordCharacterTest(String password) {
        return Pattern.matches("[a-zA-Z0-9]+", password) && Pattern.matches("[0-9]+", password);
    }

     */

    public boolean passwordCharacter(final String password) {
        Matcher matcher = pattern.matcher(password);
        Matcher altMatcher = altPattern.matcher(password);
        return matcher.matches() || altMatcher.matches();
    }


}
