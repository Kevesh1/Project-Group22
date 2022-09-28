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


    public void userCreate() throws IOException {
            App app = new App();
            app.changeScene("userCreateView.fxml");
    }

}
