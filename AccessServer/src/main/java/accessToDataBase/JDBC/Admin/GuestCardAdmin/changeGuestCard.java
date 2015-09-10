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
    public Map<String, String> change(Integer id, String name, Time startTime, Time endTime, java.sql.Date date) {
        Map<String, String> message = null;

        if(!name.isEmpty()){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE guests SET name = ? WHERE id = ?;",
                        new Object[]{name, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing guestCard");
            }
        }
        if(startTime != null){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE guests SET time_start = ? WHERE id = ?;",
                        new Object[]{startTime, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing guestCard");
            }
        }
        if(endTime != null){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE guests SET time_end = ? WHERE id = ?;",
                        new Object[]{endTime, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing guestCard");
            }
        }
        if(date != null){
            try{
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "UPDATE guests SET date = ? WHERE id = ?;",
                        new Object[]{date, id},
                        new SearchRowMapper()
                );
            }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                message = new HashMap<>();
                message.put("message", "Error when changing guestCard");
            }
        }

        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Map <String, String> searchStrokeResult = new HashMap<>();
            searchStrokeResult.put("message","Success changing guestCard");
            return searchStrokeResult;
        }
    }
}
