package budgetapp.controller.graphs;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackedBarChartController extends AnchorPane {

    // ADD LISTENER
    ObservableList<XYChart.Data<String, Number>> stackedBarChartData = FXCollections.observableArrayList();

    @FXML
    StackedBarChart<String, Number> stackedBarChart;

    public StackedBarChartController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/stackedBarChart.fxml"));
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

    @FXML
    public void initialize() {
        stackedBarChart.getYAxis().setLabel("Budget");
        stackedBarChart.setTitle("Yearly budget");
    }

    public void updateBarChart(List<BudgetMonth> budgetMonths) {
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
        stackedBarChart.setData(FXCollections.observableArrayList(temp));
    }
}
