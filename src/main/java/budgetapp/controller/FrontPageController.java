package budgetapp.controller;

import budgetapp.App;
import budgetapp.model.Account;
import budgetapp.model.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FrontPageController extends StackPane {

    @FXML
    public AnchorPane createUserAnchorPane;

    @FXML
    public TextField usernameTextField;

    @FXML
    public PasswordField passwordPasswordField;


    @FXML
    public HBox userCardContainer;

    List<User> userCards;

    private User selectedUser;

    private final Account account;

    @FXML
    public void initialize() {
        updateUserCards(userCards);
    }

    public FrontPageController(Account account) {
        this.account = account;
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



    @FXML
    public void createUser(Event event) {

    }

    public void addNewUser() {
        this.getChildren().get(1).toFront();
    }

    public void userSelected(User user) {
        createMainView(user);
    }

    public void createMainView(User user) {
        MainController mainController = new MainController(user);
        this.getScene().setRoot(mainController);
    }

    public void updateUserCards(List<User> userCards) {
        userCardContainer.getChildren().clear();
        for (User user : userCards) {
            UserCardController userCardController = new UserCardController(this, user);
            userCardContainer.getChildren().add(userCardController);
        }
        userCardContainer.getChildren().add(new AddUserCardController(this));

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
