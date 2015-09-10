package accessToDataBase.JDBC.Admin.SecurityAdmin.Roles;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface getRoleImpl {
    public ArrayList<Map> get(Integer roleId, String role);
}
