package budgetapp.model.categories;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;
 public class AbstractCategoryItem {

    private String id;
    private String name;
    private double budget;
    private double budgetSpent;


    public AbstractCategoryItem(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.budgetSpent = 0;
    }

    public AbstractCategoryItem() {

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null)
            this.name = name;
        else
            throw new IllegalArgumentException();
    }


    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isBudgetEmpty(){
        return getBudget() == 0;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }
    public void setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
    }


    public void incrementBudget(double amount) {
        setBudget(budget + amount);
    }

    public void decrementBudget(double amount) {
        setBudget(budget - amount);

    }

    public void incrementBudgetSpent(double amount) {
        setBudgetSpent(budgetSpent + amount);
    }
    public void decrementBudgetSpent(double amount) {
        setBudgetSpent(budgetSpent - amount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
