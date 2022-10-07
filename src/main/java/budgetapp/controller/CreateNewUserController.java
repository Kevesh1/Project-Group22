package budgetapp.controller;

import budgetapp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.List;

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

    public void chooseProfilePicture(ActionEvent event){
        
    }

    @FXML
    public void profileFinishedAction(ActionEvent event) {
        if (controlAllInputs() && samePassword()){
            User user = new User(firstNameInput.getText(), lastNameInput.getText(), createPassword.getText());
            user.setProfilePicture(chooseProfilePictureButton.toString());
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

    public void profilePictureToString(){
    }

    public boolean samePassword(){
        return createPassword.getText().toString().equals(createPasswordRepeat.getText().toString());
    }
}
