package budgetapp.controller;

import budgetapp.model.ProfileIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ProfilePictureCardController extends AnchorPane {

    SelectProfilePictureController parentController;

    @FXML
    ProfileIcon profileIcon;

    @FXML
    private ImageView profilePicture;

    @FXML
    void profilePictureSelected(ActionEvent event) {
    }

    public ProfilePictureCardController(SelectProfilePictureController parentController, ProfileIcon profileIcon) {
        this.profileIcon = profileIcon;
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/ProfilePictureCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        setIconData(profileIcon);

    }

    public void setIconData(ProfileIcon profileIcon){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream(profileIcon.getProfilePicture())));
    }



}
