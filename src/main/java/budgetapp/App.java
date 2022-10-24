package budgetapp;

import dataaccess.mongodb.MongoDBService;
import budgetapp.controller.loginController.FrontPageController;
import budgetapp.controller.WindowController;
import budgetapp.model.account.Account;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Optional;

/**
 * @author Johannes
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        MongoDBService.createDataBase("budgetapp");

        WindowController windowController = new WindowController();
        Optional<Account> account = windowController.showLoginStage();
        if (account.isPresent()) {
            primaryStage.setScene(new Scene(new FrontPageController(account.get())));
            primaryStage.setMaximized(true);
            primaryStage.show();
        }
    }

    private void setUpDependencyInjector() {
        Callback<Class<?>, Object> controllerFactory = param -> {
            //Account account = new Account("a", "c", List.of(new User("Test1", "User", null), new User("Test2", "user", "pass")));
            //return new FrontPageController(account);
            return null;
        };
        DependencyInjection.addInjectionMethod(
                FrontPageController.class, controllerFactory
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}