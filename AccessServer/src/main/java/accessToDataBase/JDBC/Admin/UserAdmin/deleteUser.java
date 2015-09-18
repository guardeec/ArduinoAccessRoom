package accessToDataBase.JDBC.Admin.UserAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class deleteUser extends JdbcDaoSupport implements deleteUserImpl {
    @Override
    public Map<String, String> delete(Integer userId) {
        Map<String, String> message;
        try{
            getJdbcTemplate().update(
                    "DELETE FROM users WHERE id = ?;",
                    new Object[]{userId}
            );
            message = new HashMap<>();
            message.put("message", "Success when deleting user");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when deleting user");
        }
        return message;
    }
}
