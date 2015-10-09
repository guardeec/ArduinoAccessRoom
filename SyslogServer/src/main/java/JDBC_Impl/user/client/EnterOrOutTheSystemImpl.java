package JDBC_Impl.user.client;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface EnterOrOutTheSystemImpl {
    public Map<String, String> log(String host_ip, String host_mac, Integer user_id, Integer event_type, Date datetime);
}
