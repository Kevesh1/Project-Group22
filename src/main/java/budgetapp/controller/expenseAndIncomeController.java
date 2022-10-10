package budgetapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextArea;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class expenseAndIncomeController {

    @FXML
    private TextField sumWindow;
    @FXML
    private TextArea note;
    @FXML
    private DatePicker transactionDate;
    @FXML
    private Button categorySwap;
    @FXML
    private AnchorPane categorySelect;
    @FXML
    private AnchorPane SwapCategory;
    @FXML
    private ImageView closeButton;
    @FXML private VBox statusContainer;
    @FXML
    private Button close;



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


