package budgetapp.controller;

import budgetapp.model.Category;
import budgetapp.model.CategoryItem;
import budgetapp.model.CategoryList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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

    //private void updateProgressBar(){
    //    progressBar.setProgress(this.budget - this.spentAmount);}

    private final MainController parentController;
    private final CategoryItem categoryitem;

    @FXML
    public void initialize() {

    };

    public CategoryController(MainController parentController, CategoryItem categoryItem) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/categoryItem.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
        this.categoryitem = categoryItem;
        this.parentController = parentController;
        setLabels();
    }

    public void setLabels(){
        categoryName.setText(categoryitem.getName());
        categoryBudget.setText(String.valueOf(categoryitem.getCategory()));
        progressBar.setProgress(categoryitem.getBudgetSpent()/categoryitem.getBudget());
    }

    /*@FXML
    public void removeCategory() throws IOException {
        mc.getCategories().remove(this.categoryItem);
        mc.updateCategoryList();
    }*/


}
