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
    private MainController parentController;

    public ExpenseController(MainController parentController, Expense expense) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/Expense.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }

        this.parentController = parentController;
        this.expense = expense;
        setLabels();
    }

    private void setLabels(){
        latestPurchaseNotation.setText(expense.getAnnotation());
        latestPurchaseDate.setText(String.valueOf(expense.getDate()));
        latestPurchaseCost.setText(String.valueOf(expense.getSum()));
    }

    @FXML
    private void deleteTransaction(){
        expense.getSubCategory().decrementBudgetSpent((int) expense.getSum());
        parentController.selectedBudgetMonth.removeTransaction(expense);
        parentController.updateLatestTransaction();
        parentController.updateCategoryList();
    }
}
