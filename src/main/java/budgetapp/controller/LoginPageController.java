package budgetapp.controller;

import budgetapp.App;
import budgetapp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button returnFrontButton;

    @FXML
    private Image profilePicture;

    @FXML
    private PasswordField passwordField;


    public void userLogin(ActionEvent event) throws IOException{
        passwordValidity();
    }

    public void passwordValidity() throws IOException{
        App app = new App();
        if(passwordField.getText().toString().equals("OMG")){     /*later connect with user class*/
           app.changeScene("MainView.fxml");
        }

    }

    public void returnToFrontPage(ActionEvent event) throws IOException{
        
    }
}
