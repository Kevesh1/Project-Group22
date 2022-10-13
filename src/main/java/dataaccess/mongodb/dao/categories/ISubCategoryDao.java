package dataaccess.mongodb.dao.categories;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ISubCategoryDao {
    public Optional<CategorySubItem> getSubCategoryById(ObjectId id);

    List<CategorySubItem> getAllSubCategoriesByCategory(ObjectId id, Category category);

    public List<CategorySubItem> getAllSubCategories();

    public void updateSubCategory(CategorySubItem subCategory);

    public void deleteSubCategory(CategorySubItem subCategory);
}
