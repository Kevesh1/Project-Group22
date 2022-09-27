package budgetapp.controller;

import budgetapp.model.AbstractCategoryItem;
import budgetapp.model.BudgetMonth;
import budgetapp.model.Category;
import budgetapp.model.CategoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    StackedBarChart<String, Number> stackedBarChart;
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

    @FXML
    TextField newCategoryName;
    @FXML
    TextField getNewCategoryBudget;
    @FXML
    Button addNewCategoryButton;
    @FXML
    AnchorPane addNewCategoryPane;


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

    private CategoryController cc;
    private BudgetMonth selectedBudgetMonth;
    ObservableList<BudgetMonth> budgetMonths =  FXCollections.observableArrayList();

    public MainController() {
    }

    public void initialize() {
        budgetMonthsMockUp();
        initializeComboBox();
        initializeBudgetMonths();
        updateBarChart(budgetMonths);
        updateMainView();
    }
    public void updateMainView() {
        budgetLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()));
        budgetSpentLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetSpent()));
        budgetRemainingLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()-selectedBudgetMonth.getBudgetSpent()));
        updateCategoryList();
        updatePieChart(selectedBudgetMonth.getCategories());
    }

    private void updatePieChart(ArrayList<CategoryItem> categories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(AbstractCategoryItem category : categories) {
            data.add(new PieChart.Data(category.getName(), category.getBudget()));
        }
        pieChart.setData(FXCollections.observableArrayList(data));
    }

    // TODO Refactor function
    private void updateBarChart(List<BudgetMonth> budgetMonths) {
        stackedBarChart.getYAxis().setLabel("Budget");
        stackedBarChart.setTitle("MONTH budget");
        Map<Category ,XYChart.Series<String, Number>> series = new HashMap<>();
        for(BudgetMonth budgetMonth : budgetMonths) {
            for (CategoryItem categoryItem : budgetMonth.getCategories()) {
                String temp1 = categoryItem.getName();
                series.computeIfAbsent(categoryItem.getCategory(),
                        c -> new XYChart.Series<String, Number>()).setName(categoryItem.getName().toUpperCase());
                series.get(categoryItem.getCategory()).getData().add(new XYChart.Data<String, Number>(budgetMonth.getMonth().toString(), categoryItem.getBudget()));
            }
        }
        series.forEach((category, stringNumberSeries) -> System.out.println(series.get(category)));
        List temp = new ArrayList<>() ;
        series.forEach((category, stringNumberSeries) -> temp.add(series.get(category)));
        stackedBarChart.setData(FXCollections.observableArrayList(temp));
    }

    private void initializeComboBox() {
        yearMonthComboBox.setCellFactory(comboBoxCellFactory);
        yearMonthComboBox.setConverter(comboBoxStringConverter);
        yearMonthComboBox.setItems(FXCollections.observableArrayList(budgetMonths));
    }

    public void initializeBudgetMonths() {
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
            CategoryController categoryController = new CategoryController(this, categoryItem);
            categoriesFlowPane.getChildren().add(categoryController);
        }
    }

    private void budgetMonthsMockUp() {
        BudgetMonth tempBudgetMonth1 = new BudgetMonth(5000, 2022, Month.AUGUST);
        BudgetMonth tempBudgetMonth2 = (new BudgetMonth(4000, 2022, Month.SEPTEMBER));
        BudgetMonth tempBudgetMonth3 = (new BudgetMonth(7000, 2022, Month.OCTOBER));
        CategoryItem tempCategoryItem1 = new CategoryItem(100, Category.Food);
        CategoryItem tempCategoryItem2 = new CategoryItem(300, Category.Transportation);
        tempCategoryItem1.incrementBudgetSpent(50);
        tempBudgetMonth1.addCategoryItem(new CategoryItem(200, Category.Savings));
        tempBudgetMonth1.addCategoryItem(tempCategoryItem1);
        tempBudgetMonth2.addCategoryItem(tempCategoryItem2);
        tempBudgetMonth3.addCategoryItem(new CategoryItem(300, Category.Transportation));
        tempBudgetMonth3.addCategoryItem(new CategoryItem(300, Category.Food));
        budgetMonths.add(tempBudgetMonth1);
        budgetMonths.add(tempBudgetMonth2);
        budgetMonths.add(tempBudgetMonth3);
    }

    private Callback<ListView<BudgetMonth>, ListCell<BudgetMonth>> comboBoxCellFactory = new Callback<ListView<BudgetMonth>, ListCell<BudgetMonth>>() {
        @Override
        public ListCell<BudgetMonth> call(ListView<BudgetMonth> budgetMonthListView) {
            return new ListCell<BudgetMonth>() {
                @Override
                protected void updateItem(BudgetMonth budgetMonth, boolean empty) {
                    super.updateItem(budgetMonth, empty);
                    if (budgetMonth == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(yearMonthComboBox.getConverter().toString(budgetMonth));
                    }
                }
            };
        }
    };

    private StringConverter<BudgetMonth> comboBoxStringConverter = new StringConverter<BudgetMonth>() {

        @Override
        public String toString(BudgetMonth budgetMonth) {
            return String.format("%s %d", budgetMonth.getMonth().toString(), budgetMonth.getYear());
        }

        @Override
        public BudgetMonth fromString(String string) {
            return null;
        }
    };
}
