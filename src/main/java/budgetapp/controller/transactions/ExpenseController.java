package budgetapp.controller.transactions;

import budgetapp.controller.MainController;
import budgetapp.model.transactions.Expense;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class ExpenseController extends AnchorPane {

    @FXML
    Label latestPurchaseNotation;
    @FXML
    Label latestPurchaseDate;
    @FXML
    Label latestPurchaseCost;

    private Expense expense;
    private TransactionController transactionController;

    public ExpenseController(TransactionController transactionController, Expense expense) {

        this.transactionController = transactionController;
        this.expense = expense;

        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/Expense.fxml"));
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
        latestPurchaseNotation.setText(expense.getAnnotation());
        latestPurchaseDate.setText(String.valueOf(expense.getDate()));
        latestPurchaseCost.setText(String.valueOf(expense.getSum()));
    }

    @FXML
    private void deleteTransaction(){
        transactionController.deleteTransaction(expense);
    }
}
