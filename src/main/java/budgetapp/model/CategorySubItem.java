package budgetapp.model;

import javafx.scene.image.Image;

public class CategorySubItem extends AbstractCategoryItem{

    public CategorySubItem(double budget, Category category) {
        super(category.toString(), budget, category);
    }
}
