package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class NewUserCardController extends VBox {


    @FXML
    public ImageView BlankProfilePicture;

    @FXML
    public Button EmptyUserButton;

    @FXML
    Label newUserLabel;

    private final FrontPageController parentController;


    public NewUserCardController(FrontPageController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/addUserCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        setCardData();
    }

    public void setCardData(){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/budgetapp/img/Squared_plus.svg.png")));
        BlankProfilePicture.setImage(profilePicture);
        newUserLabel.setText("New user");
    }


}
