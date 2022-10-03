package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class UserCardController extends VBox {

    @FXML
    public Label firstNameCard;

    @FXML
    public ImageView profilePictureCard;

    @FXML
    public Button userButtonCard;

    public UserCardController(FrontPageController parentController, User user) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/userCard.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
        setCardData(user);
    }

    public void setCardData(User user){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream(user.getProfilePicture())));
        profilePictureCard.setImage(profilePicture);
        firstNameCard.setText(user.getFirstName());
    }
}
