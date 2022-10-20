package budgetapp.controller.login;

import budgetapp.controller.MainController;
import budgetapp.controller.users.NewUserCardController;
import budgetapp.controller.users.UserCreateViewController;
import budgetapp.controller.users.UserCardController;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import dataaccess.mongodb.dao.account.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
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

    private final UserDao userDao;

    private final Account account;

    @FXML
    public void initialize() {
        updateUserCards(userCards);
    }

    public FrontPageController(Account account) {
        System.out.println("ACCOUNT");
        System.out.println(account.getId());
        userDao = new UserDao();
        this.account = account;
        account.setUsers(userDao.getUsersByUsername(account.getUsername()));

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


    public void createUserCreateViewController(Account account) {
        UserCreateViewController userCreateViewController = new UserCreateViewController(this.account);
        this.getScene().setRoot(userCreateViewController);
    }

    public void createAccountLoginController() {
        AccountLoginController accountLoginController = new AccountLoginController();
        this.getScene().setRoot(accountLoginController);
    }


    public void updateUserCards(List<User> userCards) {
        userCardContainer.getChildren().clear();
        for (User user : userCards) {
            System.out.println("USER");
            UserCardController userCardController = new UserCardController(this, user);
            userCardContainer.getChildren().add(userCardController);
        }
        userCardContainer.getChildren().add(new NewUserCardController(this));

    }


    @FXML
    public void manageUserAccountsAction(ActionEvent event) throws IOException{
    }

    @FXML
    public void logoutAction(ActionEvent event) throws IOException {
        createAccountLoginController();
        //currently unable to re-login
    }


}
