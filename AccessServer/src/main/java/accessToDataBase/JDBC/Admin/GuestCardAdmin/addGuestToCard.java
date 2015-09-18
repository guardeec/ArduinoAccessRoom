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
public class addGuestToCard extends JdbcDaoSupport implements addGuestToCardImpl {
    @Override
    public Map<String, String> add(Integer guestId, Integer cardId) {
        Map<String, String> message;
        try{
            getJdbcTemplate().update(
                    "INSERT INTO guests_and_cards VALUES (?,?);",
                    new Object[]{guestId, cardId}
            );
            message = new HashMap<>();
            message.put("message","Success guest to card adding");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("id", null);
            message.put("message", "Error when adding guest to card");
        }
        return message;
    }
}
