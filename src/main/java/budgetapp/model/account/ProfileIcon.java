package budgetapp.model.account;

import java.util.ArrayList;
import java.util.List;

public class ProfileIcon{
    /**
     * ProfileIcon class
     */
    private String ProfilePicture;
    private String profileGif;

    public static final List<ProfileIcon> allProfileIcons = new ArrayList<>();

    /**
     * ProfileIcon constructor with every new profileIcon object it is automatically placed in the list allProfileIcons.
     */
    public ProfileIcon(){
        allProfileIcons.add(this);
    }

    /**
     * this method gets ProfilePicture
     * @return String
     */
    public String getProfilePicture() {
        return ProfilePicture;
    }

    /**
     * this method sets ProfilePicture
     * @param profilePicture
     */

    public void setProfilePicture(String profilePicture) {
        ProfilePicture = profilePicture;
    }

    /**
     * this method gets profileGif
     * @return profileGif
     */
    public String getProfileGif() {
        return profileGif;
    }

    /**
     * this method sets profil gif
     * @param profileGif
     */
    public void setProfileGif(String profileGif) {
        this.profileGif = profileGif;
    }

    /**
     * this method creates list with all viable profile pictures
     * @return
     */
    public static List<ProfileIcon> profileIconList(){

        ProfileIcon mario = new ProfileIcon();
        mario.setProfilePicture("mario");

        ProfileIcon luigi = new ProfileIcon();
        luigi.setProfilePicture("luigi");

        ProfileIcon peach = new ProfileIcon();
        peach.setProfilePicture("peach");

        ProfileIcon donkey = new ProfileIcon();
        donkey.setProfilePicture("donkey");

        ProfileIcon wario = new ProfileIcon();
        wario.setProfilePicture("wario");

        ProfileIcon waluigi = new ProfileIcon();
        waluigi.setProfilePicture("waluigi");

        return allProfileIcons;


    }


}
