package accounts;


public class ProfileFitnessUser {


    private final String loginFit;
    private final String passFit;
    private final String emailFit;

    public ProfileFitnessUser(String loginFit, String passFit, String emailFit) {
        this.loginFit = loginFit;
        this.passFit = passFit;
        this.emailFit = emailFit;
    }

    public ProfileFitnessUser(String loginFit){
        this.loginFit = loginFit;
        this.emailFit = loginFit;
        this.passFit = loginFit;

    }


    public String getLoginFit() {
        return loginFit;

    }

    public String getPassFit() {
        return passFit;
    }

    public String getEmailFit() {
        return emailFit;

    }
}
