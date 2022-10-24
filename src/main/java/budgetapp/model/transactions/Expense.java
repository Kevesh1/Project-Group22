package budgetapp.model.transactions;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;

import java.sql.Date;
import java.time.YearMonth;

/**
 * @author Johannes
 * @author Keivan
 */
public class Expense extends Transaction {

    /**
     * Child class to Transaction
     */

    private Category category;

    private CategorySubItem subCategory;

    /**
     * Constructor for Expense, calls on super.
     * @param sum
     * @param annotation
     * @param date
     * @param category
     * @param subCategory
     */
    public Expense(Double sum, String annotation, Date date, Category category, CategorySubItem subCategory) {
        super(sum, annotation, date);
        this.category = category;
        this.subCategory = subCategory;
    }

    public Expense() {

    }

    /**
     * this method gets subcategories
     * @return subCategory
     */

    public CategorySubItem getSubCategory() {
        return subCategory;
    }

    /**
     * this method gets category
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * this method sets category
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
