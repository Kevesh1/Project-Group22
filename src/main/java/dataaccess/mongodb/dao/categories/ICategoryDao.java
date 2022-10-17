package dataaccess.mongodb.dao.categories;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.CategoryItem;

import java.util.List;

public interface ICategoryDao {
    List<CategoryItem> getAllCategoriesByBudgetMonth(String budgetMonthId);

    CategoryItem addCategory(CategoryItem categoryItem, String budgetMonth);

    List<CategoryItem> addCategories(List<CategoryItem> categoryItem, String budgetMonth);

    void setCategoryItems(List<CategoryItem> categoryItems, List<BudgetMonth> budgetMonths);
}
