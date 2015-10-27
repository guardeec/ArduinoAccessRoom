package accessToDataBase.JDBC.Admin.GuestCardAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class getGuestCard extends JdbcDaoSupport implements getGuestCardImpl {
    @Override
    public ArrayList<Map> get(String name, Date date, Integer cardId) {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT guest_id, name, card_id, time_start, time_end, date FROM guests JOIN guests_and_cards ON guests.id = guests_and_cards.guest_id WHERE name = coalesce(?,name) AND date = coalesce(?, date) AND card_id = coalesce(?,card_id) AND current_date = date AND current_time <= time_end ORDER BY guest_id;",
                    new Object[]{name, date, cardId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("id", "no");
            messageComponent.put("name", "no");
            messageComponent.put("time_start", "no");
            messageComponent.put("time_end", "no");
            messageComponent.put("date", "no");
            messageComponent.put("card_id", "no");
            messageComponent.put("message", "Success getting devices list");
            message.add(messageComponent);
        } catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Error when getting guestCard list");
            message.add(messageComponent);
        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            ArrayList<Map> message = new ArrayList<>();

            do {
                Map<String, String> messageComponent = new HashMap<>();
                messageComponent.put("id", Integer.toString(resultSet.getInt("guest_id")));
                messageComponent.put("name", resultSet.getString("name"));
                messageComponent.put("card_id", resultSet.getString("card_id"));
                messageComponent.put("time_start", resultSet.getTime("time_start").toString());
                messageComponent.put("time_end", resultSet.getTime("time_end").toString());
                messageComponent.put("date", resultSet.getDate("date").toString());

                message.add(messageComponent);
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting devices list");
            message.add(messageComponent);

            return message;
        }
    }
}
