package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontPageController {


    @FXML
    private HBox userCardContainer;

    List<User> userCard;

    public void initialize(URL location, ResourceBundle resources){

    }

    private List<User> getUserCard(){
        List<User> allUserCards = new ArrayList<>();

        User user = new User();
        user.setFirstName("Erik");
        user.setProfilePicture("");
        allUserCards.add(user);

        user = new User();
        user.setFirstName("Jacob");
        user.setProfilePicture("");
        allUserCards.add(user);

        return allUserCards;

    }

}
