package budgetapp.model.categories;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CategoryItem extends AbstractCategoryItem{

    private List<CategorySubItem> subCategories;

    private Category category;



    public CategoryItem(@NotNull Category category) {
        super(category.toString(), 0);
        this.subCategories = new ArrayList<CategorySubItem>();
        this.category = category;
    }

    @Override
    public Image getIcon() {
        return (new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "budgetapp/img/categories/" + getCategory().toString().toLowerCase() + ".png"))));
    }

    public List<CategorySubItem> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(CategorySubItem subCategory) {
        subCategories.add(subCategory);
    }
    public void removeSubcategory(CategorySubItem subCategory) {
        subCategories.remove(subCategory);

    }

    //Overload
    public void addSubcategoryBudget(){
        incrementBudget((int) subCategories.get(subCategories.size() - 1).getBudget());

    }
    //Overload
    public void addSubcategoryBudget(CategorySubItem subCategory){
        incrementBudget((int)subCategory.getBudget());

    }

    public void removeSubcategoryBudget(CategorySubItem subCategory){
        decrementBudget((int)subCategory.getBudget());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
