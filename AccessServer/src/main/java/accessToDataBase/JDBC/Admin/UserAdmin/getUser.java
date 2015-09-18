package accessToDataBase.JDBC.Admin.UserAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.servlet.http.HttpServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class getUser extends JdbcDaoSupport implements getUserImpl {
    @Override
    public ArrayList<Map> get(Integer userId, String name, Integer roleId) {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT user_id, name, user_role_id FROM users JOIN users_and_user_roles ON id = user_id WHERE id = coalesce(?,id) AND name = coalesce(?,name) AND user_role_id = coalesce(?, user_role_id);",
                    new Object[]{userId, name, roleId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Error when getting user list");
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
                messageComponent.put("id", Integer.toString(resultSet.getInt("user_id")));
                messageComponent.put("name", resultSet.getString("name"));
                messageComponent.put("role", Integer.toString(resultSet.getInt("user_role_id")));
                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting user list");
            message.add(messageComponent);

            return message;
        }
    }
}
