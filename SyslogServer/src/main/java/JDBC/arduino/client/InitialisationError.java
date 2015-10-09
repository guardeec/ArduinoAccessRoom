package JDBC.arduino.client;

import JDBC_Impl.arduino.client.InitialisationErrorImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class InitialisationError extends JdbcDaoSupport implements InitialisationErrorImpl {
    private Map<String, String> answer = new HashMap<String, String>();

    public Map<String, String> log(Integer device_id, String device_ip, Integer error_type, Date datetime) {
        try {
            getJdbcTemplate().update("?", new Object[]{});
            answer.put("message", "Success");
        }catch (CannotGetJdbcConnectionException ex){
            answer.put("message", "Error");
        }
        return answer;
    }
}
