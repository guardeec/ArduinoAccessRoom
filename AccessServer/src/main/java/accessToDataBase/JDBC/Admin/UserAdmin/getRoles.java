package accessToDataBase.JDBC.Admin.UserAdmin;

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
public class getRoles extends JdbcDaoSupport implements getRolesImpl {
    @Override
    public ArrayList<Map> get() {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM system_roles WHERE id != 1;",
                    new Object[]{},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Error when checking Roles");
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
                messageComponent.put("title", resultSet.getString("title"));
                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting Roles");
            message.add(messageComponent);

            return message;
        }
    }
}
