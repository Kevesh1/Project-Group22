package budgetapp.controller;

import budgetapp.model.CategorySubItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;


public class SubCategoryController extends AnchorPane {

    private CategoryController parentController;
    private CategorySubItem subCategory;

    @FXML
    private Label subCateogryName;

    @FXML
    private ProgressBar subCategoryProgressBar;

    @FXML
    private Label subCategoryBudget;



    public SubCategoryController(CategoryController parentController, CategorySubItem subCategory) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/subCategory.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }

        this.parentController = parentController;
        this.subCategory = subCategory;
        setLabels();
    }

    private void setLabels(){
        subCateogryName.setText(subCategory.getName());
        subCategoryBudget.setText(subCategory.getBudget() + " kr");
        subCategoryProgressBar.setProgress(subCategory.getBudgetSpent()/subCategory.getBudget());
    }

    @FXML
    private void removeSubCategory(){
        parentController.categoryItem.removeSubcategory(this.subCategory);
        parentController.updateSubCategories();
    }
}
