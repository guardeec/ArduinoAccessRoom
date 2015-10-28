package JDBC.admin.client;
import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.admin.client.EnterOrOutAdminImpl;
import Methods.POJO.AdminClient.AdminClientSessionDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class EnterOrOutAdmin extends JdbcDaoSupport implements EnterOrOutAdminImpl {

    @Override
    public String log(AdminClientSessionDATA session) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO adm_cl_auth_events (event_id, cl_acc_id, cl_acc_l, acc_r) VALUES (?, ?, ?, ?);",
                    new Object[]{
                            session.getGeneral_event_type_id(),
                            session.getCl_acc_id(),
                            session.getCl_acc_l(),
                            session.getAcc_r()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
