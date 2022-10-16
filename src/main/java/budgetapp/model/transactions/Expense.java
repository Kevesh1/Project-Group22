package budgetapp.model.transactions;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;

import java.time.YearMonth;

public class Expense extends Transaction {

    private Category category;
    private CategorySubItem subCategory;

    public Expense(Double sum, String annotation, String date, Category category, CategorySubItem subCategory) {
        super(sum, annotation, date);
        this.category = category;
        this.subCategory = subCategory;
    }

    public CategorySubItem getSubCategory() {
        return subCategory;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
