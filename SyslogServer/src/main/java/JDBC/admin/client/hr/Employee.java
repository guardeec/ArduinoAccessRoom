package JDBC.admin.client.hr;
import JDBC.SyslogData;
import JDBC_Impl.admin.client.hr.EmplyeeImpl;
import Methods.POJO.AdminClient.HR.EmployeeDATA;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;

/**
 * Created by root on 08.10.15.
 */
public class Employee extends JdbcDaoSupport implements EmplyeeImpl{

    public String log(EmployeeDATA object) {
        //HR DEPARTMENT EVENTS
        try{
            getJdbcTemplate().update(
                    "INSERT INTO hr_dep_events (event_id, empl_id, empl_name, empl_st_id, empl_st_descr, sys_r_id, sys_r_title, card_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
                    new Object[]{
                            object.getGeneral_event_type_id(),
                            object.getEmpl_id(),
                            object.getEmpl_name(),
                            object.getEmpl_st_id(),
                            object.getEmpl_st_descr(),
                            object.getSys_r_id(),
                            object.getSys_r_title(),
                            object.getCard_id()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
