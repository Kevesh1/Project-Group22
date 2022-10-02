package budgetapp.model;


public class User {
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String password;
    private boolean familyShare;
    private boolean elderlyAdjusted;
    private int userID;

<<<<<<< Updated upstream
=======
    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

>>>>>>> Stashed changes
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

<<<<<<< Updated upstream
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

=======
>>>>>>> Stashed changes
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
<<<<<<< Updated upstream

    public int getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(int totalBudget) {
        this.totalBudget = totalBudget;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public boolean isFamilyShare() {
        return familyShare;
    }

    public void setFamilyShare(boolean familyShare) {
        this.familyShare = familyShare;
    }

    public boolean isElderlyAdjusted() {
        return elderlyAdjusted;
    }

    public void setElderlyAdjusted(boolean elderlyAdjusted) {
        this.elderlyAdjusted = elderlyAdjusted;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


=======
>>>>>>> Stashed changes
}
