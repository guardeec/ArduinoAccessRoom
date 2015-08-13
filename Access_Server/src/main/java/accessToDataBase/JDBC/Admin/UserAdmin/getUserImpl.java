package accessToDataBase.JDBC.Admin.UserAdmin;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public interface getUserImpl {
    public ArrayList<Map> get(Integer userId, String name);
}
