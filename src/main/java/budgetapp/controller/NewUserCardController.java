package budgetapp.controller;

import budgetapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class NewUserCardController extends VBox {


    @FXML
    private ImageView BlankProfilePicture;

    @FXML
    private Button EmptyUserButton;

    @FXML
    private Label NewUserLabel;


    public NewUserCardController(FrontPageController parentController) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/NewUserCard.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
        setCardData();
    }

    public void setCardData(){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/budgetapp/img/Squared_plus.svg.png")));
        BlankProfilePicture.setImage(profilePicture);
        NewUserLabel.setText("New user");
    }


}
