package accessToDataBase.JDBC.Admin.DeviceAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class getDevice extends JdbcDaoSupport implements getDeviceImpl {
    @Override
    public List<Map<String, String>> get(Integer id, String specification, String ip) {
        List<Map<String, String>> message;
        try{
            message = (List<Map<String, String>>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM devices WHERE id = coalesce(?,id) AND ip = coalesce(?,ip) AND specification = coalesce(?,specification) ORDER BY id;",
                    new Object[]{id, ip, specification},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            ex.printStackTrace();


            message = new ArrayList<Map<String, String>>();
            Map<String, String> messageComponent = new HashMap<>();
            for (int i=0; i<ex.getStackTrace().length; i++){
                messageComponent.put(Integer.toString(i),ex.getStackTrace()[i].toString());
            }
            messageComponent.put("message", "Error when getting devices list");
            message.add(messageComponent);


        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<Map<String, String>> message = new ArrayList<Map<String, String>>();

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
