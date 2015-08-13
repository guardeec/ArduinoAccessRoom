package Controllers.Methods;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 13.08.15.
 */
public class AdminType {

    private Map<String, String> adminType = new HashMap<String, String>();

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
}
