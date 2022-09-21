package budgetapp.model;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class BudgetMonth {
    private double budget;
    private double budgetSpent;
    private final YearMonth yearMonth;
    private List<CategoryItem> categoryItems;

    public BudgetMonth(double budget, int year, Month month) {
        this.budget = budget;
        this.budgetSpent = 0;
        this.yearMonth = YearMonth.of(year, month);
        categoryItems = new ArrayList<CategoryItem>();
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
        this.budget = budget;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }
    public void setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
    }

    public List<CategoryItem> getCategories() {
        return categoryItems;
    }

    public void addCategoryItem(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
    }
}
