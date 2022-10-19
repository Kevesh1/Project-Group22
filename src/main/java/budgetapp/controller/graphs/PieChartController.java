package budgetapp.controller.graphs;

import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PieChartController extends AnchorPane {


    private ObservableList<PieChart.Data> pieChartData;
    @FXML
    PieChart pieChart;
    public PieChartController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/pieChart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize() {

    }

    public void updatePieChartCategories(List<CategoryItem > categories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(CategoryItem category : categories) {
            if (category.getBudget() > 0){
                data.add(new PieChart.Data(category.getName(), category.getBudget()));
                System.out.println(category.getBudget());
            }
        }
        pieChart.setData(FXCollections.observableArrayList(data));
    }

    private void updatePieChartSubCategories(List<CategorySubItem> subCategories) {
        List<PieChart.Data> data = new ArrayList<PieChart.Data>();
        for(CategorySubItem category : subCategories) {
            data.add(new PieChart.Data(category.getName(), category.getBudget()));
        }
        pieChart.setData(FXCollections.observableArrayList(data));
    }
}
