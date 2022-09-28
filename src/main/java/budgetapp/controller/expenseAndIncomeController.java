package budgetapp.controller;

import com.sun.javafx.fxml.FXMLLoaderHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


import java.io.IOException;
import java.util.ResourceBundle;


public class expenseAndIncomeController {

    @FXML
    private TextField sumWindow;
    @FXML
    private TextArea note;
    @FXML
    private DatePicker transaktionDate;
    @FXML
    private Button button;
    @FXML
    private AnchorPane categorySelect;
    @FXML
    private AnchorPane SwapCategory;
    @FXML
    private ImageView closeButton;

    @FXML private VBox statusContainer;


    @FXML
    private void loadCategory(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("budgetapp/fxml/CategorySelection.fxml"));
        categorySelect.getChildren().setAll(pane);
    }

    private TranslateTransition showStatus;
    private TranslateTransition hideStatus;
    private boolean showsStatus = false;

    @FXML void initialize() {

        showStatus = new TranslateTransition(Duration.millis(250), statusContainer);
        showStatus.setByY(-100.0);
        showStatus.setOnFinished(event -> showsStatus = true);
        hideStatus = new TranslateTransition(Duration.millis(250), statusContainer);
        hideStatus.setByY(100.0);
        hideStatus.setOnFinished(event -> showsStatus = false);
    }

    public void toggleStatus() {
        if( showsStatus ) {
            showStatus.stop();
            hideStatus.play();
        }
        else {
            hideStatus.stop();
            showStatus.play();
        }
    }

}


