package budgetapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    private Button profileFinishedButton;
}
