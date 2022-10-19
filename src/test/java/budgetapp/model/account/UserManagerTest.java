package budgetapp.model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserManagerTest {

    private UserManager userManager;

    private User user;

    private String firstName;
    private String lastName;
    private String createPassword;
    private String profilePictureName;
    private String passwordInput;
    private String passwordInputRepeat;


    @BeforeEach
    void setUp(){
        this.userManager = new UserManager();
        this.user = new User();
        this.firstName = "Jacob";
        this.lastName = "Westerberg";
        this.createPassword = "olaKONNY5000";
        this.profilePictureName = "luigi";

    }


    @Test
    void createNewUserTest(){
        user = userManager.createNewUser(firstName, lastName, createPassword, profilePictureName);
        assertEquals("Jacob Westerberg", user.getUsername());
        assertEquals("olaKONNY5000", user.getPassword());
        assertEquals("luigi", user.getProfilePicture());

    }

    @Test
    void createNewUserWithoutPasswordTest(){
        user = userManager.createNewUserWithoutPassword(firstName, lastName, profilePictureName);
        assertEquals("Jacob Westerberg", user.getUsername());
        assertNull(user.getPassword());
        assertEquals("luigi", user.getProfilePicture());

    }

    @Test
    void samePasswordTrueTest(){
        passwordInput = "BaNGBONGGIGA5000";
        passwordInputRepeat = "BaNGBONGGIGA5000";
        assertTrue(userManager.samePassword(passwordInput, passwordInputRepeat));
    }

    @Test
    void samePasswordFalseTest(){
        passwordInput = "BaNGBONGGIGA5000";
        passwordInputRepeat = "BaNGBONGGIGA4000";
        assertFalse(userManager.samePassword(passwordInput, passwordInputRepeat));
    }

    @Test
    void passwordCharacterTrueTest(){
        passwordInput = "BaNGBONGGIGA5000";
        assertTrue(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterOnlyBigLettersTest(){
        passwordInput = "BANGBONGGIGA";
        assertFalse(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterOnlyNumbersTest(){
        passwordInput = "5000";
        assertFalse(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterOnlySmallLettersTest(){
        passwordInput = "bangbonggiga";
        assertFalse(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterOnlySmallLettersAndBigLettersTest(){
        passwordInput = "bAngbongGiga";
        assertFalse(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterOnlySmallLettersAndNumbersTest(){
        passwordInput = "bangbonggiga5000";
        assertFalse(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterOnlyBIGLettersAndNumbersTest(){
        passwordInput = "BANGBONGGIGA5000";
        assertFalse(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterTrueWithSpecialTest(){
        passwordInput = "BaNGBONGGIGA5000.";
        assertTrue(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordCharacterTrueWithShortTextTest(){
        passwordInput = "BangB5";
        assertTrue(userManager.passwordCharacter(passwordInput));
    }

    @Test
    void passwordLengthFalseTest(){
        passwordInput = "Hajp";
        assertFalse(userManager.passwordLength(passwordInput));
    }

    @Test
    void passwordLengthTrueTest(){
        passwordInput = "HajpKUNGEN23";
        assertTrue(userManager.passwordLength(passwordInput));
    }

    //Är egentligen redan testad
    @Test
    void passwordComplexityTrueTest(){
        passwordInput = "BaNGBONGGIGA5000.";
        assertTrue(userManager.passwordComplexity(passwordInput));
    }

    @Test
    void passwordComplexityFalseTest(){
        passwordInput = "H5";
        assertFalse(userManager.passwordComplexity(passwordInput));
    }

}
