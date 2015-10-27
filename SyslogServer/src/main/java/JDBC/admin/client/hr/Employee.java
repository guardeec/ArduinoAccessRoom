package JDBC.admin.client.hr;
import JDBC.SyslogData;
import JDBC_Impl.addLogDAO;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Employee implements addLogDAO {

    public static Map<String, String> log(
            String host_ip,
            String host_mac,
            String login_name,
            String login_id,
            String result_type,
            String event_type,
            String employee_name,
            String employee_role_id,
            String employee_st_id,
            String empl_st_descr,
            String sys_r_title,
            String card_id,
            String employee_id,
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
        eventSpecificParams.put("empl_st_id", employee_st_id);
        eventSpecificParams.put("empl_st_descr", empl_st_descr);
        eventSpecificParams.put("sys_r_id", employee_role_id);
        eventSpecificParams.put("sys_r_title", sys_r_title);
        eventSpecificParams.put("empl_id", employee_id);
        eventSpecificParams.put("card_id", card_id);


        return DAO.log(sourceParams, eventGeneralParams, eventSpecificParams);
    }
}
