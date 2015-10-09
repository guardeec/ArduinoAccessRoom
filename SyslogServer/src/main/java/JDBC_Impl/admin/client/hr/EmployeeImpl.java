package JDBC_Impl.admin.client.hr;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface EmployeeImpl {
    public Map<String, String> log(String host_ip, String host_mac, String login_name, Integer login_id, Integer admin_type, Boolean result_type, Integer event_type, String employee_name, Integer employee_role_id, Integer card_id, Integer employee_id, Date datetime);
}
