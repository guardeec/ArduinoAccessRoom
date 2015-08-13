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
public class changeDevice extends JdbcDaoSupport implements changeDeviceImpl {
    @Override
    public Map<String, String> change(Integer id, String specification, String ip) {
        System.out.println(specification == null);
        System.out.println(ip);
        Map<String, String> message = null;
        if(specification != null){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE devices SET specification = ? WHERE id = ?;",
                        new Object[]{specification, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing Devices");
            }
        }
        if(ip != null){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE devices SET ip = ? WHERE id = ?;",
                        new Object[]{ip, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing Devices");
            }
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success Devices changing");
            return searchStrokeResult;
        }
    }
}
