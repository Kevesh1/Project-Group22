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

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class App extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) {
        MongoDBService.createDataBase("budgetapp");
        setUpDependecyInjector();
        //Parent root = DependencyInjection.load("/budgetapp/")
        AccountLoginController accountLoginController = new AccountLoginController();

        primaryStage.setScene(new Scene(accountLoginController));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    private void setUpDependecyInjector() {
        //create factories - here we'll just create one!
        Callback<Class<?>, Object> controllerFactory = param -> {
            Account account = new Account("a", "c", null);
            return new FrontPageController(account);
        };
        //save the factory in the injector
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