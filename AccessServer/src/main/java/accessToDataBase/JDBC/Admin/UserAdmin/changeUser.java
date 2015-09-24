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
public class changeUser extends JdbcDaoSupport implements changeUserImpl {
    @Override
    public Map<String, String> change(Integer id, String name, Integer roleId) {
        Map<String, String> message = null;
        if(name != null){
            try{
                getJdbcTemplate().update(
                        "UPDATE employees SET name = ? WHERE id = ?;",
                        new Object[]{name, id}
                );
                message = new HashMap<>();
                message.put("message", "Success when changing user");
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing user");
            }
        }
        if (roleId != null){
            try{
                getJdbcTemplate().update(
                        "UPDATE employees_and_roles SET system_role_id = ? WHERE employee_id = ?;",
                        new Object[]{roleId, id}
                );
                message = new HashMap<>();
                message.put("message", "Success when changing user");
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing user");
            }
        }
        return message;
    }
}
