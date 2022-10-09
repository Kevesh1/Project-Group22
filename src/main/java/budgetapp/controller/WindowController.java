package budgetapp.controller;

import budgetapp.model.Account;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Optional;

public class WindowController {

    private final AccountLoginController accountLoginController;

    private Stage stage;

    public WindowController() {
        stage = new Stage();
        accountLoginController = new AccountLoginController();
    }

    public Optional<Account> ShowLoginStage() {
        stage.setScene(new Scene(accountLoginController));
        stage.showAndWait();
        return accountLoginController.getAccount();
    }


}
