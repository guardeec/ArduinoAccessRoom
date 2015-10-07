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
                    "SELECT employee_id, name, system_role_id FROM employees JOIN employees_and_roles ON id = employee_id JOIN employee_status ON employee_status_id = employee_status.id WHERE employees.id = coalesce(?,employees.id) AND name = coalesce(?,name) AND system_role_id = coalesce(?, system_role_id) AND description = 'unblocked';",
                    new Object[]{userId, name, roleId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success when getting empty user list");
            message.add(messageComponent);

        }catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
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
                messageComponent.put("id", Integer.toString(resultSet.getInt("employee_id")));
                messageComponent.put("name", resultSet.getString("name"));
                messageComponent.put("role", Integer.toString(resultSet.getInt("system_role_id")));
                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting user list");
            message.add(messageComponent);

            return message;
        }
    }
}
