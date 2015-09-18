package Controllers.Methods;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 13.08.15.
 */
public class AdminType {

    private Map<String, String> adminType = new HashMap<String, String>();
    private String login;
    private String password;

    private static AdminType ourInstance = new AdminType();

    public static AdminType getInstance() {
        return ourInstance;
    }

    private AdminType() {
    }

    public void set(Map<String, String> adminType){
        this.adminType = adminType;
    }

    public Map<String, String> get(){
        return adminType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
