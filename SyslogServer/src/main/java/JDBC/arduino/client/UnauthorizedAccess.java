package JDBC.arduino.client;

import JDBC.SyslogData;
import JDBC_Impl.addLogDAO;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class UnauthorizedAccess implements addLogDAO {

    public static Map<String, String> log(
            String device_id,
            String device_ip,
            String device_mac,
            String description,
            String datetime
    ) {
        Map<String, String> sourceParams = new HashMap<>();
        sourceParams.put("type_id", SyslogData.Source_Types.arduinoClient.toString());
        sourceParams.put("ip", device_ip);
        sourceParams.put("mac", device_mac);
        sourceParams.put("device_id", device_id);
        sourceParams.put("cl_acc_id", null);
        sourceParams.put("cl_acc_l", null);

        Map<String, String> eventGeneralParams = new HashMap<>();
        eventGeneralParams.put("datetime", datetime);
        eventGeneralParams.put("event_type_id", SyslogData.Event_Types.ard_unauth.toString());
        eventGeneralParams.put("res_type", "true");
        eventGeneralParams.put("descr", description);

        Map<String, String> eventSpecificParams = new HashMap<>();

        return DAO.log(sourceParams, eventGeneralParams, eventSpecificParams);
    }
}
