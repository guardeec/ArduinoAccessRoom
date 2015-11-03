package accessToDataBase.JDBC.admin.client.Reception;

import Methods.POJO.AdminClient.HR.EmployeeDATA;
import Methods.POJO.AdminClient.Reception.GuestDATA;
import accessToDataBase.JDBC_Impl.admin.client.reception.GuestImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.11.15.
 */
public class Guest extends JdbcDaoSupport implements GuestImpl {

    @Override
    public GuestDATA create(GuestDATA guestDATA) {
        try {
            Integer id = getJdbcTemplate().queryForInt(
                    "?",
                    new Object[]{
                            guestDATA.getGuest_name()
                    }
            );
            getJdbcTemplate().update(
                    "?",
                    new Object[]{
                            id,
                            guestDATA.getCard_id()
                    }
            );
            guestDATA.setGuest_id(id);
            return guestDATA;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public List<GuestDATA> read(GuestDATA guestDATA) {
        List<GuestDATA> listOfGuests;
        try {
            listOfGuests = (List<GuestDATA>) getJdbcTemplate().queryForObject(
                    "?",
                    new Object[]{
                            guestDATA.getGuest_name(),
                            guestDATA.getGuest_id(),
                            guestDATA.getCard_id()
                    },
                    new SearchRowMapper()
            );
            return listOfGuests;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<GuestDATA> list = new LinkedList<>();
            do {
                GuestDATA guestDATA = new GuestDATA();
                guestDATA.setSpecificParams(
                        resultSet.getInt("guest_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("card_id"),
                        resultSet.getTime("time_start").getTime(),
                        resultSet.getTime("time_end").getTime()
                );
                list.add(guestDATA);
            }while (resultSet.next());
            return list;
        }
    }

    @Override
    public List<GuestDATA> readHistory(GuestDATA guestDATA) {
        List<GuestDATA> listOfGuests;
        try {
            listOfGuests = (List<GuestDATA>) getJdbcTemplate().queryForObject(
                    "?",
                    new Object[]{
                            guestDATA.getGuest_name(),
                            guestDATA.getGuest_id(),
                            java.util.Date.from(Instant.ofEpochSecond(guestDATA.getGeneral_datetime()))
                    },
                    new HistorySearchRowMapper()
            );
            return listOfGuests;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class HistorySearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<GuestDATA> list = new LinkedList<>();
            do {
                GuestDATA guestDATA = new GuestDATA();
                guestDATA.setSpecificParams(
                        resultSet.getInt("guest_id"),
                        resultSet.getString("name"),
                        null,
                        resultSet.getTime("time_start").getTime(),
                        resultSet.getTime("time_end").getTime()
                );
                list.add(guestDATA);
            }while (resultSet.next());
            return list;
        }
    }

    @Override
    public GuestDATA update(GuestDATA guestDATA) {
        try {
            getJdbcTemplate().update(
                    "?",
                    new Object[]{
                            guestDATA.getGuest_name(),
                            guestDATA.getGuest_id()
                    }
            );
            getJdbcTemplate().update(
                    "?",
                    new Object[]{
                            guestDATA.getCard_id(),
                            guestDATA.getGuest_id()

                    }
            );
            getJdbcTemplate().update(
                    "?",
                    new Object[]{
                            java.util.Date.from(Instant.ofEpochSecond(guestDATA.getTime_end())),
                            guestDATA.getGuest_id()
                    }
            );
            return guestDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public GuestDATA delete(GuestDATA guestDATA) {
        try {
            getJdbcTemplate().update(
                    "?",
                    new Object[]{
                            guestDATA.getGuest_id()
                    }
            );
            return guestDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
}
