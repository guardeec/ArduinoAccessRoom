package accessToDataBase.JDBC.Admin.SecurityAdmin.Policy;

import java.util.Map;

/**
 * Created by root on 07.08.15.
 */
public interface changePolicyOnDeviceImpl {
    public Map<String, String> change(Integer deviceId, Integer roleId, Boolean access);
}
