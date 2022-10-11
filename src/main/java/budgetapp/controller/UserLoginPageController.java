package budgetapp.controller;

import budgetapp.model.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
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

    public UserLoginPageController(User user){
        this.user = user;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/LoginPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        setCharacterizedProfile(user);


    }


    @FXML
    public void userLogin(ActionEvent event) throws IOException{
        passwordValidity(user);
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

    public void setDisplayName(User user){
        fullName.toString().equals(user.getLastName() + " " + user.getFirstName());
    }

    public void setUserProfile(User user){
        profilePicture.toString().equals(user.getProfilePicture());
    }

    public void setCharacterizedProfile(User user){
        setDisplayName(user);
        setUserProfile(user);
    }


    @FXML
    public void returnToFrontPage(Event event) throws IOException{
        //FrontPageController frontPageController = new FrontPageController();
        //this.getScene().setRoot(frontPageController);
    }




}
