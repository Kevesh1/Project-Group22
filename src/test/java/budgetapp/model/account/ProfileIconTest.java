package budgetapp.model.account;

import budgetapp.model.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileIconTest {

    private ProfileIcon profileIcon;

    @BeforeEach
    void setUp(){
        profileIcon = new ProfileIcon();
        profileIcon.setProfilePicture("mario");

    }

    @Test
    void getProfilePicture(){
        assertEquals("mario", profileIcon.getProfilePicture());
    }

    @Test
    void testListExtraction(){
        profileIcon.
    }

}
