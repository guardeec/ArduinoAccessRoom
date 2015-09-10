package accessToDataBase.JDBC.Admin.SecurityAdmin.Roles;

import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface changeRoleImpl {
    public Map<String, String> change(Integer roleId, String role);
}
