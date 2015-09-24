package accessToDataBase.JDBC.Admin.SecurityAdmin.Roles;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class changeRole extends JdbcDaoSupport implements changeRoleImpl {
    @Override
    public Map<String, String> change(Integer roleId, String role) {
        Map<String, String> message = null;

        try{
            getJdbcTemplate().update(
                    "UPDATE system_roles SET title = ? WHERE id = ?;",
                    new Object[]{role, roleId}
            );
            message = new HashMap<>();
            message.put("message", "Success when changing Roles");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when changing Roles");
        }
        return message;
    }
}
