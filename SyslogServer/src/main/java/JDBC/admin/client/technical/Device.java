package JDBC.admin.client.technical;

import JDBC_Impl.admin.client.technical.DeviceImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Device extends JdbcDaoSupport implements DeviceImpl {
    private Map<String, String> answer = new HashMap<String, String>();

    public Map<String, String> log(String host_ip, String host_mac, String login_name, Integer login_id, Integer admin_type, Boolean result_type, Integer event_type, String device_ip, String device_specification, Integer device_id, Date datetime) {
        try {
            getJdbcTemplate().update("?", new Object[]{});
            answer.put("message", "Success");
        }catch (CannotGetJdbcConnectionException ex){
            answer.put("message", "Error");
        }
        return answer;
    }
}
