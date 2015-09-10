package accessToDataBase.JDBC.Admin.SecurityAdmin.Roles;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class changeRole extends JdbcDaoSupport implements changeRoleImpl {
    @Override
    public Map<String, String> change(Integer roleId, String role) {
        Map<String, String> message = null;

        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "UPDATE roles SET title = ? WHERE id = ?;",
                    new Object[]{role, roleId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when changing Roles");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success Roles adding");
            return searchStrokeResult;
        }
    }
}
