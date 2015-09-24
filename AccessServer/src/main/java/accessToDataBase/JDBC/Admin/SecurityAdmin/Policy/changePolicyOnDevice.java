package accessToDataBase.JDBC.Admin.SecurityAdmin.Policy;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class changePolicyOnDevice extends JdbcDaoSupport implements changePolicyOnDeviceImpl {
    @Override
    public Map<String, String> change(Integer deviceId, Integer roleId, Boolean access) {
        Map<String, String> message;
        try{
           getJdbcTemplate().update(
                    "UPDATE access_rights SET access = ? WHERE system_role_id = ? AND device_id = ?;",
                    new Object[]{access, roleId, deviceId}
            );
            message = new HashMap<>();
            message.put("message","Success changing Devices`s Polices");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when changing Devices`s Polices");
        }
        return message;
    }
}
