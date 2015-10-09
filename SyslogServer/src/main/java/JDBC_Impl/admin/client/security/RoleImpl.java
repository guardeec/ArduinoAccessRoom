package JDBC_Impl.admin.client.security;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface RoleImpl {
    public Map<String, String> log(String host_ip, String host_mac, String login_name, Integer login_id, Integer admin_type, Boolean result_type, Integer event_type, Integer role_id, String role_title, Date datetime);
}
