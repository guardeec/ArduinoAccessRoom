package accessToDataBase.JDBC.Admin.GuestCardAdmin;

import java.sql.Time;
import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface changeGuestCardImpl {
    public Map<String, String> change(Integer id, String name, Time startTime, Time endTime, Date date);
}
