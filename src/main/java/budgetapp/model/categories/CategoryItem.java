package budgetapp.model.categories;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CategoryItem extends AbstractCategoryItem{
    private List<SubCategoryItem> subCategories;
    private Category category;


    public CategoryItem(double budget, @NotNull Category category) {
        super(category.toString(), budget);
        this.subCategories = new ArrayList<SubCategoryItem>();
        this.category = category;
    }

    @Override
    public Image getIcon() {
        return (new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "budgetapp/img/categories/" + getCategory().toString().toLowerCase() + ".png"))));
    }

    public List<SubCategoryItem> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(SubCategoryItem subCategory) {
        subCategories.add(subCategory);
    }
    public void removeSubcategory(SubCategoryItem subcategory) {
        subCategories.remove(subcategory);
    }

    public void addSubcategoryBudget(){
        incrementBudget((int) subCategories.get(subCategories.size() - 1).getBudget());
    }

    public void removeSubcategoryBudget(SubCategoryItem subCategory){
        decrementBudget((int)subCategory.getBudget());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
