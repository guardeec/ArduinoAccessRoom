package JDBC_Impl.admin.client.reception;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface GuestImpl {
    public Map<String, String> log(String host_ip, String host_mac, String login_name, Integer login_id, Integer admin_type, Boolean result_type, Integer event_type, String guest_name, Integer card_id, Integer guest_id, Date datetime);
}
