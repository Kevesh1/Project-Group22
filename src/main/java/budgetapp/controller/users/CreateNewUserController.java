package budgetapp.controller.users;

import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CreateNewUserController {

    @FXML
    private RadioButton adaptedElderlyButton;

    @FXML
    private ImageView chooseProfilePictureButton;

    @FXML
    private PasswordField createPassword;

    @FXML
    private PasswordField createPasswordRepeat;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    public Button profileFinishedButton;

    private final Account account;

    public CreateNewUserController(Account account){
        this.account = account;
        loadCurrentView();
    }

    private void loadCurrentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/userCreateView.fxml"));
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

    public void chooseProfilePicture(ActionEvent event){
    }

    @FXML
    public void profileFinishedAction(ActionEvent event) {
        if (controlAllInputs() && samePassword()){
            User user = new User(firstNameInput.getText(), lastNameInput.getText(), createPassword.getText());
            user.setProfilePicture(chooseProfilePictureButton.toString());
            returnToFrontPage();
        }

    }

    private void returnToFrontPage() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/FrontPage.fxml"));
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


    public boolean controlAllInputs(){
        if (firstNameInput.getText().toString().isEmpty() || lastNameInput.getText().toString().isEmpty() || createPassword.getText().toString().isEmpty() || createPasswordRepeat.getText().toString().isEmpty() || !chooseProfilePictureButton.getImage().toString().equals("/budgetapp/img/plus.png")){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean samePassword(){
        return createPassword.getText().toString().equals(createPasswordRepeat.getText().toString());
    }
}
