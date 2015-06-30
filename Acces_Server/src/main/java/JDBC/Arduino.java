package JDBC;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guardeec on 20.05.15.
 */
public class Arduino extends JdbcDaoSupport implements DAO_Arduino {

    private class UserRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int i) throws SQLException, SQLException {
            Map<String, String> message = new HashMap<>();
            message.put("user_id", Integer.toString(rs.getInt("id")));
            message.put("user_name", rs.getString("name"));
            if(rs.getBoolean("state")){
                message.put("user_access", "1");
            }
            else{
                message.put("user_access", "0");
            }
            return message;
        }
    }

    public Map<String, String> Get(String card, String deviceId) {

        Map<String, String> message = new HashMap<>();
        try {
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "SELECT users.id, users.name, cards.state FROM (users JOIN cards ON users.id = cards.user_id) JOIN access_rights ON users.role_id = access_rights.role_id WHERE device_id = ? AND right_id = ? AND cards.card = ? ORDER BY users.id ;",
                    new Object[]{Integer.parseInt(deviceId), Integer.parseInt(deviceId), card},
                    new UserRowMapper()
            );
        }
        catch(org.springframework.dao.EmptyResultDataAccessException ex){
                message.put("user_id", "UNDEFINED");
                message.put("user_name", "UNDEFINED");
                message.put("user_access", "0");
        }

        return message;
    }
}
