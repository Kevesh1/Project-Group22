package budgetapp.controller;

import budgetapp.model.transactions.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class TransactionController extends AnchorPane {

    @FXML
    Label latestPurchaseNotation;
    @FXML
    Label latestPurchaseDate;
    @FXML
    Label latestPurchaseCost;

    private Transaction transaction;
    private MainController parentController;

    public TransactionController(MainController parentController, Transaction transaction) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/Expense.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }

        this.parentController = parentController;
        this.transaction = transaction;

        setLabels();

    }
    private void setLabels(){
        latestPurchaseNotation.setText(transaction.getAnnotation());
        latestPurchaseDate.setText(String.valueOf(transaction.getDate()));
        latestPurchaseCost.setText(String.valueOf(transaction.getSum()));
    }

    @FXML
    private void deleteExpense(){
        parentController.selectedBudgetMonth.removeTransaction(transaction);
        parentController.updateLatestTransaction();

    }
}
