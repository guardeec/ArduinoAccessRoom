package accessToDataBase.JDBC.Authorisation;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public class checkRunnable extends JdbcDaoSupport implements checkRunnableImpl {
    @Override
    public Map<String, Boolean> check() {
        Map<String, Boolean> message;
        try{
            message = (Map<String, Boolean>) getJdbcTemplate().queryForObject(
                    "SELECT 1;",
                    new Object[]{},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.BadSqlGrammarException ex){
            message = new HashMap<>();
            message.put("DB", true);
        } catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("DB", false);
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, Boolean> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("DB", true);
            return searchStrokeResult;
        }
    }
}
