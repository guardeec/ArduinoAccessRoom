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
                    "SELECT * FROM guests WHERE name = coalesce(?,name) AND date = coalesce(?, date) AND id = coalesce((SELECT guest_id FROM guests_and_cards WHERE card_id = ?) ,id);",
                    new Object[]{name, date, cardId},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
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
                messageComponent.put("id", Integer.toString(resultSet.getInt("id")));
                messageComponent.put("name", resultSet.getString("name"));
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
