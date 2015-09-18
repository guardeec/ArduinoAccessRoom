package accessToDataBase.JDBC.Admin.DeviceAdmin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class changeDevice extends JdbcDaoSupport implements changeDeviceImpl {
    @Override
    public Map<String, String> change(Integer id, String specification, String ip) {
        System.out.println(specification == null);
        System.out.println(ip);
        Map<String, String> message = null;

        try{
            getJdbcTemplate().update(
                    "UPDATE devices SET specification = ? WHERE id = ?;",
                    new Object[]{specification, id}
            );
            message = new HashMap<>();
            message.put("message", "Success when changing Devices");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when changing Devices");
        }


        try{
            getJdbcTemplate().update(
                    "UPDATE devices SET ip = ? WHERE id = ?;",
                    new Object[]{ip, id}
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Success when changing Devices");
        }

        return message;
    }
}
