package accessToDataBase.JDBC.Authorisation;

import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface getAdminTypeImpl {
    public Map<String, Boolean> get(String name, String password);
}
