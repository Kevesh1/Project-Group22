package budgetapp.model.account;

import budgetapp.model.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    private User user;


    @BeforeEach
    void setUp(){
        this.user = new User("Jacob Westerberg", "OMG2012again", "mario");
        this.user.setUserID("1");
        this.user.setFirstName("Bengt");
        this.user.setLastName("Ingvar");
        this.user.setElderlyAdjusted(false);
        this.user.setFamilyShare(false);

    }

    @Test
    void getUsername(){
        assertEquals("Jacob Westerberg", user.getUsername());
    }

    @Test
    void getFirstName(){
        assertEquals("Bengt", user.getFirstName());
    }

    @Test
    void getLastName(){
        assertEquals("Ingvar", user.getLastName());
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
    void isElderlyAdjusted(){
        assertFalse(user.isElderlyAdjusted());
    }

    @Test
    void isFamilyShare(){
        assertFalse(user.isFamilyShare());
    }

    @Test
    void setFirstName(){
        user.setFirstName("Bengt");
        assertEquals("Bengt", user.getFirstName());
    }

    @Test
    void setLastName(){
        user.setLastName("Rolf");
        assertEquals("Rolf", user.getLastName());
    }

    @Test
    void setPassword(){
        user.setPassword("OMG2013again");
        assertEquals("OMG2013again", user.getPassword());
    }

    @Test
    void setProfilePicture(){
        user.setProfilePicture("wario");
        assertEquals("wario", user.getProfilePicture());
    }

    @Test
    void setUserID(){
        user.setUserID("2");
        assertEquals("2", user.getUserID());
    }

    @Test
    void setFamilyShareFalse(){
        user.setFamilyShare(false);
        assertFalse(user.isFamilyShare());
    }

    @Test
    void setFamilyShareTrue(){
        user.setFamilyShare(true);
        assertTrue(user.isFamilyShare());
    }

    @Test
    void setElderlyAdjustedFalse(){
        user.setElderlyAdjusted(false);
        assertFalse(user.isElderlyAdjusted());
    }

    @Test
    void setElderlyAdjustedTrue(){
        user.setElderlyAdjusted(true);
        assertTrue(user.isElderlyAdjusted());
    }

}
