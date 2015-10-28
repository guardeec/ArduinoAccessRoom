package JDBC.arduino.client;
import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.arduino.client.EnterOtOutTheRoomImpl;
import Methods.POJO.ArduinoClient.RoomDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class EnterOrOutTheRoom extends JdbcDaoSupport implements EnterOtOutTheRoomImpl {


    @Override
    public String log(RoomDATA room) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO ard_rfid_events (event_id, user_id, user_name, sys_r_id, sys_r_title, card_id) VALUES (?, ?, ?, ?, ?, ?);",
                    new Object[]{
                        room.getGeneral_event_type_id(),
                            room.getUser_id(),
                            room.getUser_name(),
                            room.getSys_r_id(),
                            room.getSys_r_title(),
                            room.getCard_id()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
