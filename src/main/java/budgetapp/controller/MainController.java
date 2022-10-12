package budgetapp.controller;

import budgetapp.controller.categories.CategoryController;
import budgetapp.controller.categories.SubCategoryController;
import budgetapp.model.BudgetMonth;
import budgetapp.model.account.User;
import budgetapp.model.categories.AbstractCategoryItem;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import budgetapp.model.transactions.Expense;
import dataaccess.mongodb.dao.account.AccountDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MainController extends AnchorPane{

    @FXML
    Button confirmButton;
    @FXML
    AnchorPane mainView;
    @FXML
    public FlowPane categoriesFlowPane;
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
    public FlowPane latestPurchases;
    @FXML
    AnchorPane confirmDeletePane;


    @FXML
    ComboBox categoryComboBox;
    @FXML
    ComboBox categoryComboBox2;
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
    Button updateCategoryButton;
    @FXML
    Button updateSubCategoryButton;

    @FXML
    AnchorPane addNewSubCategoryPane;
    @FXML
    TextField newSubCategoryName;
    @FXML
    TextField newSubCategoryBudget;
    @FXML
    Button justabutton;

    @FXML
    Button addNewSubCategoryButton;

    @FXML
    AnchorPane iEWindow;
    @FXML
    ComboBox<CategoryItem> newExpenseCategoryComboBox;
    @FXML
    TextField newExpenseAmount;
    @FXML
    DatePicker newExpenseDate;
    @FXML
    TextArea newExpenseNote;
    @FXML
    ComboBox newIncomeCategoryComboBox;
    @FXML
    TextField newIncomeAmount;
    @FXML
    DatePicker newIncomeDate;
    @FXML
    TextArea newIncomeNote;
    @FXML
    ComboBox<CategorySubItem> newExpenseSubCategoryComboBox;


    @FXML
    private void openIE(){
        iEWindow.toFront();
    }

    @FXML
    private void addExpense(){
        Double cost = Double.valueOf(newExpenseAmount.getText());
        System.out.println(cost);
        String note = newExpenseNote.getText();
        String date = newExpenseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println((newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().toString()));
        Category category = Category.valueOf(newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getName());


        CategorySubItem subCategory = newExpenseSubCategoryComboBox.getSelectionModel().getSelectedItem();
        Expense expense = new Expense(cost, note, date, category, subCategory);
        selectedBudgetMonth.addExpense(expense);
        subCategory.addExpense(expense);

        CategorySubItem subItem = newExpenseSubCategoryComboBox.getSelectionModel().getSelectedItem();
        subItem.incrementBudgetSpent(Integer.parseInt(newExpenseAmount.getText()));

        updateLatestTransaction();
        updateCategoryList();
        showMainView();
    }

    @FXML
    private void addIncome(){
        Double cost = Double.valueOf(newIncomeAmount.getText());
        String note = newIncomeNote.getText();
        String date = newIncomeDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //Category category = Category.valueOf(newIncomeCategoryComboBox.getSelectionModel().getSelectedItem().toString());
        //selectedBudgetMonth.addIncome(new Income(cost, note, date, Category.Income));
        updateLatestTransaction();
        showMainView();
    }

    @FXML
    public void closeNewCategoryWindow(){
        mainView.toFront();
        resetNewCategoryInputs();
    }
    AccountDao accountDao = new AccountDao();
    @FXML
    private void onClickPreviousMonth() {
        yearMonthComboBox.getSelectionModel().selectPrevious();
    }

    @FXML
    private void onClickNextMonth() throws IOException {
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
        updateCategoryButton.setVisible(false);
        addNewCategoryButton.setVisible(true);
    }

    @FXML
    private void showMainView(){
        mainView.toFront();
    }



    public void showEditSubCategoryWindow(SubCategoryController subCategoryController){
        this.subCategoryController = subCategoryController;
        addNewSubCategoryPane.toFront();
        updateSubCategoryButton.setVisible(true);
        addNewSubCategoryButton.setVisible(false);
        newSubCategoryName.setText(subCategoryController.subCategory.getName());
        newSubCategoryBudget.setText(String.valueOf(subCategoryController.subCategory.getBudget()));
    }

    @FXML
    private void updateSubCategory(){
        subCategoryController.parentController.categoryItem.removeSubcategoryBudget(subCategoryController.subCategory);
        subCategoryController.subCategory.setName(newSubCategoryName.getText());
        subCategoryController.subCategory.setBudget(Double.parseDouble(newSubCategoryBudget.getText()));
        subCategoryController.parentController.categoryItem.addSubcategoryBudget(subCategoryController.subCategory);
        updateCategoryList();
        showMainView();
    }

    public void showEditCategoryWindow(CategoryController categoryController){
        this.categoryController = categoryController;
        addNewCategoryPane.toFront();
        updateCategoryButton.setVisible(true);
        addNewCategoryButton.setVisible(false);
        categoryComboBox.getSelectionModel().select(categoryController.categoryItem.getCategory());
        newCategoryBudget.setText(String.valueOf(categoryController.categoryItem.getBudget()));

    }

    @FXML
    private void updateCategory(){
        categoryController.categoryItem.setCategory(Category.valueOf(categoryComboBox.getSelectionModel().getSelectedItem().toString()));
        categoryController.categoryItem.setBudget(Double.parseDouble(newCategoryBudget.getText()));
        updateCategoryList();
        showMainView();
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
        resetNewCategoryInputs();
    }

    @FXML
    public void showAddSubCategoryWindow(CategoryController categoryController){
        addNewSubCategoryPane.toFront();
        updateSubCategoryButton.setVisible(false);
        addNewSubCategoryButton.setVisible(true);
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
        resetNewCategoryInputs();
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

    @FXML
    private void LoadSubCategoryComboBox(){
        ObservableList<CategorySubItem> subCategories = FXCollections.observableArrayList();
        subCategories.addAll(newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getSubCategories());

        newExpenseSubCategoryComboBox.setItems(subCategories);
        newExpenseSubCategoryComboBox.setConverter(comboBoxSubCategoryStringConverter);
    }

    private void resetNewCategoryInputs(){
        categoryComboBox.getSelectionModel().selectFirst();
        newCategoryBudget.setText("");
        newSubCategoryName.setText("");
        newSubCategoryBudget.setText("");
    }


    private User user;
    private CategoryController categoryController;
    private SubCategoryController subCategoryController;
    public BudgetMonth selectedBudgetMonth;
    ObservableList<BudgetMonth> budgetMonths =  FXCollections.observableArrayList();


    public MainController(User user) {
        this.user = user;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/MainView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }

    }


    public void initialize() {
        budgetMonthsMockUp();
        initializeComboBox();
        initializeBudgetMonths();
        updateBarChart(budgetMonths);
        updateMainView();
        initializeCategoryComboBox();
        updateLatestTransaction();
        LoadExpenseCategoriesComboBox();

    }
    public void updateMainView() {
        budgetLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()));
        budgetSpentLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetSpent()));
        budgetRemainingLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()-selectedBudgetMonth.getBudgetSpent()));
        updateCategoryList();
        updatePieChart(selectedBudgetMonth.getCategories());
        updateLatestTransaction();

    }

    private void updatePieChart(@NotNull ArrayList<CategoryItem> categories) {
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

    private void LoadExpenseCategoriesComboBox(){
        ObservableList<CategoryItem> categories = FXCollections.observableArrayList();
        categories.addAll(selectedBudgetMonth.getCategories());
        newExpenseCategoryComboBox.setItems(categories);
        newExpenseCategoryComboBox.setConverter(comboBoxCategoryStringConverter);
        newExpenseCategoryComboBox.getSelectionModel().selectFirst();
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

    public void updateLatestTransaction(){
        latestPurchases.getChildren().clear();
        for (Expense expense : selectedBudgetMonth.getExpenses()){
            TransactionController transactionController = new TransactionController(this, expense);
            latestPurchases.getChildren().add(transactionController);
        }
    }

    private void budgetMonthsMockUp() {
        BudgetMonth tempBudgetMonth1 = new BudgetMonth(5000, 2022, Month.AUGUST);
        BudgetMonth tempBudgetMonth2 = (new BudgetMonth(4000, 2022, Month.SEPTEMBER));
        BudgetMonth tempBudgetMonth3 = (new BudgetMonth(7000, 2022, Month.OCTOBER));
        CategoryItem tempCategoryItem1 = new CategoryItem(0, Category.Food);
        CategoryItem tempCategoryItem2 = new CategoryItem(300, Category.Transportation);
        tempCategoryItem1.addSubCategory(new CategorySubItem(40, "AHHH"));

       /* tempBudgetMonth1.addTransaction(new Expense(100.0,"McDonalds", "7 -23-2018", Category.Food,"a"));
        tempBudgetMonth1.addTransaction(new Expense(100.0,"Taxi", "7-23-2018", Category.Transportation,"b"));
        tempBudgetMonth1.addTransaction(new Expense(100.0,"Dator", "7-23-2018", Category.Hobbies,"s"));
        tempBudgetMonth1.addTransaction(new Expense(100.0,"Investment", "7-23-2018", Category.Savings,"a"));
        tempBudgetMonth2.addTransaction(new Expense(100.0,"Shopping", "2018- 07 - 23", Category.Hobbies,"f"));
*/
        //tempBudgetMonth1.addIncome(new Income(10000.0, "Salary", "2022-07-07", Category.Income));

        //tempCategoryItem1.incrementBudgetSpent(50);
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

    private StringConverter<CategoryItem> comboBoxCategoryStringConverter = new StringConverter<CategoryItem>() {

        @Override
        public String toString(CategoryItem categoryItem) {
            return categoryItem.getName();
        }

        @Override
        public CategoryItem fromString(String string) {
            return null;
        }

    };

    private StringConverter<CategorySubItem> comboBoxSubCategoryStringConverter = new StringConverter<CategorySubItem>() {

        @Override
        public String toString(CategorySubItem categorySubItem) {
            return categorySubItem.getName();
        }

        @Override
        public CategorySubItem fromString(String string) {
            return null;
        }
    };


}
