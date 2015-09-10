package accessToDataBase.JDBC.Arduino;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public class cashAdminDB extends JdbcDaoSupport implements cashAdminDBImpl {
    @Override
    public ArrayList<Map> get(Integer deviceId) {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT users.id, users.name, cards.number FROM users JOIN users_and_cards ON users.id = users_and_cards.user_id JOIN cards ON cards.id = users_and_cards.card_id JOIN users_and_roles ON users.id = users_and_roles.user_id JOIN roles ON users_and_roles.role_id = roles.id JOIN access_rights ON roles.id = access_rights.role_id WHERE access_rights.role_id = 3 AND device_id = ?;",
                    new Object[]{deviceId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Error when getting adminCash list");
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
                messageComponent.put("name", resultSet.getString("name"));
                messageComponent.put("number", resultSet.getString("number"));
                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting adminCash list");
            message.add(messageComponent);
            return message;
        }
    }
}
