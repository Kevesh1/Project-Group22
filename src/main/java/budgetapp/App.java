package budgetapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setTitle("Hello World!");
        Parent root = FXMLLoader.load(getClass().getResource("/budgetapp/fxml/MainView.fxml"));
        Scene scene = new Scene(root, 300, 275);
        //Node node =  FXMLLoader.load(getClass().getResource("/budgetapp/fxml/test.fxml"));
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        
    }

    public static void main(String[] args){
        launch(args);
    }
}