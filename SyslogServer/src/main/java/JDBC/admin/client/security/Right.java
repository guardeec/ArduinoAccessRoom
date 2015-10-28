package JDBC.admin.client.security;

import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.admin.client.security.RightImpl;
import Methods.POJO.AdminClient.Security.RightDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Right extends JdbcDaoSupport implements RightImpl{


    @Override
    public String log(RightDATA right) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO sec_dep_acs_r_events (event_id, device_id, device_spec, sys_r_id, sys_r_title, access) VALUES (?, ?, ?, ?, ?, ?);",
                    new Object[]{
                            right.getGeneral_event_type_id(),
                            right.getDevice_id(),
                            right.getDevice_spec(),
                            right.getSys_r_id(),
                            right.getSys_r_title(),
                            right.getAccess()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
