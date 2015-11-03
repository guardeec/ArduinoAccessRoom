package accessToDataBase.JDBC.arduino.client;

import Methods.POJO.ArduinoClient.RoomDATA;
import Methods.POJO.ArduinoClient.RoomDATA_withAccess;
import accessToDataBase.JDBC_Impl.Arduino.GetAccessImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by root on 02.11.15.
 */
public class GetAccess extends JdbcDaoSupport implements GetAccessImpl {
    @Override
    public RoomDATA_withAccess read(RoomDATA_withAccess roomDATA) {

        Boolean user = false;
        Boolean guest = false;

        try {
            Integer employeer_id = getJdbcTemplate().queryForInt(
                    "SELECT employee_id FROM employees_and_cards WHERE card_id = (SELECT cards.id FROM cards WHERE cards.number = ?);",
                    new Object[]{
                            roomDATA.getCard_id()
                    }
            );
            roomDATA.setUser_id(employeer_id);
            user = true;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        } catch (EmptyResultDataAccessException ex){
        }

        if (!user){
            try {
                Integer guest_id = getJdbcTemplate().queryForInt(
                        "SELECT guest_id FROM guests_and_cards WHERE card_id = (SELECT cards.id FROM cards WHERE cards.number = ?);",
                        new Object[]{
                                roomDATA.getCard_id()
                        }
                );
                roomDATA.setUser_id(guest_id);
                guest = true;
            }catch (CannotGetJdbcConnectionException ex){
                return null;
            }catch (EmptyResultDataAccessException ex){
            }
        }

        if (user){
            try {
                roomDATA = (RoomDATA_withAccess) getJdbcTemplate().queryForObject(
                        "SELECT employees.id, employees.name, access_rights.system_role_id, access_rights.access FROM employees JOIN employees_and_cards ON employees.id = employees_and_cards.employee_id JOIN cards ON cards.id = employees_and_cards.card_id JOIN employees_and_roles ON employees.id = employees_and_roles.employee_id JOIN system_roles ON employees_and_roles.system_role_id = system_roles.id JOIN access_rights ON system_roles.id = access_rights.system_role_id WHERE employee_status_id = 1 AND device_id = ? AND cards.number = ?;",
                        new Object[]{
                                roomDATA.getSource_device_id(),
                                roomDATA.getCard_id()
                        },
                        new RowMapperUserGetAccess()
                );
            }
            catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                return null;
            }
            catch (org.springframework.dao.EmptyResultDataAccessException ex ){
                roomDATA.setSpecificParams(
                        null,
                        "BLOCKED",
                        null,
                        "BLOCKED",
                        null,
                        false
                );
            }
        }

        if (guest){
            try {
                roomDATA = (RoomDATA_withAccess) getJdbcTemplate().queryForObject(
                        "SELECT guests.id, guests.name, access_rights.access FROM guests JOIN guests_and_cards ON guests.id = guests_and_cards.guest_id JOIN cards ON cards.id = guests_and_cards.card_id JOIN access_rights ON access_rights.system_role_id = (SELECT id FROM system_roles WHERE title = 'guest') WHERE device_id = ? AND current_time <= time_end AND cards.number = ?;",
                        new Object[]{
                                roomDATA.getSource_device_id(),
                                roomDATA.getCard_id()
                        },
                        new RowMapperGuestGetAccess()
                );
            }
            catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                return null;
            }
            catch (org.springframework.dao.EmptyResultDataAccessException ex ){
                roomDATA.setSpecificParams(
                        null,
                        "TIME_END",
                        1,
                        "Guest",
                        null,
                        false
                );
            }
        }

        if (!user && !guest){
            roomDATA.setSpecificParams(
                    null,
                    "UNKNOWN",
                    null,
                    "UNKNOWN",
                    null,
                    false
            );
        }
        return roomDATA;
    }

    private class RowMapperUserGetAccess implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            RoomDATA_withAccess roomDATA = new RoomDATA_withAccess();
            roomDATA.setSpecificParams(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    null,
                    null,
                    null,
                    resultSet.getBoolean("access")
            );
            return roomDATA;
        }
    }

    private class RowMapperGuestGetAccess implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            RoomDATA_withAccess roomDATA = new RoomDATA_withAccess();
            roomDATA.setSpecificParams(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    1,
                    "guest",
                    null,
                    resultSet.getBoolean("access")
            );
            return roomDATA;
        }
    }
}
