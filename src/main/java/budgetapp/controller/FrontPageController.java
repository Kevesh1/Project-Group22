package budgetapp.controller;

import budgetapp.App;
import budgetapp.model.Account;
import budgetapp.model.User;
import com.mongodb.client.FindIterable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FrontPageController extends AnchorPane {


    @FXML
    public HBox userCardContainer;

    List<User> userCards;

    public User selectedUser;

    @FXML
    public void initialize() {
        updateUserCards(userCards);
    }

    public FrontPageController(Account account) {
        System.out.println("ACCOUNT");
        System.out.println(account.getUsers());
        userCards = account.getUsers();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/FrontPage.fxml"));
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

    public void updateUserCards(List<User> userCards) {
        userCardContainer.getChildren().clear();
        for (User user : userCards) {
            UserCardController userCardController = new UserCardController(this, user);
            userCardContainer.getChildren().add(userCardController);
        }
        userCardContainer.getChildren().add(new AddUsercardController());

    }

    public void initializeUserCards(){
        userCards = new ArrayList<>(User.userList());
            for(User user : userCards){
                UserCardController userCardController = new UserCardController(this, user);
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
