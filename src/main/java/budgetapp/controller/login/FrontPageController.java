package budgetapp.controller.login;

import budgetapp.controller.MainController;
import budgetapp.controller.users.CreateNewUserController;
import budgetapp.controller.users.UserCardController;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FrontPageController extends AnchorPane {

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
        loadCurrentView();
    }

    private void loadCurrentView() {
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

    public void addNewUser() {
        this.getChildren().get(1).toFront();
    }

    public void loginToUser(User user) {
        if(user.getPassword() == null){
            createMainView(user);
        }
        else{
            createLoginPage(user);
        }
    }

    public void createLoginPage(User user) {
        UserLoginPageController userLoginPageController = new UserLoginPageController(user, account);
        this.getScene().setRoot(userLoginPageController);
    }

    public void createMainView(User user) {
        MainController mainController = new MainController(user);
        this.getScene().setRoot(mainController);
    }

    /*
    public void createCreateNewUserController() {
        CreateNewUserController createNewUserController = new CreateNewUserController();
        this.getScene().setRoot(getParent());
    }
    */

    public void updateUserCards(List<User> userCards) {
        userCardContainer.getChildren().clear();
        for (User user : userCards) {
            UserCardController userCardController = new UserCardController(this, user);
            userCardContainer.getChildren().add(userCardController);
        }
        userCardContainer.getChildren().add(new NewUserCardController(this));

    }

    public void initializeUserCards(){
        userCards = new ArrayList<>(User.userList());
            for(User user : userCards){
                UserCardController userCardController = new UserCardController(this, user);
                userCardContainer.getChildren().add(userCardController);

            }
    }



    @FXML
    public void createUserSelect(ActionEvent event) throws IOException {

    }

    @FXML
    public void manageUserAccountsAction(ActionEvent event) throws IOException{
    }

    @FXML
    public void logoutAction(ActionEvent event) throws IOException {
    }


}
