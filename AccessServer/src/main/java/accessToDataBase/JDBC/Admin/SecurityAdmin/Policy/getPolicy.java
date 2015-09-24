package accessToDataBase.JDBC.Admin.SecurityAdmin.Policy;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class getPolicy extends JdbcDaoSupport implements getPolicyImpl {
    @Override
    public ArrayList<Map> get(Integer deviceId, Integer roleId) {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM access_rights WHERE system_role_id = coalesce(?, system_role_id) AND device_id = coalesce(?, device_id) AND access = coalesce(NULL, access);",
                    new Object[]{roleId, deviceId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Error when getting Polices list");
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
                messageComponent.put("role_id", Integer.toString(resultSet.getInt("system_role_id")));
                messageComponent.put("device_id", resultSet.getString("device_id"));
                messageComponent.put("access", resultSet.getString("access"));
                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting Polices list");
            message.add(messageComponent);

            return message;
        }
    }
}
