package budgetapp.controller.users;

import budgetapp.model.account.Account;
import budgetapp.model.account.ProfileIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectProfilePictureController extends AnchorPane {

    @FXML
    private HBox profileSelectionContainer;

    List<ProfileIcon> profileCards;

    UserCreateViewController parentController;

    public final Account account;

    public SelectProfilePictureController(Account account){
        loadCurrentView();
        this.account = account;
    }

    private void loadCurrentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/SelectProfilePictureView.fxml"));
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


    public void updatePictureCards() {
        profileCards = ProfileIcon.profileIconList();
        profileSelectionContainer.getChildren().clear();
        for (ProfileIcon profileIcon : profileCards) {
            ProfilePictureCardController profilePictureCardController = new ProfilePictureCardController(this, profileIcon, account);
            profileSelectionContainer.getChildren().add(profilePictureCardController);
        }
    }

    @FXML
    public void initilize(){
        profileCards = new ArrayList<>(ProfileIcon.profileIconList());
        for(ProfileIcon profileIcon : profileCards){
            ProfilePictureCardController profilePictureCardController = new ProfilePictureCardController(this, profileIcon, account);
            profileSelectionContainer.getChildren().add(profilePictureCardController);

        }
    }








}
