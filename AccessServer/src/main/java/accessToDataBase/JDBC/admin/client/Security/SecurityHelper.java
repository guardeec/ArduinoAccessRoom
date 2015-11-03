package accessToDataBase.JDBC.admin.client.Security;

import Methods.POJO.AdminClient.HR.EmployeeDATA;
import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.ConnectionStatusDATA;
import accessToDataBase.JDBC_Impl.admin.client.security.SecurityHelperImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public class SecurityHelper extends JdbcDaoSupport implements SecurityHelperImpl {

    @Override
    public List<EmployeeDATA> readEmployers(EmployeeDATA employeeDATA) {
        List<EmployeeDATA> listOfEmployers;
        try {
            listOfEmployers = (List<EmployeeDATA>) getJdbcTemplate().queryForObject(
                    "SELECT employee_id, name, system_role_id FROM employees JOIN employees_and_roles ON id = employee_id JOIN employee_status ON employee_status_id = employee_status.id WHERE employees.id = coalesce(?,employees.id) AND name = coalesce(?,name) AND system_role_id = coalesce(?, system_role_id) AND description = 'unblocked';",
                    new Object[]{
                            employeeDATA.getEmpl_id(),
                            employeeDATA.getEmpl_name(),
                            employeeDATA.getSys_r_id()
                    },
                    new EmployerSearchRowMapper()
            );
            return listOfEmployers;

        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class EmployerSearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<EmployeeDATA> list = new LinkedList<>();
            do {
                EmployeeDATA employeeDATA = new EmployeeDATA();
                employeeDATA.setSpecificParams(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("name"),
                        null,
                        null,
                        resultSet.getInt("system_role_id"),
                        null, //syst_title
                        null //card_id
                );
                list.add(employeeDATA);
            }while (resultSet.next());
            return list;
        }
    }

    @Override
    public List<ConnectionStatusDATA> readConnectionStatus() {
        return null;
    }

    @Override
    public List<DeviceDATA> readDevice(DeviceDATA deviceDATA) {
        List<DeviceDATA> deviceList;
        try {
            deviceList = (List<DeviceDATA>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM devices WHERE id = coalesce(?,id) AND ip = coalesce(?,ip) AND specification = coalesce(?,specification) ORDER BY id;",
                    new Object[]{

                    },
                    new DeviceSearchRowMapper()
            );
            return deviceList;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class DeviceSearchRowMapper implements RowMapper {
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
}
