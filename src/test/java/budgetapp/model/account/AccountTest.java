package budgetapp.model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {

    private Account account;
    private User user;
    private User user2;

    @BeforeEach
    void setUp(){
        this.account = new Account("Jacob Westerberg", "BraMedGlass17");
        this.account.setId("3");

    }

    @Test
    void getUsername(){
        assertEquals("Jacob Westerberg", account.getUsername());
    }

    @Test
    void setUsername(){
        account.setUsername("Haj Bo");
        assertEquals("Haj Bo", account.getUsername());
    }

    @Test
    void getPassword(){
        assertEquals("BraMedGlass17", account.getPassword());
    }


    @Test
    void setPassword(){
        account.setPassword("gladIDAG55");
        assertEquals("gladIDAG55", account.getPassword());
    }

    @Test
    void getID(){
        assertEquals("3", account.getId());
    }


    @Test
    void setUsers() {
        this.user = new User("Jacob Westerberg", "OMG2012again", "mario");
        this.user.setId("1");
        this.user.setFirstName("Bengt");
        this.user.setLastName("Ingvar");
        this.user.setElderlyAdjusted(false);
        this.user.setFamilyShare(false);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        account.setUsers(userList);
        assertEquals(userList, account.getUsers());

    }

    @Test
    void setMultipleUsers(){
        this.user = new User("Jacob Westerberg", "OMG2012again", "mario");
        this.user.setId("1");
        this.user.setFirstName("Bengt");
        this.user.setLastName("Ingvar");
        this.user.setElderlyAdjusted(false);
        this.user.setFamilyShare(false);
        this.user2 = new User("Jake West", "OMG2013again", "luigi");
        this.user2.setId("2");
        this.user2.setFirstName("Benge");
        this.user2.setLastName("Inge");
        this.user2.setElderlyAdjusted(false);
        this.user2.setFamilyShare(false);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        account.setUsers(userList);
        assertEquals(userList, account.getUsers());

    }

    @Test
    void getUsers() {
        assertEquals(null, account.getUsers());

    }



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
