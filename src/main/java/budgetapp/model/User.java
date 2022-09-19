package budgetapp.model;

import javafx.scene.image.Image;


public class User {
    private String firstName;
    private String lastName;
    private Image profilePicture;
    private String password;
    private int totalBudget;
    private int totalExpenses;

    public User(String firstName, String lastName, Image profilePicture){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

}
