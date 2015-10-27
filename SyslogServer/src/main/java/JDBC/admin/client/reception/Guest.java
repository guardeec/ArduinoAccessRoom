package JDBC.admin.client.reception;
import JDBC.SyslogData;
import JDBC_Impl.addLogDAO;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Guest implements addLogDAO {


    public static Map<String, String> log(
            String host_ip,
            String host_mac,
            String login_name,
            String login_id,
            String result_type,
            String event_type,
            String guest_name,
            String card_id,
            String guest_id,
            String time_start,
            String time_end,
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
        eventSpecificParams.put("guest_id", guest_id);
        eventSpecificParams.put("guest_name", guest_name);
        eventSpecificParams.put("card_id", card_id);
        eventSpecificParams.put("time_start", time_start);
        eventSpecificParams.put("time_end", time_end);

        return DAO.log(sourceParams, eventGeneralParams, eventSpecificParams);
    }
}
