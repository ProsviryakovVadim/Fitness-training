package accounts;


import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private final Map<String, ProfileFitnessUser> loginToProfile;
    private final Map<String, ProfileFitnessUser> sessionIdToProfile;

    public AccountService() {
        this.loginToProfile = new HashMap<>();
        this.sessionIdToProfile = new HashMap<>();
    }

    public ProfileFitnessUser getSessionIdToProfile(String session) {
        return sessionIdToProfile.get(session);
    }

    public ProfileFitnessUser getLoginToProfile(String login) {
        return loginToProfile.get(login);
    }

    public void addNewUser(ProfileFitnessUser profileFitnessUser){
        loginToProfile.put(profileFitnessUser.getLoginFit(), profileFitnessUser);
    }

    public void addSessionId(String sessionId, ProfileFitnessUser profileFitnessUser){
        sessionIdToProfile.put(sessionId, profileFitnessUser);

    }

    public void deleteSession(String session){
        sessionIdToProfile.remove(session);

    }


}
