package budgetapp.controller.transactions;

import budgetapp.controller.MainController;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class IncomeController extends AnchorPane {

    @FXML
    Label latestPurchaseNotation;
    @FXML
    Label latestPurchaseDate;
    @FXML
    Label latestPurchaseCost;

    private final Income income;
    private final MainController parentController;

    public IncomeController(MainController parentController, Income income) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/Income.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }

        this.parentController = parentController;
        this.income = income;
        setLabels();
    }

    private void setLabels(){
        latestPurchaseNotation.setText(income.getAnnotation());
        latestPurchaseDate.setText(String.valueOf(income.getDate()));
        latestPurchaseCost.setText(String.valueOf(income.getSum()));
    }

    @FXML
    private void deleteTransaction(){
        parentController.selectedBudgetMonth.removeTransaction(income);
        parentController.updateLatestTransaction();
        parentController.updateMainView();
    }
}
