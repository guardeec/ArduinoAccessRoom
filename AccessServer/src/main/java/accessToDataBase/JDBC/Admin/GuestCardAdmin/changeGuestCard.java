package accessToDataBase.JDBC.Admin.GuestCardAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class changeGuestCard extends JdbcDaoSupport implements changeGuestCardImpl {
    @Override
    public Map<String, String> change(Integer id, String name, Integer cardNumber) {
        Map<String, String> message = null;

        if (!name.isEmpty()){
            try{
                getJdbcTemplate().update(
                        "UPDATE guests SET name = ? WHERE id = ?;",
                        new Object[]{name, id}
                );
                message = new HashMap<>();
                message.put("message", "Success changing guestCard");
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing guestCard");
            }
        }
        if (cardNumber != null){
            try{
                getJdbcTemplate().update(
                        "UPDATE guests_and_cards SET card_id = ?  WHERE  guest_id = ?;",
                        new Object[]{cardNumber, id}
                );
                message = new HashMap<>();
                message.put("message", "Success changing guestCard");
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing guestCard");
            }
        }
        return message;
    }
}
