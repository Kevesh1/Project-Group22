package budgetapp.controller.usermodify;

import budgetapp.model.account.Account;
import budgetapp.model.account.ProfileIcon;
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

    public final Account account;

    @FXML
    public ProfileIcon profileIcon;

    @FXML
    public ImageView profilePicture;


    public ProfilePictureCardController(SelectProfilePictureController parentController, ProfileIcon profileIcon, Account account) {
        this.profileIcon = profileIcon;
        this.parentController = parentController;
        this.account = account;
        loadCard();
    }

    private void loadCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/userView/ProfilePictureCard.fxml"));
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

    @FXML
    public void initialize(){
        setIconData(profileIcon);
    }


    public void setIconData(ProfileIcon profileIcon){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/budgetapp/img/profilepictures/" + profileIcon.getProfilePicture() +".png")));
        profilePicture.setImage(image);
    }


    @FXML
    void profilePictureSelected(ActionEvent event) {
        UserCreateViewController userCreateViewController = new UserCreateViewController(account);
        userCreateViewController.setChooseProfilePictureButton(profileIcon.getProfilePicture());
        this.getScene().setRoot(userCreateViewController);
        //Later upgrade with more information saved
    }


}
