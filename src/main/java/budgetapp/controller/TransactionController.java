package budgetapp.controller;

import budgetapp.controller.categories.CategoryController;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import budgetapp.model.transactions.Expense;
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

    private Expense expense;
    private MainController parentController;

    public TransactionController(MainController parentController, Expense expense) {
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

    //TODO Fix so the corresponding subcategory removes the transaction cost from itself
    @FXML
    private void deleteTransaction(){
        expense.getSubCategory().decrementBudgetSpent((int) expense.getSum());
        parentController.selectedBudgetMonth.removeExpense(expense);
        parentController.updateLatestTransaction();
        parentController.updateCategoryList();
    }
}
