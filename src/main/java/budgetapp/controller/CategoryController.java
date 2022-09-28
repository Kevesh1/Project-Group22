package budgetapp.controller;

import budgetapp.model.CategoryItem;
import budgetapp.model.CategorySubItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class CategoryController extends AnchorPane{


    @FXML
    ProgressBar progressBar;

    @FXML
    Label categoryName;

    @FXML
    Label categoryBudget;

    @FXML
    Button editCategory;

    @FXML
    Button removeCategoryButton;

    @FXML
    ImageView categoryImage;

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane CategoryPane;

    @FXML
    Button addSubCategoryButton;

    //private void updateProgressBar(){
    //    progressBar.setProgress(this.budget - this.spentAmount);}

    private final MainController parentController;
    private final CategoryItem categoryItem;
    public ArrayList<CategorySubItem> subCategories = new ArrayList<>();
    private int index;

    @FXML
    public void initialize() {

    };

    public CategoryController(MainController parentController, CategoryItem categoryItem, int i) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/category.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
        this.categoryItem = categoryItem;
        this.parentController = parentController;
        this.index = i;
        setLabels();
        subCategoriesMock();
    }

    public void setLabels() {
        categoryName.setText(categoryItem.getName());
        categoryBudget.setText(categoryItem.getBudget() + " kr");
        progressBar.setProgress(categoryItem.getBudgetSpent() / categoryItem.getBudget());
        categoryImage.setImage(categoryItem.getIcon());
    }

    private void subCategoriesMock(){
        CategorySubItem subCategory1 = new CategorySubItem(20,"Food");
        CategorySubItem subCategory2 = new CategorySubItem(20,"Food");
        CategorySubItem subCategory3 = new CategorySubItem(20,"Food");

        subCategories.add(subCategory1);
        subCategories.add(subCategory2);
        subCategories.add(subCategory3);
    }

    @FXML
    public void updateSubCategories(){
        parentController.updateCategoryList();
        for (CategorySubItem subCategory : subCategories) {
            SubCategoryController subCategoryController = new SubCategoryController(this, subCategory);
            parentController.categoriesFlowPane.getChildren().add(index,subCategoryController);

        }
    }

    @FXML
    private void getNewSubCategoryWindow(){
        parentController.addNewSubCategoryPane.toFront();
    }

    @FXML
    public void addSubCategory(){
        subCategories.add(new CategorySubItem(Double.parseDouble(
                parentController.newSubCategoryBudget.getText()), parentController.newSubCategoryName.getText()));
    }

    @FXML
    public void removeCategory(){
        parentController.selectedBudgetMonth.getCategories().remove(this.categoryItem);
        parentController.updateCategoryList();
    }

}
