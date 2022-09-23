package budgetapp.controller;

import budgetapp.model.AbstractCategoryItem;
import budgetapp.model.BudgetMonth;
import budgetapp.model.Category;
import budgetapp.model.CategoryItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

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
    Label budgetSpentLabel;
    @FXML
    ImageView profileImage;
    @FXML
    Label budgetRemainingLabel;
    @FXML
    FlowPane detailedViewFlowPane;

    //private ArrayList<CategoryItem> categoryList = new ArrayList<>();
    private CategoryController cc;
    private BudgetMonth selectedBudgetMonth;
    List<BudgetMonth> budgetMonths = new ArrayList<BudgetMonth>();

    @FXML
    private void onClickPreviousMonth() {
        yearMonthComboBox.getSelectionModel().selectPrevious();
    }

    @FXML
    private void onClickNextMonth() {
        yearMonthComboBox.getSelectionModel().selectNext();
    }

    @FXML
    private void onChangeBudgetMonthComboBox() {
        selectedBudgetMonth = yearMonthComboBox.getSelectionModel().getSelectedItem();
        updateMainView();
    }

    private void budgetMonthsMockUp() {
        BudgetMonth tempBudgetMonth1 = new BudgetMonth(5000, 2022, Month.AUGUST);
        CategoryItem tempCategoryItem1 = new CategoryItem(100, null, Category.Food);
        tempCategoryItem1.incrementBudgetSpent(50);
        tempBudgetMonth1.addCategoryItem(tempCategoryItem1);
        tempBudgetMonth1.addCategoryItem(new CategoryItem(200, null, Category.Savings));
        budgetMonths.add(tempBudgetMonth1);
        budgetMonths.add(new BudgetMonth(4000, 2022, Month.SEPTEMBER));
        budgetMonths.add(new BudgetMonth(7000, 2022, Month.OCTOBER));
    }

    public void initialize() throws IOException {
        budgetMonthsMockUp();
        initializeBudgetMonths();
        updateMainView();
    }
    public void updateMainView() {
        budgetLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()));
        budgetSpentLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetSpent()));
        budgetRemainingLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()-selectedBudgetMonth.getBudgetSpent()));
        updateCategoryList();
        updatePieChart(selectedBudgetMonth.getCategories());
    }

    public void updatePieChart(ArrayList<CategoryItem> categories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(AbstractCategoryItem category : categories) {
            System.out.println(category.getName());
            data.add(new PieChart.Data(category.getName(), category.getBudget()));
        }
        pieChart.setData(FXCollections.observableArrayList(data));
    }

    public void initializeBudgetMonths() {
        yearMonthComboBox.setItems(FXCollections.observableArrayList(budgetMonths));
        yearMonthComboBox.getSelectionModel().selectFirst();
        selectedBudgetMonth = yearMonthComboBox.getSelectionModel().getSelectedItem();
    }

    /*private void initiateCategories() throws IOException {
        for (Category category : Category.values()) {
            categoryList.add(new CategoryItem(5000, null, Category.valueOf(category.name())));
        }
    }*/

    public void updateCategoryList() {
        categoriesFlowPane.getChildren().clear();
        for (CategoryItem categoryItem : selectedBudgetMonth.getCategories()) {
            //CategoryItem categoryItem = new CategoryItem(category.getBudget(), category.getIcon(), category.getCategory());
            CategoryController categoryController = new CategoryController(this, categoryItem);
            categoriesFlowPane.getChildren().add(categoryController);
        }
    }
}
