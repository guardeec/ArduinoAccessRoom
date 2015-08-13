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
    public ArrayList<Map> get(Integer userId, String name) {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM users WHERE id = coalesce(?,id) AND name = coalesce(?,name);",
                    new Object[]{userId, name},
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
                messageComponent.put("id", Integer.toString(resultSet.getInt("id")));
                messageComponent.put("name", resultSet.getString("name"));
                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting user list");
            message.add(messageComponent);

            return message;
        }
    }
}
