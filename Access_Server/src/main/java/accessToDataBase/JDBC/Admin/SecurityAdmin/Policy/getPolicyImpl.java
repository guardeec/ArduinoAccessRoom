package accessToDataBase.JDBC.Admin.SecurityAdmin.Policy;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface getPolicyImpl {
    public ArrayList<Map> get(Integer deviceId, Integer roleId);
}
