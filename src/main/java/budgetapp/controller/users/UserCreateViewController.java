package budgetapp.controller.users;

import budgetapp.controller.login.FrontPageController;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserCreateViewController extends AnchorPane {

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

    @FXML
    private Text passwordText;

    @FXML
    private Text reenterText;

    @FXML
    private CheckBox passwordCheckbox;

    public final Account account;

    public User user;

    public UserCreateViewController(Account account){
        this.account = account;
        loadStackPane();
    }

    private void loadStackPane() {
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
        if(passwordCheckbox.isSelected() && controlAllInputsWithoutPassword()){
            createNewUserWithoutPassword();
            createFrontPage();
        }
        if (controlAllInputsWithPassword() && samePassword()){
            createNewUser();
            createFrontPage();
        }

    }

    private void createNewUser() {
        User user = new User(firstNameInput.getText(), lastNameInput.getText(), createPassword.getText());
        user.setProfilePicture(chooseProfilePictureButton.toString());
    }

    private void createNewUserWithoutPassword() {
        User user = new User(firstNameInput.getText(), lastNameInput.getText(), null);
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

    private void createSelectProfilePictureController() {
        SelectProfilePictureController selectProfilePictureController = new SelectProfilePictureController();
        this.getScene().setRoot(selectProfilePictureController);
    }

    public boolean controlAllInputsWithPassword(){
        if (firstNameInput.getText().toString().isEmpty() || lastNameInput.getText().toString().isEmpty() || createPassword.getText().toString().isEmpty() || createPasswordRepeat.getText().toString().isEmpty() || !chooseProfilePictureButton.getImage().toString().equals("/budgetapp/img/plus.png")){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean controlAllInputsWithoutPassword(){
        if (firstNameInput.getText().toString().isEmpty() || lastNameInput.getText().toString().isEmpty() || !chooseProfilePictureButton.getImage().toString().equals("/budgetapp/img/plus.png")){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean samePassword(){
        return createPassword.getText().toString().equals(createPasswordRepeat.getText().toString());
    }


    @FXML
    public void chooseProfilePicture(Event event){
        createSelectProfilePictureController();
    }

    @FXML
    void passwordCheckboxAction(ActionEvent event) {
        if(passwordCheckbox.isSelected()){
            passwordText.setVisible(false);
            reenterText.setVisible(false);
            createPassword.setVisible(false);
            createPasswordRepeat.setVisible(false);
        }
        else{
            passwordText.setVisible(true);
            reenterText.setVisible(true);
            createPassword.setVisible(true);
            createPasswordRepeat.setVisible(true);
        }
    }
}
