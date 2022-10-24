package budgetapp.controller.loginController;

import budgetapp.controller.MainController;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserLoginPageController extends AnchorPane {

    @FXML
    public Button returnFrontButton;

    @FXML
    public ImageView profilePicture;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Text fullName;

    public User user;

    public final Account account;

    public UserLoginPageController(User user, Account account){
        this.user = user;
        this.account = account;
        loadCurrentView();

    }

    private void loadCurrentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/loginView/LoginPage.fxml"));
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
    public void userLogin(ActionEvent event) throws IOException{
        passwordValidity(user);
    }

    @FXML
    public void onEnter(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            passwordValidity(user);
        }
    }

    public void passwordValidity(User user) throws IOException{
        if(passwordField.getText().toString().equals(user.getPassword())){
            createMainView(user);
        }

    }

    public void createMainView(User user) {
        MainController mainController = new MainController(user);
        this.getScene().setRoot(mainController);
    }

    private void createFrontPage() {
        FrontPageController frontPageController = new FrontPageController(account);
        this.getScene().setRoot(frontPageController);
    }

    public void setDisplayName(){
        fullName.setText(user.getUsername());
    }

    public void setUserProfile(){
        Image image = new Image("/budgetapp/img/profilepictures/" + user.getProfilePicture() + ".png");
        profilePicture.setImage(image);
    }

    @FXML
    public void initialize(){
        setDisplayName();
        setUserProfile();
    }


    @FXML
    public void returnToFrontPage(Event event) throws IOException{
        createFrontPage();
    }


}
