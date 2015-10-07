package accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public interface GetAccountImpl {
    public List<Map> get(String login, Integer id, Integer employee_id);
}
