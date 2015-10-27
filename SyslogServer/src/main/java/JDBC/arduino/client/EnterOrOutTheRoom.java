package JDBC.arduino.client;
import JDBC.SyslogData;
import JDBC_Impl.addLogDAO;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class EnterOrOutTheRoom implements addLogDAO {

    public static Map<String, String> log(
            String device_id,
            String device_ip,
            String device_mac,
            String user_id,
            String user_name,
            String card_id,
            String user_type_id,
            String user_type,
            String event_type,
            String result_type,
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
        eventGeneralParams.put("event_type_id", event_type);
        eventGeneralParams.put("datetime", datetime);
        eventGeneralParams.put("res_type", result_type);
        eventGeneralParams.put("descr", description);

        Map<String, String> eventSpecificParams = new HashMap<>();
        eventSpecificParams.put("user_id", user_id);
        eventSpecificParams.put("user_name", user_name);
        eventSpecificParams.put("sys_r_id", user_type_id);
        eventSpecificParams.put("sys_r_title", user_type);
        eventSpecificParams.put("card_id", card_id);

        return DAO.log(sourceParams, eventGeneralParams, eventSpecificParams);
    }
}
