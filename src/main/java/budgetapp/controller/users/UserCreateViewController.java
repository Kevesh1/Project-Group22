package budgetapp.controller.users;

import budgetapp.controller.login.FrontPageController;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import budgetapp.model.account.UserManager;
import dataaccess.mongodb.dao.account.UserDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private Text warningText;

    @FXML
    private Text passwordComplex;

    public final Account account;

    public User user;

    private final UserDao userDao;

    public String profilePictureName;

    public UserManager userManager;

    public UserCreateViewController(Account account){
        this.account = account;
        userDao = new UserDao();
        userManager = new UserManager();
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

    @FXML
    public void profileFinishedAction(ActionEvent event) {
        if(passwordCheckbox.isSelected() && controlAllInputsWithoutPassword()){
            createNewUserWithoutPassword();
            createFrontPage();
        }
        else if (controlAllInputsWithPassword() && samePassword() && passwordComplexity()){
            createNewUser();
            createFrontPage();
        }
        else{
            warningText.setText("Please enter all required data");
        }

    }

    //should be part of model
    /*
    private void createNewUser() {
        User user = new User(firstNameInput.getText() + " " + lastNameInput.getText(), createPassword.getText(), profilePictureName);
        userDao.addUser(user, account);
    }


     */

    private void createNewUser(){
        user = userManager.createNewUser(firstNameInput.getText(), lastNameInput.getText(), createPassword.getText(), profilePictureName);
        userDao.addUser(user, account);
    }


    /*
    //should be part of model
    private void createNewUserWithoutPassword() {
        User user = new User(firstNameInput.getText() + " " + lastNameInput.getText(), null, profilePictureName);
        userDao.addUser(user, account);
    }
    */

    private void createNewUserWithoutPassword(){
        user = userManager.createNewUserWithoutPassword(firstNameInput.getText(), lastNameInput.getText(), profilePictureName);
        userDao.addUser(user, account);
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
        SelectProfilePictureController selectProfilePictureController = new SelectProfilePictureController(account);
        this.getScene().setRoot(selectProfilePictureController);
    }

    public boolean controlAllInputsWithPassword(){
        if (firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty() || createPassword.getText().isEmpty() || createPasswordRepeat.getText().isEmpty() || chooseProfilePictureButton.getImage().toString().equals("/budgetapp/img/profilepictures/BlankProfilePicture.png")){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean controlAllInputsWithoutPassword(){
        if (firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty() || chooseProfilePictureButton.getImage().toString().equals("/budgetapp/img/profilepictures/BlankProfilePicture.png")){
            return false;
        }
        else{
            return true;
        }
    }

    /*
    //should be part of model
    public boolean samePassword(){
        return createPassword.getText().equals(createPasswordRepeat.getText());
    }

    */

    public boolean samePassword(){
        return userManager.samePassword(createPassword.getText(), createPasswordRepeat.getText());
    }

    /*
    //should be part of model
    public boolean passwordComplexity(){
        if (passwordLength() && passwordCharacterTest()){
            return true;
        }
        else{
            passwordComplex.setText("minimum 8 characters with letters and numbers");
            return false;
        }
    }

     */

    public boolean passwordComplexity(){
        if(userManager.passwordComplexity(createPassword.getText())){
            return true;
        }
        else{
            passwordComplex.setText("minimum 8 characters with letters and numbers");
            return false;
        }
    }

    /*
    //should be part of model
    public boolean passwordLength(){
        return createPassword.getText().length() >= 8;
    }

     */


    /*
    //should be part of model
    public boolean passwordCharacterTest() {
        return Pattern.matches("[a-zA-Z]+", createPassword.toString()) && Pattern.matches("[0-9]+", createPassword.toString());

    }

     */


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


    public ImageView getChooseProfilePictureButton() {
        return chooseProfilePictureButton;
    }


    public void setChooseProfilePictureButton(String imageFileName) {
        profilePictureName = imageFileName;
        System.out.println(imageFileName);
        Image image = new Image("/budgetapp/img/profilepictures/" + imageFileName + ".png");
        chooseProfilePictureButton.setImage(image);
    }

}
