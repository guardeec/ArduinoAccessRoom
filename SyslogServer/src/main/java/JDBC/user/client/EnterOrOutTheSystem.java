package JDBC.user.client;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.user.client.EnterOtOutTheSystemImpl;
import Methods.POJO.UserClient.UserClientSessionDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class EnterOrOutTheSystem extends JdbcDaoSupport implements EnterOtOutTheSystemImpl {

    @Override
    public String log(UserClientSessionDATA userClientSession) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO host_ag_events (event_id, empl_id, empl_name, os_acc_id, os_acc_l) VALUES (?, ?, ?, ?, ?);",
                    new Object[]{
                            userClientSession.getGeneral_event_type_id(),
                            userClientSession.getEmpl_id(),
                            userClientSession.getEmpl_name(),
                            userClientSession.getOs_ac_id(),
                            userClientSession.getOs_acc_l()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }

}
