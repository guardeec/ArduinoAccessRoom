package JDBC.admin.client.technical;

import JDBC.SyslogData;
import JDBC_Impl.addLogDAO;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Device implements addLogDAO {

    public static Map<String, String> log(
            String host_ip,
            String host_mac,
            String login_name,
            String login_id,
            String result_type,
            String event_type,
            String device_ip,
            String device_specification,
            String device_id,
            String device_mac,
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
        eventSpecificParams.put("device_ip", device_ip);
        eventSpecificParams.put("device_mac", device_mac);
        eventSpecificParams.put("device_spec", device_specification);

        return DAO.log(sourceParams, eventGeneralParams, eventSpecificParams);
    }
}
