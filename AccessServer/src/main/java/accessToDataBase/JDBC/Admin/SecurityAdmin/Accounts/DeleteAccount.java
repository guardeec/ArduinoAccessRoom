package accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public class DeleteAccount extends JdbcDaoSupport implements DeleteAccountImpl {
    @Override
    public Map<String, String> delete(Integer id) {
        Map<String, String> message;
        try{
            getJdbcTemplate().update(
                    "DELETE FROM accounts WHERE id = ?;",
                    new Object[]{id}
            );
            message = new HashMap<>();
            message.put("message", "Success when deleting account");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when deleting account");
        }
        return message;
    }
}
