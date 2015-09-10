package accessToDataBase.JDBC.Admin.UserAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class changeUser extends JdbcDaoSupport implements changeUserImpl {
    @Override
    public Map<String, String> change(Integer id, String name, Integer roleId) {
        Map<String, String> message = null;
        if(name != null){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE users SET name = ? WHERE id = ?;",
                        new Object[]{name, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("id", null);
                message.put("message", "Error when changing user");
            }
        }
        if (roleId != null){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE users_and_roles SET role_id = ? WHERE user_id = ?;",
                        new Object[]{roleId, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("id", null);
                message.put("message", "Error when changing user");
            }
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success user changing");
            return searchStrokeResult;
        }
    }
}
