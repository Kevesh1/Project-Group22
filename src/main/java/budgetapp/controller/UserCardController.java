package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserCardController extends VBox {

    @FXML
    Label usernameLabel;

    FrontPageController parentController;

    @FXML
    User user;


    public UserCardController(FrontPageController parentController, User user) {
        this.user = user;
        //this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/userCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }

        usernameLabel.setText(user.getFirstName());
    }

    @FXML
    public void selectUser() {
        parentController.selectedUser = user;

    }
}
