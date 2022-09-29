package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontPageController {


    @FXML
    public HBox userCardContainer;

    List<User> userCards;


    public void initializeUserCards(URL location, ResourceBundle resources){
        userCards = new ArrayList<>(userList());
        try{
            for(User user : userCards){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("userCard.fxml"));

                VBox vBox= fxmlLoader.load();
                UserCardController userCardController = fxmlLoader.getController();
                userCardController.setCardData(user);

                userCardContainer.getChildren().add(vBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> userList(){
        List<User> allUsers = new ArrayList<>();

        User user = new User();
        user.setFirstName("Erik");
        user.setProfilePicture("budgetapp/img/BlankProfilePicture.png");
        user.setUserID(1);
        allUsers.add(user);

        user = new User();
        user.setFirstName("Jacob");
        user.setProfilePicture("budgetapp/img/BlankProfilePicture.png");
        user.setUserID(2);
        allUsers.add(user);

        user = new User();
        user.setFirstName("Sigfrid");
        user.setProfilePicture("budgetapp/img/BlankProfilePicture.png");
        user.setUserID(3);
        allUsers.add(user);

        return allUsers;

    }

}
