package budgetapp.model;

import budgetapp.model.categories.CategoryItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BudgetMonth {

    private String id;

    private double budget;

    private double budgetSpent;

    private Month month;

    private int year;

    private List<CategoryItem> categoryItems;

    private List<Expense> expenses;

    private List<Income> incomes;

    private List<Transaction> transactions;

    public BudgetMonth(int year, Month month) {
        this.budget = 0;
        this.budgetSpent = 0;
        this.year = year;
        this.month = month;
        initLists();
    }

    public BudgetMonth() {
    }

    private void initLists() {
        categoryItems = new ArrayList<>();
        expenses = new ArrayList<>();
        incomes = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "BudgetMonth{" +
                "budget=" + budget +
                ", budgetSpent=" + budgetSpent +
                ", categoryItems=" + categoryItems +
                ", expenses=" + expenses +
                ", incomes=" + incomes +
                ", transactions=" + transactions +
                '}';
    }

    public double getBudgetRemaining(){
        return (budget - getBudgetSpent());
    }

    public double getBudget() {
        return this.budget;
    }

    /*public double getBudget() {
        budget = 0;
        for (Transaction transaction : transactions){
            if (transaction instanceof Income)
                budget += transaction.getSum();
        }
        return budget;
    }*/


    public void setBudget(double budget) {
        this.budget += budget;
    }

    public void addBudget(Income income){
        setBudget(income.getSum());
    }

    /*public double getBudgetSpent() {
        budgetSpent = 0;
        for (Transaction transaction : transactions){
            if (transaction instanceof Expense)
                budgetSpent += transaction.getSum();
        }
        return budgetSpent;
    }*/

    public double getBudgetSpent() {
        return this.budgetSpent;
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


    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    public List<CategoryItem> setCategoryItems(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
        return categoryItems;
    }

//    public void setMonth(Month month) {
//        this.month = month;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Month getMonth() {
        return month;
    }

    public BudgetMonth setMonth(Month month) {
        this.month = month;
        return this;
    }

    public int getYear() {
        return year;
    }

    public BudgetMonth setYear(int year) {
        this.year = year;
        return this;
    }
}
