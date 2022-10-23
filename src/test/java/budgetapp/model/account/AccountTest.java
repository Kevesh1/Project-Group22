package budgetapp.model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp(){
        this.account = new Account("Jacob Westerberg", "BraMedGlass17");
        this.account.setId("3");
        User user1 = new User("Jake", "HomeBome55", "mario");

    }

    @Test
    void getUsername(){
        assertEquals("Jacob Westerberg", account.getUsername());
    }

    @Test
    void getPassword(){
        assertEquals("BraMedGlass17", account.getPassword());
    }

    @Test
    void getID(){
        assertEquals("3", account.getId());
    }

    /*
    @Test
    void getUsers() {
        assertEquals("Jake", account.getUsers().get(1).getUsername());

    }
    
     */

    @Test
    void accountToStringTest(){
        assertEquals("Account{username='Jacob Westerberg', password='BraMedGlass17', users=null, id='3'}", account.toString());
    }

    @Test
    void createAccountObject(){
        Account account = new Account();
        assertEquals("Account{username='null', password='null', users=null, id='null'}", account.toString());
    }

}
