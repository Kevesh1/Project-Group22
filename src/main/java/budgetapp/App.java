package budgetapp;

import DAO.MongoDB.MongoDBService;
import budgetapp.controller.AccountLoginController;
import budgetapp.controller.FrontPageController;
import budgetapp.controller.MainController;
import budgetapp.model.Account;
import budgetapp.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.modelmapper.internal.util.Callable;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class App extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        MongoDBService.createDataBase("budgetapp");
        setUpDependencyInjector();
        //Parent root = DependencyInjection.load("/budgetapp/fxml/AccountLoginView.fxml");
        AccountLoginController accountLoginController = new AccountLoginController();

        primaryStage.setScene(new Scene(accountLoginController));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    private void setUpDependencyInjector() {
        Callback<Class<?>, Object> controllerFactory = param -> {
            Account account = new Account("a", "c", List.of(new User("Test1", "User", null), new User("Test2", "user", "pass")));
            return new FrontPageController(account);
        };
        DependencyInjection.addInjectionMethod(
                FrontPageController.class, controllerFactory
        );
    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);

    }

    public static void main(String[] args) {
        launch(args);
    }
}