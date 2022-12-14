package budgetapp.controller.transactionsController;

import budgetapp.model.transactions.Income;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * @author Johannes
 * @author Keivan
 */
public class IncomeController extends AnchorPane {

    @FXML
    Label latestPurchaseNotation;
    @FXML
    Label latestPurchaseDate;
    @FXML
    Label latestPurchaseCost;

    private final Income income;
    private final TransactionController transactionController;

    public IncomeController(TransactionController transactionController, Income income) {
        this.transactionController = transactionController;
        this.income = income;
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/TransaktionsView/Income.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
    }

    @FXML
    public void initialize() {
        setLabels();
    }
    private void setLabels(){
        latestPurchaseNotation.setText(income.getAnnotation());
        latestPurchaseDate.setText(String.valueOf(income.getDate()));
        latestPurchaseCost.setText(String.valueOf(income.getSum()));
    }

    @FXML
    private void deleteTransaction(){
        transactionController.deleteIncome(income);

    }
}
