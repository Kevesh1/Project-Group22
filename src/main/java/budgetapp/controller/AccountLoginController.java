package budgetapp.controller;

import DAO.MongoDB.AccountDao;
import budgetapp.DependencyInjection;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AccountLoginController extends BorderPane {

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongPassword;

    private final AccountDao accountDao;

    public AccountLoginController() {
        accountDao = new AccountDao();
        //sceneManager.switchScene("/budgetapp/fxml/AccountLoginView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/AccountLoginView.fxml"));
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
    public void toRegistration() {
        Scene scene = this.getScene();
        scene.setRoot(new RegistrationController());
    }

    @FXML
    public void loginEvent(Event event) throws IOException {
        if(accountDao.validateAccount(username.getText(), password.getText()).isPresent()) {
            Parent root = DependencyInjection.load("/budgetapp/fxml/FrontPage.fxml");
            this.getScene().setRoot(root);
        }
        event.consume();
    }

    /*@FXML
    public void accountLogin(ActionEvent event) throws IOException {
        //accountValidity();
    }*/

    /*@FXML
    private void OpenIEWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FrontPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    /*public void accountValidity() throws IOException {
        App app = new App();
        if(AccountName.getText().equals("javatest") && AccountPassword.getText().toString().equals("Fun1")){
            app.changeScene("FrontPage.fxml");
        }
        else if(AccountName.getText().isEmpty()){
            wrongPassword.setText("Please enter your name");
        }
        else if(AccountPassword.getText().isEmpty()){
            wrongPassword.setText("Please enter your password");
        }
        else{
            wrongPassword.setText("Wrong username or password");
        }
    }*/

}


