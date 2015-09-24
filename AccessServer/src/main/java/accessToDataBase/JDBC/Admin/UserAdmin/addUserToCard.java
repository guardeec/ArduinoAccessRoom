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
            getJdbcTemplate().update(
                    "INSERT INTO employees_and_cards VALUES (?,?);",
                    new Object[]{userId, cardId}
            );
            getJdbcTemplate().update(
                    "INSERT INTO employees_and_roles VALUES (?,?);",
                    new Object[]{userId, roleId}
            );
            message = new HashMap<>();
            message.put("message", "Success when adding user to card");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when adding user to card");
        }
        return message;
    }
}
