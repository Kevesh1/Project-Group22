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

    private ArrayList<CategoryItem> list = new ArrayList<>();
    private CategoryController cc;

    public MainController() throws IOException {
        //cc = new CategoryController(this);
    }

    @FXML
    public void initialize() throws IOException {
        initiateCategories();
    }

    private void initiateCategories() throws IOException {
        categoriesFlowPane.getChildren().clear();
        for(Category category : Category.values()){
            //int i = 0;
            //i++;
            //list.add(new CategoryItem(5000, null, category.valueOf(category.name())));
            //cc.setLabels(categoryItem);
            //categoriesFlowPane.getChildren().add(categoryItem);


            CategoryItem categoryItem = new CategoryItem(5000, null, category.valueOf(category.name()));
            CategoryController categoryController = new CategoryController(this, categoryItem);
            categoriesFlowPane.getChildren().add(categoryController);
        }
    }

    public ArrayList<CategoryItem> getCategories(){
        return null;
    }

}
