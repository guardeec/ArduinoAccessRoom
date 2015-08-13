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
public class deleteUser extends JdbcDaoSupport implements deleteUserImpl {
    @Override
    public Map<String, String> delete(Integer userId) {
        Map<String, String> message;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "DELETE FROM users WHERE id = ?;",
                    new Object[]{userId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when deleting user");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success user delete");
            return searchStrokeResult;
        }
    }
}
