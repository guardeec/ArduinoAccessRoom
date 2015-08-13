package accessToDataBase.JDBC.Admin.DeviceAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class addDevice extends JdbcDaoSupport implements  addDeviceImpl {

    @Override
    public Map<String, String> add(String specification, String ip) {
       Map<String, String> message;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "INSERT INTO devices(specification, ip) VALUES (?, ?) RETURNING id;",
                    new Object[]{specification, ip},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("id", null);
            message.put("message", "Error when adding Devices");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("id", Integer.toString(resultSet.getInt("id")));
            searchStrokeResult.put("message","Success Devices adding");
            return searchStrokeResult;
        }
    }

}
