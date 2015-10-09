package JDBC_Impl.arduino.client;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface InitialisationErrorImpl {
    public Map<String, String> log(Integer device_id, String device_ip, Integer error_type, Date datetime);
}
