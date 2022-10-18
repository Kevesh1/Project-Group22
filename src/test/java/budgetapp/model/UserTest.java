package budgetapp.model;

import budgetapp.model.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    private User user;


    @BeforeEach
    void setUp(){
        user = new User("Jacob Westerberg", "OMG2012again", "mario");
        user.setUserID("1");


    }


    @Test
    void getFirstName(){
        assertEquals("Jacob", user.getFirstName());
    }

    @Test
    void getLastName(){
        assertEquals("Westerberg", user.getLastName());
    }

    @Test
    void getPassword(){
        assertEquals("OMG2012again", user.getPassword());
    }

    @Test
    void getProfilePicture(){
        assertEquals("mario", user.getProfilePicture());
    }

    @Test
    void getUserID(){
        assertEquals("1", user.getUserID());
    }

    @Test
    void setFirstName(){
        user.setFirstName("Bengt");
        assertEquals("Jacob", user.getFirstName());
    }

    @Test
    void setLastName(){
        user.setLastName("Rolf");
        assertEquals("Rolf", user.getLastName());
    }

    @Test
    void setPassword(){
        user.setLastName("OMG2013again");
        assertEquals("OMG2013again", user.getPassword());
    }

    @Test
    void setProfilePicture(){
        user.setProfilePicture("wario");
        assertEquals("wario", user.getProfilePicture());
    }

    @Test
    void setUserID(){
        user.setProfilePicture("2");
        assertEquals("2", user.getUserID());
    }


}
