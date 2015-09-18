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
public class deleteGuestCard extends JdbcDaoSupport implements deleteGuestCardImpl {
    @Override
    public Map<String, String> delete(Integer id) {
        Map<String, String> message;
        try{
            getJdbcTemplate().update(
                    "DELETE FROM guests_and_cards WHERE guest_id = ?;",
                    new Object[]{id}
            );
            message = new HashMap<>();
            message.put("message", "Success deleting guestCard");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when deleting guestCard");
        }
        return message;
    }
}
