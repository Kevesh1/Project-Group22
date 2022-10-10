package budgetapp.controller;

import budgetapp.App;
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


    public void userLogin(ActionEvent event, User user) throws IOException{
        passwordValidity();
        createMainView(user);
    }

    public void passwordValidity() throws IOException{
        App app = new App();
        if(passwordField.getText().toString().equals("OMG")){     /*later connect with user class*/
           app.changeScene("MainView.fxml");
        }

    }

    public void createMainView(User user) {
        MainController mainController = new MainController(user);
        this.getScene().setRoot(mainController);
    }


    public void returnToFrontPage(ActionEvent event) throws IOException{
        App app = new App();
        app.changeScene("FrontPage.fxml");

    }


}
