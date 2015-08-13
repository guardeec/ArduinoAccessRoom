package accessToDataBase.JDBC.Arduino;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public interface cashAdminDBImpl {
    public ArrayList<Map> get(Integer deviceId);
}
