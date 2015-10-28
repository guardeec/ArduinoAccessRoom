package JDBC.admin.client.technical;

import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.admin.client.technical.DeviceImpl;
import Methods.POJO.AdminClient.Technical.DeviceDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Device extends JdbcDaoSupport implements DeviceImpl {

    @Override
    public String log(DeviceDATA device) {
        try{
            getJdbcTemplate().update(
                    "TECHNICAL DEPARTMENT EVENTS INSERT INTO tech_dep_events (event_id, device_id, device_ip, device_mac, device_spec, device_st_cond, device_st_descr)VALUES (?, ?, ?, ?, ?, ?, ?);",
                    new Object[]{
                            device.getGeneral_event_type_id(),
                            device.getDevice_id(),
                            device.getDevice_ip(),
                            device.getDevice_mac(),
                            device.getDevice_spec(),
                            device.getDevice_condition(),
                            device.getDevice_description()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }


    }
}
