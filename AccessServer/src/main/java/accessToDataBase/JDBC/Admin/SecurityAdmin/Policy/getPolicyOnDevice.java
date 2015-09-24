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
public class getPolicyOnDevice extends JdbcDaoSupport implements getPolicyOnDeviceImpl {
    @Override
    public Map<String, String> get(Integer deviceId, Integer roleId) {
        Map<String, String> message = null;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM access_rights WHERE system_role_id = coalesce(?, system_role_id) AND device_id = coalesce(?, device_id) AND access = coalesce(NULL, access);",
                    new Object[]{roleId, deviceId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when getting Devices`s Polices");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("getAccess", Boolean.toString(resultSet.getBoolean("getAccess")));
            searchStrokeResult.put("message","Success getting Devices`s Polices");
            return searchStrokeResult;
        }
    }
}
