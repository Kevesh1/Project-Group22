package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.Objects;

public class UserCardController {

    @FXML
    private Label firstNameCard;

    @FXML
    private ImageView profilePictureCard;

    @FXML
    private Button userButtonCard;

    public void setCardData(User user){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream(user.getProfilePicture())));
        profilePictureCard.setImage(profilePicture);
        firstNameCard.setText(user.getFirstName());
    }
}
