package budgetapp.model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileIconTest {

    private ProfileIcon profileIcon;

    private List<ProfileIcon> profileCards;

    @BeforeEach
    void setUp(){
        profileCards = ProfileIcon.profileIconList();

    }




    @Test
    void getProfilePicture(){
        profileIcon = new ProfileIcon();
        profileIcon.setProfilePicture("mario");
        assertEquals("mario", profileIcon.getProfilePicture());
    }

    @Test
    void getGIF(){
        profileIcon = new ProfileIcon();
        profileIcon.setProfileGif("bang");
        assertEquals("bang", profileIcon.getProfileGif());
    }

    @Test
    void testListCorrectIconMario(){
        ProfileIcon index0 = profileCards.get(0);
        assertEquals("mario", index0.getProfilePicture());
    }

    @Test
    void testListCorrectIconLuigi(){
        ProfileIcon index1 = profileCards.get(1);
        assertEquals("luigi", index1.getProfilePicture());
    }

    @Test
    void testListCorrectIconPeach(){
        ProfileIcon index2 = profileCards.get(2);
        assertEquals("peach", index2.getProfilePicture());
    }

    @Test
    void testListCorrectIconDonkey(){
        ProfileIcon index3 = profileCards.get(3);
        assertEquals("donkey", index3.getProfilePicture());
    }

    @Test
    void testListCorrectIconWario(){
        ProfileIcon index4 = profileCards.get(4);
        assertEquals("wario", index4.getProfilePicture());
    }

    @Test
    void testListCorrectIconWaluigi(){
        ProfileIcon index5 = profileCards.get(5);
        assertEquals("waluigi", index5.getProfilePicture());
    }



}
