package budgetapp.model;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

abstract public class AbstractCategoryItem {
    private String name;
    private double budget;
    private double budgetSpent;
    private Image icon;
    private Category category;


    public AbstractCategoryItem(String name, double budget, Category category) {
        this.name = name;
        this.budget = budget;
        this.budgetSpent = 0;
        this.category = category;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Image getIcon() {
        return null;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }


   public Category getCategory() {
        return category;
    }

    public double getBudget() {
        return budget;
    }
    protected void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }
    private void setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
    }


    protected void incrementBudget(int amount) {
        setBudget(budget + amount);
    }
    protected void decrementBudget(int amount) {
        setBudget(budget - amount);
    }

    //TODO Temp probably shouldn't be public
    public void incrementBudgetSpent(int amount) {
        setBudgetSpent(budgetSpent + amount);
    }
    public void decrementBudgetSpent(int amount) {
        setBudgetSpent(budgetSpent - amount);
    }
}
