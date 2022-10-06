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
        profileCards = profileIconList();
        profileSelectionContainer.getChildren().clear();
        for (ProfileIcon profileIcon : profileCards) {
            ProfilePictureCardController profilePictureCardController = new ProfilePictureCardController(this, profileIcon);
            profileSelectionContainer.getChildren().add(ProfilePictureCardController);
        }
    }

    public void initilizeProfileCards(){
        profileCards = new ArrayList<>(profileIconList());
        for(ProfileIcon profileIcon : profileCards){
            ProfilePictureCardController profilePictureCardController = new ProfilePictureCardController(this, profileIcon);
            profileSelectionContainer.getChildren().add(profilePictureCardController);

        }
    }
     */

    //should be moved to model
    public List<ProfileIcon> profileIconList(){
        List<ProfileIcon> allProfileIcons = new ArrayList<>();

        ProfileIcon profileIcon = new ProfileIcon();
        profileIcon.setProfilePicture("/budgetapp/img/Mario.png");
        allProfileIcons.add(profileIcon);

        return allProfileIcons;


    }

}
