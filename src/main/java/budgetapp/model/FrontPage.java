package budgetapp.model;
import java.util.*;
import javafx.scene.*;
import javafx.scene.image.Image;

public class FrontPage {
    private int numUsers;
    private List<User> usersList;
    private int maxNumUsers;



    public FrontPage(int numUsers, String[] Users){
        this.numUsers = numUsers;
        this.maxNumUsers = 4;
    }

    public int getNumUsers(){
        return numUsers;
    }

    public int getMaxNumUsers(){
        return maxNumUsers;
    }

    public void calculateNumUsers(List<User> usersList){
        int numUsers = usersList.size();
    }


    public void userCreate(){
        calculateNumUsers(usersList);
        if (getNumUsers() <= getMaxNumUsers()){
            customizeNewUser();
        }
    }

    public void userDisplayConditions(){
        calculateNumUsers(usersList);
        if (getNumUsers() == 0){
            
        }
        else if (getNumUsers() == 1){

        }
        else if (getNumUsers() == 2){

        }
        else if (getNumUsers() == 3){

        }
        else {

        }
    }

    public void customizeNewUser(){
      /*  firstName =
        User user = new User(); */
    }
}
