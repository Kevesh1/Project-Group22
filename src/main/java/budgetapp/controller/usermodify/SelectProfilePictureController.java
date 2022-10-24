package budgetapp.controller.usermodify;

import budgetapp.model.account.Account;
import budgetapp.model.account.ProfileIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class SelectProfilePictureController extends AnchorPane {

    @FXML
    private HBox profileSelectionContainer;

    List<ProfileIcon> profileCards;

    UserCreateViewController parentController;

    public final Account account;

    public SelectProfilePictureController(Account account){
        this.account = account;
        profileCards = ProfileIcon.profileIconList();
        loadCurrentView();
    }

    private void loadCurrentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/userView/SelectProfilePictureView.fxml"));
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


    public void updatePictureCards(List<ProfileIcon> profileCards) {
        profileSelectionContainer.getChildren().clear();
        for (ProfileIcon profileIcon : profileCards) {
            ProfilePictureCardController profilePictureCardController = new ProfilePictureCardController(this, profileIcon, account);
            profileSelectionContainer.getChildren().add(profilePictureCardController);
        }
        profileCards.clear();
    }


    @FXML
    public void initialize(){
        updatePictureCards(profileCards);
    }


}
