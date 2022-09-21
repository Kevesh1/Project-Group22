package budgetapp.controller;

import budgetapp.model.BudgetMonth;
import budgetapp.model.Category;
import budgetapp.model.CategoryItem;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;

public class MainController {

    @FXML
    FlowPane categoriesFlowPane;
    @FXML
    PieChart pieChart;
    @FXML
    Button previousMonthButton;
    @FXML
    Button nextMonthButton;
    @FXML
    ComboBox<BudgetMonth> yearMonthComboBox;
    @FXML
    StackedBarChart stackedBarChart;
    @FXML
    Label budgetLabel;
    @FXML
    Label spentBudgetLabel;
    @FXML
    ImageView profileImage;
    @FXML
    Label remainingBudgetLabel;
    @FXML
    FlowPane detailedViewFlowPane;

    private boolean started = false;

    private ArrayList<CategoryItem> categoryList = new ArrayList<>();
    private CategoryController cc;

    public MainController() throws IOException {
        //cc = new CategoryController(this);
    }


    //does not work!
    public void initialize() throws IOException {
        try {
            initiateCategories();
        }
        catch (Exception ignored){
        }
        try {
            updateCategoryList();
        }
        catch (Exception ignored){
        }

    }

    private void initiateCategories() throws IOException {
        for (Category category : Category.values()) {

            //int i = 0;
            //i++;
            //cc.setLabels(categoryItem);
            //categoriesFlowPane.getChildren().add(categoryItem);
            categoryList.add(new CategoryItem(5000, null, category.valueOf(category.name())));
        }
    }

    public void updateCategoryList() throws IOException {
        categoriesFlowPane.getChildren().clear();
        for (CategoryItem category : categoryList) {
            CategoryItem categoryItem = new CategoryItem(category.getBudget(), category.getIcon(), category.getCategory());
            CategoryController categoryController = new CategoryController(this, categoryItem);
            categoriesFlowPane.getChildren().add(categoryController);
        }
    }

    public ArrayList<CategoryItem> getCategories(){
        return categoryList;
    }

}
