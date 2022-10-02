package budgetapp.controller;

import budgetapp.model.CategoryItem;
import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.event.AncestorEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontPageController {


    @FXML
    public HBox userCardContainer;

    List<User> userCards;


    public void initialize() {
        userCards = userList();
        userCardContainer.getChildren().clear();
        for (User user : userCards) {
            UserCardController userCardController = new UserCardController(this, user);
            userCardContainer.getChildren().add(userCardController);
        }
    }

    public List<User> userList(){
        List<User> allUsers = new ArrayList<>();

        User user = new User();
        user.setFirstName("Erik");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(1);
        allUsers.add(user);

        user = new User();
        user.setFirstName("Jacob");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(2);
        allUsers.add(user);

        user = new User();
        user.setFirstName("Sigfrid");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(3);
        allUsers.add(user);

        return allUsers;

    }

}
