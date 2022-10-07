package budgetapp.controller;

import budgetapp.model.ProfileIcon;
import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class SelectProfilePictureController {

    @FXML
    private HBox profileSelectionContainer;

    List<ProfileIcon> profileCards;




    /*
    public void initialize() {
        profileCards = ProfileIcon.profileIconList();
        profileSelectionContainer.getChildren().clear();
        for (ProfileIcon profileIcon : profileCards) {
            ProfilePictureCardController profilePictureCardController = new ProfilePictureCardController(this, profileIcon);
            profileSelectionContainer.getChildren().add(ProfilePictureCardController);
        }
    }

    public void initilizeProfileCards(){
        profileCards = new ArrayList<>(ProfileIcon.profileIconList());
        for(ProfileIcon profileIcon : profileCards){
            ProfilePictureCardController profilePictureCardController = new ProfilePictureCardController(this, profileIcon);
            profileSelectionContainer.getChildren().add(profilePictureCardController);

        }
    }

     */


}
