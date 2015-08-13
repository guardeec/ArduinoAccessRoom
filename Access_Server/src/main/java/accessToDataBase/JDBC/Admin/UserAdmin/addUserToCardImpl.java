package accessToDataBase.JDBC.Admin.UserAdmin;

import java.util.Map;

/**
 * Created by root on 07.08.15.
 */
public interface addUserToCardImpl {
    public Map<String, String> add(Integer userId, Integer cardId, Integer roleId);
}
