package budgetapp.controller;

import budgetapp.App;
import budgetapp.model.Account;
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

    public FrontPageController(Account account) {
        System.out.println(account.getUsername());
    }


    public void initialize() {
        userCards = User.userList();
        userCardContainer.getChildren().clear();
        for (User user : userCards) {
            UserCardController userCardController = new UserCardController(this, user);
            userCardContainer.getChildren().add(userCardController);
        }
        userCardContainer.getChildren().add(new NewUserCardController(this));

    }

    public void initilizeUserCards(){
        userCards = new ArrayList<>(User.userList());
            for(User user : userCards){
                UserCardController userCardController = new UserCardController(this,user);
                userCardContainer.getChildren().add(userCardController);

            }
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
