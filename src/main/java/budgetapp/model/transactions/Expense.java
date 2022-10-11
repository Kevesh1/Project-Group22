package budgetapp.model.transactions;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;

import java.time.YearMonth;

public class Expense extends Transaction {

    private Category category;
    private String subCategory;

    public Expense(Double cost, String annotation, String date, Category category, String subCategory) {
        super(cost,annotation,date);
        this.category = category;
        this.subCategory = subCategory;
    }


    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSubCategory(){
        return subCategory;
    }

    public void setSubCategory(String subCategory){
        this.subCategory = subCategory;
    }



}
