package accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts;

import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public interface ChangeAccountImpl {
    public Map<String, String> change(String login, String password, Integer id, Boolean technical, Boolean reception, Boolean hr, Boolean security);
}
