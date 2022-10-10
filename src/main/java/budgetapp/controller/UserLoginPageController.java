package budgetapp.controller;

import budgetapp.App;
import budgetapp.model.Account;
import budgetapp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserLoginPageController extends AnchorPane {

    @FXML
    private Button returnFrontButton;

    @FXML
    private Image profilePicture;

    @FXML
    private PasswordField passwordField;

    private User user;

    public UserLoginPageController(User user){
        this.user = user;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/MainView.fxml"));
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


    public void userLogin(ActionEvent event, User user) throws IOException{
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


    public void returnToFrontPage(ActionEvent event, Account account) throws IOException{
        FrontPageController frontPageController = new FrontPageController(account);
        this.getScene().setRoot(frontPageController);
    }




}
