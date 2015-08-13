package accessToDataBase.JDBC.Admin.GuestCardAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class addGuest extends JdbcDaoSupport implements addGuestImpl {
    @Override
    public Map<String, String> addGuest(String name) {
        Map<String, String> message;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "INSERT INTO guests(name) VALUES(?) RETURNING id;",
                    new Object[]{name},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("id", null);
            message.put("message", "Error when adding guest");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {

            Map<String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("id", Integer.toString(resultSet.getInt("id")));
            searchStrokeResult.put("message", "Success adding guest");
            return searchStrokeResult;
        }
    }
}
