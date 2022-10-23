package budgetapp.controller;

import budgetapp.controller.login.AccountLoginController;
import budgetapp.controller.login.RegistrationController;
import budgetapp.model.account.Account;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Optional;

public class WindowController {

    private final AccountLoginController accountLoginController;

    private final RegistrationController registrationController;

    private final Scene accountScene;

    private final Scene registrationScene;

    private Stage stage;

    public WindowController() {
        stage = new Stage();
        accountLoginController = new AccountLoginController(this);
        registrationController = new RegistrationController(this);
        accountScene = new Scene(accountLoginController);
        registrationScene = new Scene(registrationController);
    }

    public Optional<Account> showLoginStage() {
        stage.setScene(accountScene);
        if (!stage.isShowing()) {
            stage.showAndWait();
        }
        return accountLoginController.getAccount();
    }

    public void showRegistrationScene() {
        stage.setScene(registrationScene);
        stage.show();
    }


}
