package accessToDataBase.JDBC.Authorisation;

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
public class getAdminType extends JdbcDaoSupport implements getAdminTypeImpl {

    @Override
    public Map<String, Boolean> get(String name, String password) {

        /*
        Map<String, Boolean> message;
        try{
            message = (Map<String, Boolean>) getJdbcTemplate().queryForObject(
                    "SQL",
                    new Object[]{name, password},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("deviceAdmin", false);
            message.put("guestAdmin", false);
            message.put("userAdmin", false);
            message.put("securityAdmin", false);
        }
        return message;
        */

        Map<String, Boolean> adminTable = new HashMap<String, Boolean>();

        if (name.contains("Max") && password.contains("12345")){
            adminTable.put("deviceAdmin", true);
            adminTable.put("guestAdmin", true);
            adminTable.put("userAdmin", true);
            adminTable.put("securityAdmin", true);
            adminTable.put("message", true);
        }else {
            adminTable.put("deviceAdmin", false);
            adminTable.put("guestAdmin", false);
            adminTable.put("userAdmin", false);
            adminTable.put("securityAdmin", false);
            adminTable.put("message", false);
        }

        return adminTable;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, Boolean> searchStrokeResult = new HashMap<String, Boolean>();
            searchStrokeResult.put("deviceAdmin", resultSet.getBoolean("deviceAdmin"));
            searchStrokeResult.put("guestAdmin", resultSet.getBoolean("guestAdmin"));
            searchStrokeResult.put("userAdmin", resultSet.getBoolean("userAdmin"));
            searchStrokeResult.put("securityAdmin", resultSet.getBoolean("securityAdmin"));
            return searchStrokeResult;
        }
    }
}
