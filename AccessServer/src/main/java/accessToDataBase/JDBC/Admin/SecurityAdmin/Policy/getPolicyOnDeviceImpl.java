package accessToDataBase.JDBC.Admin.SecurityAdmin.Policy;

import java.util.Map;

/**
 * Created by root on 07.08.15.
 */
public interface getPolicyOnDeviceImpl {
    public Map<String, String> get(Integer deviceId, Integer roleId);
}
