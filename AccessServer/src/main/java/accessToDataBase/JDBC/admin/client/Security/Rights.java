package accessToDataBase.JDBC.admin.client.Security;

import Methods.POJO.AdminClient.Security.RightDATA;
import accessToDataBase.JDBC_Impl.admin.client.security.RightsImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.11.15.
 */
public class Rights extends JdbcDaoSupport implements RightsImpl {
    @Override
    public List<RightDATA> readAllRights(RightDATA rightDATA) {
        List<RightDATA> rightDATAList;
        try {
            rightDATAList = (List<RightDATA>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM access_rights WHERE system_role_id = coalesce(?, system_role_id) AND device_id = coalesce(?, device_id) AND access = coalesce(NULL, access);",
                    new Object[]{
                            rightDATA.getSys_r_id(),
                            rightDATA.getSys_r_title()
                    },
                    new SearchRowMapper()
            );
            return rightDATAList;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<RightDATA> list = new LinkedList<>();
            do {
                RightDATA rightDATA = new RightDATA();
                rightDATA.setSpecificParams(
                        resultSet.getInt("device_id"),
                        resultSet.getString("device_specification"),
                        resultSet.getInt("sys_r_id"),
                        resultSet.getString("sys_r_title"),
                        resultSet.getBoolean("access")
                );
                list.add(rightDATA);
            }while (resultSet.next());
            return list;
        }
    }

    @Override
    public RightDATA read(RightDATA rightDATA) {
        try {
            rightDATA = (RightDATA) getJdbcTemplate().queryForObject(
                    "SELECT * FROM access_rights WHERE system_role_id = coalesce(?, system_role_id) AND device_id = coalesce(?, device_id) AND access = coalesce(NULL, access);",
                    new Object[]{
                            rightDATA.getDevice_id(),
                            rightDATA.getSys_r_id()
                    },
                    new OnDeviceSearchRowMapper()
            );
            return rightDATA;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class OnDeviceSearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            RightDATA rightDATA = new RightDATA();
            rightDATA.setSpecificParams(
                    resultSet.getInt("device_id"),
                    resultSet.getString("device_specification"),
                    resultSet.getInt("sys_r_id"),
                    resultSet.getString("sys_r_title"),
                    resultSet.getBoolean("access")
            );
            return rightDATA;
        }
    }

    @Override
    public RightDATA update(RightDATA rightDATA) {
        try {
            getJdbcTemplate().update(
                    "UPDATE access_rights SET access = ? WHERE system_role_id = ? AND device_id = ?;",
                    new Object[]{
                            rightDATA.getSys_r_id(),
                            rightDATA.getDevice_id(),
                            rightDATA.getAccess()
                    }
            );
            return rightDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }


}
