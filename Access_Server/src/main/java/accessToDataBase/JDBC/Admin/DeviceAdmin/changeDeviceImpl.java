package accessToDataBase.JDBC.Admin.DeviceAdmin;

import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface changeDeviceImpl {
    public Map<String, String> change(Integer id, String specification, String ip);
}
