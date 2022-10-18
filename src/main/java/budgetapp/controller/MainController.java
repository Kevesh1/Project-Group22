package budgetapp.controller;

import budgetapp.controller.categories.CategoryController;
import budgetapp.controller.categories.SubCategoryController;
import budgetapp.controller.transactions.ExpenseController;
import budgetapp.controller.transactions.IncomeController;
import budgetapp.model.BudgetMonth;
import budgetapp.model.account.User;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;

import dataaccess.mongodb.dao.BudgetMonthDao;
import dataaccess.mongodb.dao.account.AccountDao;
import dataaccess.mongodb.dao.categories.CategoryDao;
import dataaccess.mongodb.dto.categories.CategoryItemDto;
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
import java.time.LocalDate;
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
    ComboBox<Category> categoryComboBox;
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
        selectedBudgetMonth.addTransaction(expense);
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
        selectedBudgetMonth.addTransaction(new Income(cost, note, date));
        updateLatestTransaction();
        updateMainView();
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
        updateLists();
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
        updateMainView();
    }


    public void showEditSubCategoryWindow(SubCategoryController subCategoryController){
        this.subCategoryController = subCategoryController;
        CategorySubItem subCategory = subCategoryController.getSubCategory();
        addNewSubCategoryPane.toFront();
        updateSubCategoryButton.setVisible(true);
        addNewSubCategoryButton.setVisible(false);
        newSubCategoryName.setText(subCategory.getName());
        newSubCategoryBudget.setText(String.valueOf(subCategory.getBudget()));
    }

    @FXML
    private void updateSubCategory(){
        CategorySubItem subCategory = subCategoryController.getSubCategory();
        subCategoryController.getCategoryItem().removeSubcategoryBudget(subCategory);
        subCategory.setName(newSubCategoryName.getText());
        subCategory.setBudget(Double.parseDouble(newSubCategoryBudget.getText()));
        subCategoryController.getCategoryItem().addSubcategoryBudget(subCategory);
        updateCategoryList();
        showMainView();
    }

    public void showEditCategoryWindow(CategoryController categoryController){
        this.categoryController = categoryController;
        addNewCategoryPane.toFront();
        updateCategoryButton.setVisible(true);
        addNewCategoryButton.setVisible(false);
        categoryComboBox.getSelectionModel().select(categoryController.getCategoryItem().getCategory());
        newCategoryBudget.setText(String.valueOf(categoryController.getCategoryItem().getBudget()));

    }

    @FXML
    private void updateCategory(){
        categoryController.getCategoryItem().setCategory(Category.valueOf(categoryComboBox.getSelectionModel().getSelectedItem().toString()));
        categoryController.getCategoryItem().setBudget(Double.parseDouble(newCategoryBudget.getText()));
        updateCategoryList();
        showMainView();
    }

    @FXML
    private void addNewCategory(){
        /*if (newCategoryBudget == null) {
            newCategoryBudget.setText("0");
        }*/

        Category category = Category.valueOf(categoryComboBox.getSelectionModel().getSelectedItem().toString());
        CategoryItem categoryItem = new CategoryItem(category);
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
        categoryController.getCategoryItem().addSubCategory(categorySubItem);
        categoryController.getCategoryItem().addSubcategoryBudget();
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
    ObservableList<BudgetMonth> budgetMonths;
    private BudgetMonthDao budgetMonthDao;
    private CategoryDao categoryDao;


    public MainController(User user) {
        this.user = user;
        this.budgetMonths = FXCollections.observableArrayList();
        budgetMonthDao = new BudgetMonthDao();
        categoryDao = new CategoryDao();

        loadBudgetMonths();

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

    private void loadBudgetMonths() {
        //budgetMonthDao.addManyBudgetMonths(createDefaultBudgetMonths(), user.getId());
        Optional<List<BudgetMonth>> dbBudgetMonths = budgetMonthDao.getAllBudgetMonthsByUserId(user.getId());
        if (dbBudgetMonths.isPresent()) {
            System.out.println("ISPRESENT");
            budgetMonths.addAll(dbBudgetMonths.get());
        }
        else {
            System.out.println("NOT PRESENT");
            //budgetMonths = FXCollections.observableArrayList(createDefaultBudgetMonths());
//            budgetMonthDao.addManyBudgetMonths(createDefaultBudgetMonths(), user.getId());
            List<BudgetMonth> defaultBudgetMonths = budgetMonthDao
                    .initNewBudgetMonths(createDefaultBudgetMonths(), createDefaultCategoryItems(), user.getId());
            budgetMonths.addAll(defaultBudgetMonths);
            }
        selectedBudgetMonth = budgetMonths.get(0);
    }

    private List<BudgetMonth> createDefaultBudgetMonths() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<BudgetMonth> budgetMonths = new ArrayList<>();
        for (int i = year -1; i < year +2; i++){
            for (Month month : Month.values()){
                BudgetMonth budgetMonth = new BudgetMonth(i, month);
                budgetMonths.add(budgetMonth);
            }
        }
        return budgetMonths;
    }

    private List<CategoryItem> createDefaultCategoryItems() {
        List<CategoryItem> categoryItems = new ArrayList<>();
        for (Category category : Category.values()){
            if (category != Category.Transportation) {
                categoryItems.add(new CategoryItem(category));
            } else {
                break;
            }
        }
        return categoryItems;
    }

    @FXML
    public void initialize() {
        //budgetMonthsMockUp();
        initializeComboBox();
        initializeBudgetMonths();
        updateBarChart(budgetMonths);
        updateMainView();
        updateLists();
        initializeCategoryComboBox();
        LoadExpenseCategoriesComboBox();
    }

    //This can be in UpdateMainView if issues regarding removing subcategories gets resolved
    private void updateLists(){
        updateCategoryList();
        updateLatestTransaction();

    }
    public void updateMainView() {
        budgetLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()));
        budgetSpentLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetSpent()));
        budgetRemainingLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetRemaining()));

        updatePieChartCategories(selectedBudgetMonth.getCategories());

    }

    public void updatePieChartCategories(List<CategoryItem> categories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(CategoryItem category : categories) {
            if (category.getBudget() > 0){
                data.add(new PieChart.Data(category.getName(), category.getBudget()));
                System.out.println(category.getBudget());
            }
        }
        pieChart.setData(FXCollections.observableArrayList(data));
        System.out.println(data);

    }

    private void updatePieChartSubCategories(@NotNull ArrayList<CategorySubItem> categories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(CategorySubItem category : categories) {
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
        categories.addAll(List.of(Category.values()));
        categoryComboBox.setItems(categories);
        categoryComboBox.getSelectionModel().selectFirst();

    }

    private void LoadExpenseCategoriesComboBox(){
        newExpenseDate.setValue(LocalDate.now());
        newIncomeDate.setValue(LocalDate.now());
        ObservableList<CategoryItem> categories = FXCollections.observableArrayList();
        categories.addAll(selectedBudgetMonth.getCategories());
        newExpenseCategoryComboBox.setItems(categories);
        newExpenseCategoryComboBox.setConverter(comboBoxCategoryStringConverter);
        newExpenseCategoryComboBox.getSelectionModel().selectFirst();
    }



    public void initializeBudgetMonths() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        int currentBudget = ((currentYear)-(currentYear-1))*12 + currentMonth;
        System.out.println(currentBudget);
        yearMonthComboBox.getSelectionModel().select(currentBudget);
        selectedBudgetMonth = yearMonthComboBox.getSelectionModel().getSelectedItem();
    }

    public void updateCategoryList() {
        categoriesFlowPane.getChildren().clear();
        int i = 0;
        for (CategoryItem categoryItem : selectedBudgetMonth.getCategories()) {
            CategoryController categoryController = new CategoryController(this, categoryItem,i);
            categoriesFlowPane.getChildren().add(categoryController);
            i++;
        }
    }

    public void updateLatestTransaction(){
        latestPurchases.getChildren().clear();
        if (selectedBudgetMonth.getTransactions() != null) {
            for (Transaction transaction : selectedBudgetMonth.getTransactions()){
                if (transaction instanceof Expense){
                    ExpenseController expenseController = new ExpenseController(this, (Expense) transaction);
                    latestPurchases.getChildren().add(expenseController);
                }
                else{
                    IncomeController incomeController = new IncomeController(this, (Income)transaction);
                    latestPurchases.getChildren().add(incomeController);
                }
            }
        }
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
