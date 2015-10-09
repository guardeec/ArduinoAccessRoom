package JDBC.admin.client.security;

import JDBC_Impl.admin.client.security.RightImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Right extends JdbcDaoSupport implements RightImpl {
    private Map<String, String> answer = new HashMap<String, String>();

    public Map<String, String> log(String host_ip, String host_mac, String login_name, Integer login_id, Integer admin_type, Boolean result_type, Integer event_type, Integer device_id, Integer role_id, Boolean access_type, Date datetime) {
        try {
            getJdbcTemplate().update("?", new Object[]{});
            answer.put("message", "Success");
        }catch (CannotGetJdbcConnectionException ex){
            answer.put("message", "Error");
        }
        return answer;
    }
}
