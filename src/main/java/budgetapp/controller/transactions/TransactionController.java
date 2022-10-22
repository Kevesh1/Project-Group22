package budgetapp.controller.transactions;

import budgetapp.controller.MainController;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;
import dataaccess.mongodb.dao.transactions.ExpenseDao;
import dataaccess.mongodb.dao.transactions.IncomeDao;

import java.sql.Date;
import java.time.LocalDate;

public class TransactionController
{
    private MainController mainController;

    private ExpenseDao expenseDao;

    private IncomeDao incomeDao;

    public TransactionController(MainController mainController) {
        this.mainController = mainController;
        expenseDao = new ExpenseDao();
        incomeDao = new IncomeDao();
    }

    public void addExpense() {
        try {
            Double cost = Double.valueOf(mainController.newExpenseAmount.getText());
            String note = mainController.newExpenseNote.getText();
            LocalDate tempDate = mainController.newExpenseDate.getValue();
            Date date = Date.valueOf(tempDate);
            Category category = Category.valueOf(mainController.newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getName());


            CategorySubItem subCategory = mainController.newExpenseSubCategoryComboBox.getSelectionModel().getSelectedItem();
            Expense expense = new Expense(cost, note, date, category, subCategory);

            mainController.selectedBudgetMonth.addTransaction(expenseDao.addExpense(expense, mainController.selectedBudgetMonth.getId()));
            subCategory.addExpense(expense);


            CategorySubItem subItem = mainController.newExpenseSubCategoryComboBox.getSelectionModel().getSelectedItem();
            subItem.incrementBudgetSpent(Integer.parseInt(mainController.newExpenseAmount.getText()));

        }catch (NullPointerException | IllegalArgumentException exception){
            //System.out.println("Not a valid number or/and chose a subcategory");
            System.out.println(exception.toString());
        }


        mainController.updateMainView();
        mainController.showMainView();
    }

    public void updateTransactions(){
        mainController.latestPurchases.getChildren().clear();
        if (mainController.selectedBudgetMonth.getTransactions() != null) {
            for (Transaction transaction : mainController.selectedBudgetMonth.getTransactions()){
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
        mainController.updateMainView();
    }

    void deleteExpense(Expense expense) {
        mainController.selectedBudgetMonth.removeTransaction(expenseDao.deleteExpense(expense));
        mainController.updateMainView();
    }

    void deleteIncome(Income income) {
        mainController.selectedBudgetMonth.removeTransaction(incomeDao.deleteIncome(income));
        mainController.updateMainView();
    }

    public void addIncome() {
        try {
            Double cost = Double.valueOf(mainController.newIncomeAmount.getText());
            String note = mainController.newIncomeNote.getText();
            Date date = Date.valueOf(mainController.newExpenseDate.getValue());
            Income income = new Income(cost, note, date);
            addTransaction(incomeDao.addIncome(income, mainController.selectedBudgetMonth.getId()));
        }
        catch (NumberFormatException exception){
            System.out.println("Not a valid number!");
        }
        mainController.updateMainView();
        mainController.showMainView();
    }
}
