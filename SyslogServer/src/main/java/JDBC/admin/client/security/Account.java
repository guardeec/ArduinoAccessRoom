package JDBC.admin.client.security;

import JDBC.SyslogData;
import JDBC_Impl.addLogDAO;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Account implements addLogDAO {
    public static Map<String, String> log(
            String host_ip,
            String host_mac,
            String login_name,
            String login_id,
            String result_type,
            String event_type,
            String account_login,
            String employee_id,
            String employee_name,
            String account_id,
            String db_roles,
            String description,
            String datetime
    ) {
        Map<String, String> sourceParams = new HashMap<>();
        sourceParams.put("type_id", SyslogData.Source_Types.adminClient.toString());
        sourceParams.put("ip", host_ip);
        sourceParams.put("mac", host_mac);
        sourceParams.put("device_id", null);
        sourceParams.put("cl_acc_id", login_id);
        sourceParams.put("cl_acc_l", login_name);

        Map<String, String> eventGeneralParams = new HashMap<>();
        eventGeneralParams.put("event_type_id", event_type);
        eventGeneralParams.put("datetime", datetime);
        eventGeneralParams.put("res_type", result_type);
        eventGeneralParams.put("descr", description);

        Map<String, String> eventSpecificParams = new HashMap<>();
        eventSpecificParams.put("empl_id", employee_id);
        eventSpecificParams.put("empl_name", employee_name);
        eventSpecificParams.put("cl_acc_id", account_id);
        eventSpecificParams.put("cl_acc_l", account_login);
        eventSpecificParams.put("acc_r", db_roles);

        return DAO.log(sourceParams, eventGeneralParams, eventSpecificParams);
    }
}
