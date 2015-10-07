package accessToDataBase.JDBC.Admin.GuestCardAdmin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public interface getGuestCardHistoryImpl {
    public ArrayList<Map> get(String name, Date date);
}
