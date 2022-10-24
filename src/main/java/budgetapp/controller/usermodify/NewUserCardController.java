package budgetapp.controller.usermodify;

import budgetapp.controller.login.FrontPageController;
import budgetapp.model.account.Account;
import javafx.event.Event;
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


    FrontPageController parentController;

    @FXML
    public Account account;

    @FXML
    public ImageView BlankProfilePicture;

    @FXML
    public Button EmptyUserButton;

    @FXML
    Label newUserLabel;

    @FXML
    public void createUserSelect(Event event) {
        parentController.addNewUser();
    }


    public NewUserCardController(FrontPageController parentController) {
        this.parentController = parentController;
        loadCurrentView();
    }

    private void loadCurrentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/userView/addUserCard.fxml"));
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
    public void initilize(){
        setCardData();
    }

    public void setCardData(){
        Image profilePicture = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/budgetapp/img/Squared_plus.svg.png")));
        BlankProfilePicture.setImage(profilePicture);
        newUserLabel.setText("New user");
    }


    @FXML
    public void selectUserAction(Event event) {
        parentController.createUserCreateViewController(account);

    }


}

