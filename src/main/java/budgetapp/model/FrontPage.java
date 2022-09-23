package budgetapp.model;
import java.io.IOException;
import java.util.*;

import budgetapp.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class FrontPage {
    private int numUsers;
    private List<User> usersList;
    private int maxNumUsers;
    private int numSlots;

    @FXML
    private Button userButton1;

    @FXML
    private Button userButton2;

    @FXML
    private Button userButton3;

    @FXML
    private Button userButton4;

    @FXML
    private Image userImage1;

    @FXML
    private Image userImage2;

    @FXML
    private Image userImage3;

    @FXML
    private Image userImage4;

    public FrontPage(int numUsers, String[] Users){
        this.numUsers = numUsers;
        this.maxNumUsers = 4;
        this.numSlots = 4;
    }

    public void userSelect1(ActionEvent event) throws IOException {
        if (!checkProfileSlotEmpty()){

        }
        else{
            userCreate();
        }
    }

    public boolean checkProfileSlotEmpty(){
        return true;
    }

    public void calculateNumUsers(List<User> usersList){
        int numUsers = usersList.size();
    }


    public void userCreate() throws IOException {
        calculateNumUsers(usersList);
        if (getNumUsers() <= getMaxNumUsers()){
            App app = new App();
            app.changeScene("userCreateView.fxml");
        }
    }

    public int getNumUsers(){
        return numUsers;
    }

    public int getMaxNumUsers(){
        return maxNumUsers;
    }
}
