package budgetapp.controller.transactions;

import budgetapp.controller.MainController;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;

public class TransactionController
{
    private MainController mainController;
    public TransactionController(MainController mainController) {
        this.mainController = mainController;

    }

    public void updateTransactions(){
        mainController.latestPurchases.getChildren().clear();
        if (mainController.selectedBudgetMonth.getTransactions() != null) {
            for (Transaction transaction : mainController.selectedBudgetMonth.getTransactions()){
                System.out.println(transaction.getSum());
                if (transaction instanceof Expense){
                    ExpenseController expenseController = new ExpenseController(this, (Expense) transaction);
                    mainController.latestPurchases.getChildren().add(expenseController);
                } else if (transaction instanceof Income){
                    IncomeController incomeController = new IncomeController(this, (Income)transaction);
                    mainController.latestPurchases.getChildren().add(incomeController);
                }
            }
        }
    }

    public void addTransaction(Transaction transaction) {
        mainController.selectedBudgetMonth.addTransaction(transaction);
    }

    void deleteTransaction(Transaction transaction) {
        mainController.selectedBudgetMonth.removeTransaction(transaction);
        updateTransactions();
        mainController.updateMainView();
    }
}
