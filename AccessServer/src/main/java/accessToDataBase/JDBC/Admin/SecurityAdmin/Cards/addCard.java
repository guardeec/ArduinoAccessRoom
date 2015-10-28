package accessToDataBase.JDBC.Admin.SecurityAdmin.Cards;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class addCard extends JdbcDaoSupport implements addCardImpl {

    @Override
    public Map<String, String> add(String card) {

        Map<String, String> message;
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                    "INSERT INTO cards(number) VALUES (?) RETURNING id;",
                    new Object[]{card},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("id", null);
            message.put("message", "Error when adding card");
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("id", Integer.toString(resultSet.getInt("id")));
            searchStrokeResult.put("message","Success card adding");
            return searchStrokeResult;
        }
    }
}
