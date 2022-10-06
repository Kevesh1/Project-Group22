package budgetapp.controller;

import budgetapp.model.ProfileIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class ProfilePictureCardController extends AnchorPane {

    @FXML
    private ImageView profilePicture;

    @FXML
    void profilePictureSelected(ActionEvent event) {

    }

    public ProfilePictureCardController(SelectProfilePictureController parentController) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/ProfilePictureCard.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
    }

    public void setCardData(ProfileIcon profileIcon){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream(profileIcon.getProfilePicture())));
    }



}
