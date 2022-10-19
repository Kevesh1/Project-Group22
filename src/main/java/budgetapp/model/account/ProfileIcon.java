package budgetapp.model.account;

import java.util.ArrayList;
import java.util.List;

public class ProfileIcon implements ILocked{
    private String ProfilePicture;
    //Better having an expandable class than having a String[] with links to pictures
    private String profileGif;

    public static final List<ProfileIcon> allProfileIcons = new ArrayList<>();

    public ProfileIcon(){
        allProfileIcons.add(this);
    }


    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        ProfilePicture = profilePicture;
    }

    public String getProfileGif() {
        return profileGif;
    }

    public void setProfileGif(String profileGif) {
        this.profileGif = profileGif;
    }

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

    @Override
    public void lock() {

    }

    @Override
    public void unlock() {

    }
}
