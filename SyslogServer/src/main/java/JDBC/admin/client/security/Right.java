package JDBC.admin.client.security;

import JDBC.SyslogData;
import JDBC_Impl.addLogDAO;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Right implements addLogDAO {

    public static Map<String, String> log(
            String host_ip,
            String host_mac,
            String login_name,
            String login_id,
            String result_type,
            String event_type,
            String device_id,
            String device_spec,
            String role_id,
            String role_title,
            String access_type,
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
        eventSpecificParams.put("device_id", device_id);
        eventSpecificParams.put("device_spec", device_spec);
        eventSpecificParams.put("sys_r_id", role_id);
        eventSpecificParams.put("sys_r_title", role_title);
        eventSpecificParams.put("sys_r_access", access_type);

        return DAO.log(sourceParams, eventGeneralParams, eventSpecificParams);
    }
}
