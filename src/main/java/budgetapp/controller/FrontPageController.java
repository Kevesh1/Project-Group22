package budgetapp.controller;

import budgetapp.App;
import budgetapp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.io.IOException;
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

    @FXML
    public void loginToUserAction(ActionEvent event) throws IOException {
        App app = new App();
        app.changeScene("LoginPage.fxml");
    }

    @FXML
    public void createNewUserAction(ActionEvent event) throws IOException {
        App app = new App();
        app.changeScene("userCreateView.fxml");
    }

    @FXML
    public void manageUserAccounts(ActionEvent event) throws IOException{
    }

}
