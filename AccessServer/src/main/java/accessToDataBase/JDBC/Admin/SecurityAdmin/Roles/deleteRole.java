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
public class deleteRole extends JdbcDaoSupport implements deleteRoleImpl {
    @Override
    public Map<String, String> delete(Integer roleId) {
        Map<String, String> message;
        try{
            getJdbcTemplate().update(
                    "DELETE FROM system_roles WHERE id = ?;",
                    new Object[]{roleId}
            );
            message = new HashMap<>();
            message.put("message", "Success when deleting Roles");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when deleting Roles");
        }
        return message;
    }
}
