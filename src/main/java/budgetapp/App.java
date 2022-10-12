package budgetapp;

import DAO.MongoDB.MongoDBService;
import budgetapp.controller.login.FrontPageController;
import budgetapp.controller.WindowController;
import budgetapp.model.account.Account;
import budgetapp.model.account.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class App extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        MongoDBService.createDataBase("budgetapp");
        //setUpDependencyInjector();
        //Parent root = DependencyInjection.load("/budgetapp/fxml/AccountLoginView.fxml");

        WindowController windowController = new WindowController();
        Optional<Account> account = windowController.ShowLoginStage();
        if(account.isPresent()) {
            System.out.printf("%s, %s", account.get().getUsername(), account.get().getPassword());;
            primaryStage.setScene(new Scene(new FrontPageController(account.get())));
            System.out.println("SCENE SET");
            primaryStage.setMaximized(true);//primaryStage.show();
            primaryStage.show();
        }
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