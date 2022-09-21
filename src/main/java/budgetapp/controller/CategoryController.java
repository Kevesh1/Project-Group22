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

public class CategoryController extends AnchorPane {

    private CategoryList categoryList;
    private CategoryItem category;
    private MainController mc;
    private CategoryItem categoryItem;

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


    public CategoryController(MainController mc, CategoryItem categoryItem) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/budgetapp/fxml/category.fxml"));
        //categoryList = new CategoryList();
        this.mc = mc;
        categoryName.setText(categoryItem.getName());
        categoryBudget.setText(String.valueOf(categoryItem.getCategory()));
        categoryAmount.setText(String.valueOf(categoryItem.getBudget()));
        progressBar.setProgress((categoryItem.getBudget()-categoryItem.getBudgetSpent())/categoryItem.getBudget());
    }

    /*@FXML
    public void removeCategory() throws IOException {
        mc.getCategories().remove(this.categoryItem);
        mc.updateCategoryList();
    }*/

/*    @FXML
    public void setLabels(CategoryItem item){
        categoryName.setText(item.getName());
        categoryBudget.setText(String.valueOf(item.getCategory()));
        categoryAmount.setText(String.valueOf(item.getBudget()));
        progressBar.setProgress((item.getBudget()-item.getBudgetSpent())/item.getBudget());
    }*/
}
