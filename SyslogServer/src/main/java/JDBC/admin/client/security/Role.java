package JDBC.admin.client.security;

import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.admin.client.security.RoleImpl;
import Methods.POJO.AdminClient.Security.RoleDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Role extends JdbcDaoSupport implements RoleImpl{


    @Override
    public String log(RoleDATA role) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO sec_dep_sys_r_events (event_id, sys_r_id, sys_r_title) VALUES (?, ?, ?);",
                    new Object[]{
                            role.getGeneral_event_type_id(),
                            role.getSys_r_id(),
                            role.getSys_r_title()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
