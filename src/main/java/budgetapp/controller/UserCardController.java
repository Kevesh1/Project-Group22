package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class UserCardController extends VBox {

    FrontPageController parentController;

    @FXML
    User user;

    @FXML
    public Label firstNameCard;

    @FXML
    public ImageView profilePictureCard;

    @FXML
    public Button userButtonCard;


    public UserCardController(FrontPageController parentController, User user) {
        this.user = user;
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/userCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }


    }

    public void setCardData(User user){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream(user.getProfilePicture())));
        profilePictureCard.setImage(profilePicture);
        firstNameCard.setText(user.getFirstName());
    }

    @FXML
    public void initilize(){
        setCardData(user);
    }


    @FXML
    public void selectUserAction() {
        parentController.loginToUser(user);

    }
}
