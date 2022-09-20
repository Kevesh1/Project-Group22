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

import java.io.IOException;

public class CategoryController {

    private CategoryList categoryList;
    private CategoryItem category;
    MainController mc;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label categoryAmount;

    @FXML
    private Label categoryName;

    @FXML
    private Label categoryBudget;

    @FXML
    private Button editCategory;

    @FXML
    private Button removeCategoryButton;

    //private void updateProgressBar(){
    //    progressBar.setProgress(this.budget - this.spentAmount);}


    public CategoryController(MainController mc) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/budgetapp/fxml/category.fxml"));
        categoryList = new CategoryList();
        this.mc = mc;


    }

    @FXML
    public void removeCategory(){
        mc.getCategories().remove(this);
    }

    @FXML
    public void setLabels(CategoryItem item){
        categoryName.setText(item.getName());
        categoryBudget.setText(String.valueOf(item.getCategory()));
        categoryAmount.setText(String.valueOf(item.getBudget()));
        progressBar.setProgress((item.getBudget()-item.getBudgetSpent())/item.getBudget());
    }
}
