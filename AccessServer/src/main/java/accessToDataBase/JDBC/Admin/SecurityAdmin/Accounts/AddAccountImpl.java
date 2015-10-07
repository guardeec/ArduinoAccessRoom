package accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts;

import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public interface AddAccountImpl {
    public Map<String, String> add(String login, String password, Integer id, Boolean technical, Boolean reception, Boolean hr, Boolean security);
}
