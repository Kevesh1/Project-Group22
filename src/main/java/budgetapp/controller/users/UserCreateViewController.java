package budgetapp.controller.users;

import budgetapp.controller.login.FrontPageController;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserCreateViewController extends AnchorPane {

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

    public final Account account;

    public User user;

    public UserCreateViewController(Account account){
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
            createNewUser();
            createFrontPage();
        }

    }

    private void createNewUser() {
        User user = new User(firstNameInput.getText(), lastNameInput.getText(), createPassword.getText());
        user.setProfilePicture(chooseProfilePictureButton.toString());
    }

    @FXML
    public void returnToFrontPage(Event event) throws IOException{
        createFrontPage();
    }

    private void createFrontPage() {
        FrontPageController frontPageController = new FrontPageController(account);
        this.getScene().setRoot(frontPageController);
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
