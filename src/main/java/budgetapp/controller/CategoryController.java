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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

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
    private ImageView categoryImage;

    //private void updateProgressBar(){
    //    progressBar.setProgress(this.budget - this.spentAmount);}

    private final MainController parentController;
    private final CategoryItem categoryItem;

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
        this.categoryItem = categoryItem;
        this.parentController = parentController;
        setLabels();
    }

    public void setLabels() {
        categoryName.setText(categoryItem.getName());
        categoryBudget.setText(String.valueOf(categoryItem.getCategory()));
        progressBar.setProgress(categoryItem.getBudgetSpent() / categoryItem.getBudget());
        //categoryImage.setImage(categoryItem.getIcon(categoryItem.getCategory()));

    }

    /*@FXML
    public void removeCategory() throws IOException {
        mc.getCategories().remove(this.categoryItem);
        mc.updateCategoryList();
    }*/


}
