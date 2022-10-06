package budgetapp.model.categories;

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



    public AbstractCategoryItem(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.budgetSpent = 0;
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

    /*public void setIcon(Image icon) {
        this.icon = icon;
    }*/

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
