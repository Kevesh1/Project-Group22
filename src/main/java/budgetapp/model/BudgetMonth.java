package budgetapp.model;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;

import javax.swing.*;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class BudgetMonth {
    private double budget;
    private double budgetSpent;
    private final YearMonth yearMonth;
    private final ArrayList<CategoryItem> categoryItems;
    private final List<Expense> expenses;
    private final List<Income> incomes;
    private final List<Transaction> transactions;

    public BudgetMonth(int year, Month month) {
        this.budget = 0;
        this.budgetSpent = 0;
        this.yearMonth = YearMonth.of(year, month);
        categoryItems = new ArrayList<CategoryItem>();
        expenses = new ArrayList<>();
        incomes = new ArrayList<>();
        transactions = new ArrayList<>();


    }

    public int getYear() {
        return yearMonth.getYear();
    }
    public Month getMonth() {
        return yearMonth.getMonth();
    }

    public double getBudgetRemaining(){
        return (getBudget() - getBudgetSpent());
    }

    public double getBudget() {
        budget = 0;
        for (Transaction transaction : transactions){
            if (transaction instanceof Income)
                budget += transaction.getSum();
        }
        return budget;
    }
    public void setBudget(double budget) {
        this.budget += budget;
    }
    public void addBudget(Income income){
        setBudget(income.getSum());
    }

    public double getBudgetSpent() {
        budgetSpent = 0;
        for (Transaction transaction : expenses){
            if (transaction instanceof Expense)
                budgetSpent += transaction.getSum();
        }
        return budgetSpent;
    }
    public void setBudgetSpent(double budgetSpent) {
        this.budgetSpent += budgetSpent;
    }
    public void addBudgetSpent(Expense expense){
        setBudgetSpent(expense.getSum());
    }

    public ArrayList<CategoryItem> getCategories() {
        return categoryItems;
    }

    public void addCategoryItem(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
    }


    public void removeTransaction(Transaction transaction){
        transactions.remove(transaction);
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public List<Expense> getExpenses(){
        return  getTransactions().stream().filter(expense -> expense instanceof Expense)
                .map(expense -> (Expense)expense)
                .collect(Collectors.toList());
    }

}
