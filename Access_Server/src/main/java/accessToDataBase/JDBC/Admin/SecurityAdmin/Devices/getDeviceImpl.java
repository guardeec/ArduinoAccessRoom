package accessToDataBase.JDBC.Admin.SecurityAdmin.Devices;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 07.08.15.
 */
public interface getDeviceImpl {
    public ArrayList<Map> get(String specification, String ip, Integer id);
}
