package dataaccess.mongodb.dao.categories;

import budgetapp.model.categories.CategoryItem;

import java.util.List;

/**
 * @author Johannes
 */
public interface ICategoryDao {
    List<CategoryItem> getAllCategoriesByBudgetMonth(String budgetMonthId);

    CategoryItem addCategory(CategoryItem categoryItem, String budgetMonth);

    CategoryItem deleteCategory(CategoryItem categoryItem);

    CategoryItem updateCategory(CategoryItem categoryItem);

    List<CategoryItem> addCategories(List<CategoryItem> categoryItem, String budgetMonth);

    List<CategoryItem> addCategoryItems(List<CategoryItem> categoryItems, String budgetMonths);

    List<CategoryItem> setCategoryItems(List<CategoryItem> categoryItems, String budgetMonths);

    List<CategoryItem> initCategoryItems(List<CategoryItem> categoryItems, String budgetMonth);
}
