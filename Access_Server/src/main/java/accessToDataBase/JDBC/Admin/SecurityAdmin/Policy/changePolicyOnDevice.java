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
        Map<String, String> message = null;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "UPDATE access_rights SET access = ? WHERE role_id = ? AND device_id = ?;",
                    new Object[]{access, roleId, deviceId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when changing Devices`s Polices");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success changing Devices`s Polices");
            return searchStrokeResult;
        }
    }
}
