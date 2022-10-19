package dataaccess.mongodb.dao.categories;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.CategoryItem;

import java.util.List;

public interface ICategoryDao {
    List<CategoryItem> getAllCategoriesByBudgetMonth(String budgetMonthId);

    CategoryItem addCategory(CategoryItem categoryItem, String budgetMonth);

    List<CategoryItem> addCategories(List<CategoryItem> categoryItem, String budgetMonth);

    List<CategoryItem> addCategoryItems(List<CategoryItem> categoryItems, String budgetMonths);

    List<CategoryItem> setCategoryItems(List<CategoryItem> categoryItems, String budgetMonths);

    List<CategoryItem> initCategoryItems(List<CategoryItem> categoryItems, String budgetMonth);
}
