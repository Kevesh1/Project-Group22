package budgetapp.controller.users;

import budgetapp.controller.login.FrontPageController;
import budgetapp.controller.users.SelectProfilePictureController;
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
    ProfileIcon profileIcon;

    @FXML
    private ImageView profilePicture;

    @FXML
    void profilePictureSelected(ActionEvent event) {
        UserCreateViewController userCreateViewController = new UserCreateViewController(account);
        userCreateViewController.setChooseProfilePictureButton(profilePicture);
        this.getScene().setRoot(userCreateViewController);
        //Later upgrade with more information saved
    }

    public ProfilePictureCardController(SelectProfilePictureController parentController, ProfileIcon profileIcon, Account account) {
        this.profileIcon = profileIcon;
        this.parentController = parentController;
        this.account = account;
        //this.profilePicture = new ImageView(String.valueOf(getClass().getResourceAsStream(profileIcon.getProfilePicture())));
        loadCurrentView();

    }

    private void loadCurrentView() {
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
    }


    @FXML
    public void initilize(){
        setIconData(profileIcon);
    }


    public void setIconData(ProfileIcon profileIcon){
        ImageView profilePicture = new ImageView(String.valueOf(profileIcon.getProfilePicture()));
    }


}
