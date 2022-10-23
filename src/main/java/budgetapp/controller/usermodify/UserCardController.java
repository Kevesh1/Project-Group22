package budgetapp.controller.usermodify;

import budgetapp.controller.login.FrontPageController;
import budgetapp.model.account.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserCardController extends VBox {

    FrontPageController parentController;

    User user;

    @FXML
    public Label usernameLabel;

    @FXML
    public ImageView profilePictureCard;

    @FXML
    public Button userButtonCard;


    public UserCardController(FrontPageController parentController, User user) {
        this.user = user;
        this.parentController = parentController;
        loadCurrentView();


    }

    private void loadCurrentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/usermodify/userCard.fxml"));
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

    public void setCardData(){
        Image image = new Image("/budgetapp/img/profilepictures/" + user.getProfilePicture() + ".png");
        profilePictureCard.setImage(image);
        usernameLabel.setText(user.getUsername());
    }

    @FXML
    public void initialize(){
        setCardData();
    }


    @FXML
    public void selectUserAction() {
        parentController.loginToUser(user);

    }
}
