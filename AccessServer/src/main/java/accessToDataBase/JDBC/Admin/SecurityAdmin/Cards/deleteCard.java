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
public class deleteCard extends JdbcDaoSupport implements deleteCardImpl{
    @Override
    public Map<String, String> delete(Integer cardId) {
        Map<String, String> message;
        try{
            getJdbcTemplate().update(
                    "DELETE FROM cards WHERE id = ?;",
                    new Object[]{cardId}
            );
            message = new HashMap<>();
            message.put("message", "Success when deleting card");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when deleting card");
        }
        return message;
    }
}
