package budgetapp.model;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;

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
    private final ArrayList<Transaction> transactions;

    public BudgetMonth(double budget, int year, Month month) {
        this.budget = budget;
        this.budgetSpent = 0;
        this.yearMonth = YearMonth.of(year, month);
        categoryItems = new ArrayList<CategoryItem>();
        transactions = new ArrayList<>();

    }

    public int getYear() {
        return yearMonth.getYear();
    }
    public Month getMonth() {
        return yearMonth.getMonth();
    }

    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget += budget;
    }
    public void addBudget(Income income){
        setBudget(income.getSum());
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }
    public void setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
    }

    public ArrayList<CategoryItem> getCategories() {
        return categoryItems;
    }

    public void addCategoryItem(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactions.remove(transaction);
    }

    public CategoryItem getCategory(Category category){
        for (CategoryItem categoryItem : getCategories()){
            if (Objects.equals(categoryItem.getCategory(),category)){
                return categoryItem;
            }
        }
        return null;
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
