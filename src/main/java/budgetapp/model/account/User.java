package budgetapp.model.account;

public class User{

    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private String profilePicture;

    private String password;

    private boolean familyShare;

    private boolean elderlyAdjusted;
    private String userID;

    public User(String username, String password, String profilePicture) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public User() {

    }

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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    /*
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

     */

    public String getId() {
        return this.id;
    }

    public User setId(String id) {
        this.id = id;
        return this;

    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", familyShare=" + familyShare +
                ", elderlyAdjusted=" + elderlyAdjusted +
                '}';
    }
}
