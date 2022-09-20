package budgetapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button returnFrontButton;

    @FXML
    private Image profilePicture;

    @FXML
    private TextField passwordField;


    public void userLogin(ActionEvent event) throws IOException{
        passwordValidity();
    }

    public void passwordValidity() throws IOException{

    }
}
