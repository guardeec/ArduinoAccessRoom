package JDBC.admin.client.security;

import JDBC_Impl.admin.client.security.CardImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Card extends JdbcDaoSupport implements CardImpl {
    private Map<String, String> answer = new HashMap<String, String>();

    public Map<String, String> log(String host_ip, String host_mac, String login_name, Integer login_id, Integer admin_type, Boolean result_type, Integer event_type, Integer card_id, Date datetime) {
        try {
            getJdbcTemplate().update("?", new Object[]{});
            answer.put("message", "Success");
        }catch (CannotGetJdbcConnectionException ex){
            answer.put("message", "Error");
        }
        return answer;
    }
}
