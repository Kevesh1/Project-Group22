package budgetapp.model.account;

/**
 * @author Johannes
 * @author Jacob
 */
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

    /**
     * User constructor. Decides which deafault values that is needed when creating a new user object.
     * @param username
     * @param password
     * @param profilePicture
     */

    public User(String username, String password, String profilePicture) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    /**
     * Empty User constructor for database maping
     */
    public User() {

    }

    /**
     * this method gets firstName
     * @return firstName
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * this method sets firstname
     * @param firstName
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * this method gets lastName
     * @return lastName
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * this method sets lastName
     * @param lastName
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets profilePicture
     * @return profilePicture
     */

    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * sets profilePicture
     * @param profilePicture
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * checks if familyShare status
     * @return
     */
    public boolean isFamilyShare() {
        return familyShare;
    }

    /**
     * sets familyShare status
     * @param familyShare
     */
    public void setFamilyShare(boolean familyShare) {
        this.familyShare = familyShare;
    }

    /**
     * checks status for elderlyAdjusted
     * @return
     */

    public boolean isElderlyAdjusted() {
        return elderlyAdjusted;
    }

    /**
     * sets elderlyAdjusted status
     * @param elderlyAdjusted
     */
    public void setElderlyAdjusted(boolean elderlyAdjusted) {
        this.elderlyAdjusted = elderlyAdjusted;
    }

    /**
     * gets userid
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * sets user id
     * @param id
     * @return id
     */
    public User setId(String id) {
        this.id = id;
        return this;

    }

    /**
     * gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     * @param username
     * @return username
     */

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * method for debugging
     * @return
     */
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
