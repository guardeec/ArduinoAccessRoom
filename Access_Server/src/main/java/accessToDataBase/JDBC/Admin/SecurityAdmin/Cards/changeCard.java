package accessToDataBase.JDBC.Admin.SecurityAdmin.Cards;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class changeCard extends JdbcDaoSupport implements changeCardImpl {
    @Override
    public Map<String, String> change(Integer id, String card) {
        Map<String, String> message = null;

        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "UPDATE cards SET number = ? WHERE id = ?;",
                    new Object[]{id, card},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when changing card");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success card adding");
            return searchStrokeResult;
        }
    }
}
