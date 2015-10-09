package JDBC.arduino.client;

import JDBC_Impl.arduino.client.EnterOrOutTheRoomImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class EnterOrOutTheRoom extends JdbcDaoSupport implements EnterOrOutTheRoomImpl {
    private Map<String, String> answer = new HashMap<String, String>();

    public Map<String, String> log(Integer device_id, Integer device_ip, Integer user_id, String user_name, Integer card_id, Integer user_type, Integer event_type, Boolean result_type, Date datetime) {
        try {
            getJdbcTemplate().update("?", new Object[]{});
            answer.put("message", "Success");
        }catch (CannotGetJdbcConnectionException ex){
            answer.put("message", "Error");
        }
        return answer;
    }
}
