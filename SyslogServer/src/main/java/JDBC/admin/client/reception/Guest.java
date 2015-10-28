package JDBC.admin.client.reception;
import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.admin.client.reception.GuestImpl;
import Methods.POJO.AdminClient.Reception.GuestDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Guest extends JdbcDaoSupport implements GuestImpl {

    @Override
    public String log(GuestDATA guest) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO rec_dep_events (event_id, guest_id, guest_name, card_id, time_start, time_end) VALUES (?, ?, ?, ?, ?, ?);",
                    new Object[]{
                            guest.getGeneral_event_type_id(),
                            guest.getGuest_id(),
                            guest.getGuest_name(),
                            guest.getCard_id(),
                            guest.getTime_start(),
                            guest.getTime_end()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
