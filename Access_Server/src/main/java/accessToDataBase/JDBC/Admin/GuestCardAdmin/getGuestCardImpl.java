package accessToDataBase.JDBC.Admin.GuestCardAdmin;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface getGuestCardImpl {
    public ArrayList<Map> get(String name, Integer guestId, Integer cardId);
}
