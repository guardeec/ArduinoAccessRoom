package accessToDataBase.JDBC.Admin.UserAdmin;

import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface changeUserImpl {
    public Map<String, String> change(Integer id, String name, Integer roleId);
}
