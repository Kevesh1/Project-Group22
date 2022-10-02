package budgetapp.controller;

import DAO.Dao;
import DAO.MongoDB.UserDao;
import budgetapp.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.Month;
import java.util.*;


public class MainController {

    @FXML
    Button confirmButton;
    @FXML
    AnchorPane mainView;
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
    AnchorPane confirmDeletePane;

    @FXML
    ComboBox categoryComboBox;
    @FXML
    TextField newCategoryBudget;
    @FXML
    Button addNewCategoryButton;
    @FXML
    AnchorPane addNewCategoryPane;
    @FXML
    Button addCategoryButton;
    @FXML
    Label newCategoryNameLabel;
    @FXML
    Label newCategoryBudgetLabel;

    @FXML
    AnchorPane addNewSubCategoryPane;
    @FXML
    TextField newSubCategoryName;
    @FXML
    TextField newSubCategoryBudget;
    @FXML
    Button justabutton;
    @FXML
    private void OpenIEWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/budgetapp/fxml/expenseAndIncomeWindow.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void closeNewCategoryWindow(){
        mainView.toFront();
    }


    @FXML
    private void onClickPreviousMonth() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            System.out.println(user.getFirstName() + " " + user.getPassword());
        }
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

    @FXML
    private void showAddCategoryWindow(){
        addNewCategoryPane.toFront();
    }

    @FXML
    private void showMainView(){
        mainView.toFront();
    }

    @FXML
    private void addNewCategory(){
        /*if (newCategoryBudget == null) {
            newCategoryBudget.setText("0");
        }*/
        Category category = Category.valueOf(categoryComboBox.getSelectionModel().getSelectedItem().toString());
        CategoryItem categoryItem = new CategoryItem(Double.parseDouble(newCategoryBudget.getText()), category);
        selectedBudgetMonth.addCategoryItem(categoryItem);
        updateCategoryList();
        showMainView();
        newCategoryBudget.setText("");
        categoryComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void showAddSubCategoryWindow(CategoryController categoryController){
        addNewSubCategoryPane.toFront();
        this.categoryController = categoryController;
    }

    @FXML
    private void addNewSubCategory(){
        String name = newSubCategoryName.getText();
        double budget = Double.parseDouble(newSubCategoryBudget.getText());
        CategorySubItem categorySubItem = new CategorySubItem(budget,name);
        categoryController.categoryItem.addSubCategory(categorySubItem);
        categoryController.categoryItem.addSubcategoryBudget();
        //categoryController.subCategories.add(categorySubItem);
        categoryController.updateSubCategories();
        //System.out.println(categoryController.subCategories);
        showMainView();
        newSubCategoryBudget.setText("");
        newSubCategoryName.setText("");
    }

    @FXML
    public void confirmRemoveCategoryWindow(CategoryController categoryController){
        confirmDeletePane.toFront();
        this.categoryController = categoryController;
    }

    @FXML
    public void removeCategory(){
        categoryController.confirmRemoveCategory();
        showMainView();
    }

    private CategoryController categoryController;
    public BudgetMonth selectedBudgetMonth;
    ObservableList<BudgetMonth> budgetMonths =  FXCollections.observableArrayList();
    private static Dao<User> userDao;

    public MainController() {
    }

    public void initialize() {
        userDao = new UserDao();
        budgetMonthsMockUp();
        initializeComboBox();
        initializeBudgetMonths();
        updateBarChart(budgetMonths);
        updateMainView();
        initializeCategoryComboBox();
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
        stackedBarChart.setTitle("Yearly budget");
        Map<Category ,XYChart.Series<String, Number>> series = new HashMap<>();
        for(BudgetMonth budgetMonth : budgetMonths) {
            for (CategoryItem categoryItem : budgetMonth.getCategories()) {
                series.computeIfAbsent(categoryItem.getCategory(),
                        c -> new XYChart.Series<String, Number>()).setName(categoryItem.getName().toUpperCase());
                series.get(categoryItem.getCategory()).getData().add(new XYChart.Data<String, Number>(budgetMonth.getMonth().toString(), categoryItem.getBudget()));
            }
        }
        List<XYChart.Series<String, Number>> temp = new ArrayList<>() ;
        series.forEach((category, stringNumberSeries) -> temp.add(series.get(category)));
        stackedBarChart.setData(FXCollections.observableArrayList(temp));
    }

    private void initializeComboBox() {
        yearMonthComboBox.setCellFactory(comboBoxCellFactory);
        yearMonthComboBox.setConverter(comboBoxStringConverter);
        yearMonthComboBox.setItems(FXCollections.observableArrayList(budgetMonths));
    }

    private void initializeCategoryComboBox(){
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(Arrays.asList(Category.values()));
        categoryComboBox.setItems(categories);
        categoryComboBox.getSelectionModel().selectFirst();
    }



    public void initializeBudgetMonths() {
        yearMonthComboBox.getSelectionModel().selectFirst();
        selectedBudgetMonth = yearMonthComboBox.getSelectionModel().getSelectedItem();
    }

    public void updateCategoryList() {
        categoriesFlowPane.getChildren().clear();
        int i = 0;
        for (CategoryItem categoryItem : selectedBudgetMonth.getCategories()) {
            i++;
            CategoryController categoryController = new CategoryController(this, categoryItem,i);
            categoriesFlowPane.getChildren().add(categoryController);

        }
    }

    private void budgetMonthsMockUp() {
        BudgetMonth tempBudgetMonth1 = new BudgetMonth(5000, 2022, Month.AUGUST);
        BudgetMonth tempBudgetMonth2 = (new BudgetMonth(4000, 2022, Month.SEPTEMBER));
        BudgetMonth tempBudgetMonth3 = (new BudgetMonth(7000, 2022, Month.OCTOBER));
        CategoryItem tempCategoryItem1 = new CategoryItem(100, Category.Food);
        CategoryItem tempCategoryItem2 = new CategoryItem(300, Category.Transportation);
        tempCategoryItem1.addSubCategory(new CategorySubItem(40, "AHHH"));
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
