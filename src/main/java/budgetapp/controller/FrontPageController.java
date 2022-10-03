package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;


public class FrontPageController {


    @FXML
    public HBox userCardContainer;

    List<User> userCards;



    public void initialize() {
        userCards = userList();
        userCardContainer.getChildren().clear();
        for (User user : userCards) {
            UserCardController userCardController = new UserCardController(this, user);
            userCardContainer.getChildren().add(userCardController);
        }
        userCardContainer.getChildren().add(new NewUserCardController(this));

    }

    public void initilizeUserCards(){
        userCards = new ArrayList<>(userList());
            for(User user : userCards){
                UserCardController userCardController = new UserCardController(this,user);
                userCardContainer.getChildren().add(userCardController);

            }
    }

    public List<User> userList(){
        List<User> allUsers = new ArrayList<>();

        User user = new User();
        user.setFirstName("Erik");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(1);
        allUsers.add(user);

        user = new User();
        user.setFirstName("Jacob");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(2);
        allUsers.add(user);

        user = new User();
        user.setFirstName("Sigfrid");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(3);
        allUsers.add(user);

        return allUsers;

    }

}
