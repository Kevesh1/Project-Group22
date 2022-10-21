package budgetapp.controller.graphs;

import budgetapp.controller.MainController;
import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackedBarChartController {

    // ADD LISTENER
    ObservableList<XYChart.Data<String, Number>> stackedBarChartData ;

    ObservableList<BudgetMonth> budgetMonths;

    private MainController mainController;

    public StackedBarChartController(MainController mainController, ObservableList<BudgetMonth> budgetMonths) {
        this.mainController = mainController;
        this.budgetMonths = budgetMonths;
    }

    public void initialize() {
        mainController.stackedBarChart.getYAxis().setLabel("Budget");
        mainController.stackedBarChart.setTitle("Yearly budget");
        updateBarChart();
    }

    public void updateBarChart() {
        Map<Category, XYChart.Series<String, Number>> series = new HashMap<>();
        for(BudgetMonth budgetMonth : budgetMonths) {
            for (CategoryItem categoryItem : budgetMonth.getCategoryItems()) {
                series.computeIfAbsent(categoryItem.getCategory(),
                        c -> new XYChart.Series<String, Number>()).setName(categoryItem.getName().toUpperCase());
                series.get(categoryItem.getCategory()).getData().add(new XYChart.Data<String, Number>(budgetMonth.getMonth().toString(), categoryItem.getBudget()));
            }
        }
        List<XYChart.Series<String, Number>> temp = new ArrayList<>() ;
        series.forEach((category, stringNumberSeries) -> temp.add(series.get(category)));
        mainController.stackedBarChart.setData(FXCollections.observableArrayList(temp));
    }
}
