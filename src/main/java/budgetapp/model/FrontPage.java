package budgetapp.model;
import java.util.*;

public class FrontPage {
    private int numUsers;
    private List<User> usersList;
    private int maxNumUsers;



    public FrontPage(int numUsers, String[] Users){
        this.numUsers = numUsers;
        this.Users = Users;
        this.maxNumUsers = 4;
    }

    public int getNumUsers(){
        return numUsers;
    }

    public int getMaxNumUsers(){
        return maxNumUsers;
    }

    public void calculateNumUsers(String[] usersList){
        int numUsers = usersList.size();
    }


    public void userCreate(){
        calculateNumUsers(String[] usersList);
        if (getNumUsers() <= getNumUsers()){

        }

    }
}
