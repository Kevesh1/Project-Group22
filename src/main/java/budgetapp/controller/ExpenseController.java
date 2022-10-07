package budgetapp.controller;

import budgetapp.controller.categories.CategoryController;
import budgetapp.model.Expense;
import budgetapp.model.categories.CategorySubItem;
import com.sun.tools.javac.Main;
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
        latestPurchaseDate.setText(Integer.toString(expense.getDate()));
        latestPurchaseCost.setText(String.valueOf(expense.getCost()));
    }

    @FXML
    private void deleteExpense(){
        parentController.selectedBudgetMonth.removeExpense(expense);
        parentController.updateLatestPurchases();

    }
}
