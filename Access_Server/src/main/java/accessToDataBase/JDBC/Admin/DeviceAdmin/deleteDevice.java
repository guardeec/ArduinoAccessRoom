package accessToDataBase.JDBC.Admin.DeviceAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class deleteDevice extends JdbcDaoSupport implements deleteDeviceImpl {
    @Override
    public Map<String, String> delete(Integer id) {
        Map<String, String> message;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "DELETE FROM devices WHERE id = ?;",
                    new Object[]{id},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when deleting Devices");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success Devices delete");
            return searchStrokeResult;
        }
    }
}
