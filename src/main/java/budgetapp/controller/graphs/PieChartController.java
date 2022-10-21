package budgetapp.controller.graphs;

import budgetapp.controller.MainController;
import budgetapp.model.categories.AbstractCategoryItem;
import budgetapp.model.categories.CategoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.List;

public class PieChartController{


    private MainController mainController;

    public PieChartController(MainController mainController) {
        this.mainController = mainController;

    }

    public void initialize() {
        mainController.pieChart.setLegendVisible(false);
    }

    private ObservableList<PieChart.Data> initialize(List<CategoryItem > categories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(CategoryItem category : categories) {
            if (category.getBudget() > 0){
                data.add(new PieChart.Data(category.getName(), category.getBudget()));
            }
        }
        return FXCollections.observableArrayList(data);
    }

    public void updatePieChart(List<? extends AbstractCategoryItem> categories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for (AbstractCategoryItem category : categories) {
            if (category.getBudget() > 0) {
                data.add(new PieChart.Data(category.getName(), category.getBudget()));
            }
        }
        mainController.pieChart.setData(FXCollections.observableArrayList(data));
    }
}
