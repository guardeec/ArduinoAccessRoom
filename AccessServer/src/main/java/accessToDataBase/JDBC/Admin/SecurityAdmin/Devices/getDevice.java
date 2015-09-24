package accessToDataBase.JDBC.Admin.SecurityAdmin.Devices;

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
public class getDevice extends JdbcDaoSupport implements getDeviceImpl {

    @Override
    public ArrayList<Map> get(String specification, String ip, Integer id) {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM devices WHERE id = coalesce(?,id) AND ip = coalesce(?,ip) AND specification = coalesce(?,specification) ORDER BY id;",
                    new Object[]{id, ip, specification},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("id", "...");
            messageComponent.put("specification", "...");
            messageComponent.put("ip", "...");
            messageComponent.put("message", "Success getting devices list");
            message.add(messageComponent);
        } catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Error when getting devices list");
            message.add(messageComponent);


        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            ArrayList<Map> message = new ArrayList<>();

            do {
                Map<String, String> messageComponent = new HashMap<>();
                messageComponent.put("id", Integer.toString(resultSet.getInt("id")));
                messageComponent.put("specification", resultSet.getString("specification"));
                messageComponent.put("ip", resultSet.getString("ip"));
                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting devices list");
            message.add(messageComponent);

            return message;
        }
    }
}
