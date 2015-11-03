package accessToDataBase.JDBC.admin.client.Technical;

import Methods.POJO.AdminClient.Reception.GuestDATA;
import Methods.POJO.AdminClient.Technical.DeviceDATA;
import accessToDataBase.JDBC_Impl.admin.client.technical.DeviceImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.11.15.
 */
public class Device extends JdbcDaoSupport implements DeviceImpl {
    @Override
    public DeviceDATA create(DeviceDATA deviceDATA) {
        try{
            Integer id = getJdbcTemplate().queryForInt(
                    "INSERT INTO devices(specification, ip) VALUES (?, ?) RETURNING id;",
                    new Object[]{
                            deviceDATA.getDevice_spec(),
                            deviceDATA.getDevice_ip()
                    }
            );
            deviceDATA.setDevice_id(id);
            return deviceDATA;
        } catch (CannotGetJdbcConnectionException ex) {
            return null;
        }
    }

    @Override
    public List<DeviceDATA> read(DeviceDATA deviceDATA) {
        List<DeviceDATA> deviceList;
        try {
            deviceList = (List<DeviceDATA>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM devices WHERE id = coalesce(?,id) AND ip = coalesce(?,ip) AND specification = coalesce(?,specification) ORDER BY id;",
                    new Object[]{

                    },
                    new SearchRowMapper()
            );
            return deviceList;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<DeviceDATA> list = new LinkedList<>();
            do {
                DeviceDATA deviceDATA = new DeviceDATA();
                deviceDATA.setSpecificParams(
                        resultSet.getInt("id"),
                        resultSet.getString("ip"),
                        null,
                        resultSet.getString("specification"),
                        null,
                        null
                );
                list.add(deviceDATA);
            }while (resultSet.next());
            return list;
        }
    }

    @Override
    public DeviceDATA update(DeviceDATA deviceDATA) {
        try {
            getJdbcTemplate().update(
                    "UPDATE devices SET specification = ? WHERE id = ?;",
                    new Object[]{
                            deviceDATA.getDevice_spec(),
                            deviceDATA.getDevice_id()
                    }
            );
            getJdbcTemplate().update(
                    "UPDATE devices SET ip = ? WHERE id = ?;",
                    new Object[]{
                            deviceDATA.getDevice_ip(),
                            deviceDATA.getDevice_id()
                    }
            );
            return deviceDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }

    }

    @Override
    public DeviceDATA delete(DeviceDATA deviceDATA) {
        try {
            getJdbcTemplate().update(
                    "DELETE FROM devices WHERE id = ?;",
                    new Object[]{
                            deviceDATA.getDevice_id()
                    }
            );
            return deviceDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
}
