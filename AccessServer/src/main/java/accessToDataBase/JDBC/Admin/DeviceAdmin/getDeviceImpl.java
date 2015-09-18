package accessToDataBase.JDBC.Admin.DeviceAdmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface getDeviceImpl {
    public List<Map<String, String>> get(Integer id, String specification, String ip);
}
