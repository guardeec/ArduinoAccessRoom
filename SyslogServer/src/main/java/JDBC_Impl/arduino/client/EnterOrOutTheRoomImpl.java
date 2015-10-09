package JDBC_Impl.arduino.client;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface EnterOrOutTheRoomImpl {
    public Map<String, String> log(Integer device_id, Integer device_ip, Integer user_id, String user_name, Integer card_id, Integer user_type, Integer event_type, Boolean result_type, Date datetime);
}
