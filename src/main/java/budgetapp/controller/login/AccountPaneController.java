package budgetapp.controller.login;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AccountPaneController extends BorderPane {
    @FXML
    public AnchorPane mainPane;
    @FXML
    public StackPane maiPane;
    RegistrationController registrationController;
    FrontPageController frontPageController;

    public AccountPaneController() {
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


        registrationController = new RegistrationController();

    }

    @FXML
    public void addView(Event event) {
        showRegistration();
    }

    @FXML
    public void initialize() {
        System.out.println("INITIALIZE");
        System.out.println(this.getScene().getWindow().toString());
        //mainPane.getChildren().add(new AccountPaneController());
    }
    @FXML
    public void init() {
        System.out.println("INIT");
    }

    void showRegistration() {
        mainPane.getScene().setRoot(registrationController);
    }
    void showFrontPage() {
        mainPane.getScene().setRoot(frontPageController);
    }


}
