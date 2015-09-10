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
public class addUserToCard extends JdbcDaoSupport implements addUserToCardImpl {
    @Override
    public Map<String, String> add(Integer userId, Integer cardId, Integer roleId) {
        Map<String, String> message;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "INSERT INTO users_and_cards VALUES (?,?);",
                    new Object[]{userId, cardId},
                    new SearchRowMapper()
            );
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "INSERT INTO users_and_roles VALUES (?,?);",
                    new Object[]{userId, roleId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("id", null);
            message.put("message", "Error when adding user to card");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success user to card adding");
            return searchStrokeResult;
        }
    }
}
