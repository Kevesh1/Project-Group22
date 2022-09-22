package budgetapp.model;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

abstract public class AbstractCategoryItem {
    private String name;
    private double budget;
    private double budgetSpent;
    private Image icon;


    public AbstractCategoryItem(String name, double budget, Image icon) {
        this.budget = budget;
        this.budgetSpent = 0;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public Image getIcon() {
        return icon;
    }
    public void setIcon(Image icon) {
        this.icon = icon;
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
