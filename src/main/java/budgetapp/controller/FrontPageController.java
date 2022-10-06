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

    //should be moved to model
    public List<User> userList(){
        List<User> allUsers = new ArrayList<>();

        User user = new User("Sven", "Svensson", "omg");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(1);
        allUsers.add(user);

        user = new User("Jacob","West","Di1");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID(2);
        allUsers.add(user);

        user = new User("Sigfrid","Bort","2d");
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

    @FXML
    public void logoutAction(ActionEvent event) throws IOException {
        App app = new App();
        app.changeScene("AccountLoginView.fxml");
    }


}
