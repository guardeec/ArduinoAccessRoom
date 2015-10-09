package JDBC_Impl.admin.client.technical;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface DeviceImpl {
    public Map<String, String> log(String host_ip, String host_mac, String login_name, Integer login_id, Integer admin_type , Boolean result_type, Integer event_type , String device_ip, String device_specification, Integer device_id , Date datetime);
}
