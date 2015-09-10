package accessToDataBase.JDBC.Admin.DeviceAdmin;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface getDeviceImpl {
    public ArrayList<Map> get(Integer id, String specification, String ip);
}
