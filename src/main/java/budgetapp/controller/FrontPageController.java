package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontPageController {


    @FXML
    private HBox userCardContainer;

    List<User> userCards;


    public void initialize(URL location, ResourceBundle resources){
        userCards = new ArrayList<>(getUserCard());
        try{
            for(User user : userCards){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("userCard.fxml"));

                ScrollPane scrollPane = fxmlLoader.load();
                UserCardController userCardController = fxmlLoader.getController();
                userCardController.setCardData(user);

                userCardContainer.getChildren().add(scrollPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> getUserCard(){
        List<User> allUserCards = new ArrayList<>();

        User user = new User();
        user.setFirstName("Erik");
        user.setProfilePicture("budgetapp/img/BlankProfilePicture.png");
        allUserCards.add(user);

        user = new User();
        user.setFirstName("Jacob");
        user.setProfilePicture("budgetapp/img/BlankProfilePicture.png");
        allUserCards.add(user);

        return allUserCards;

    }

}
