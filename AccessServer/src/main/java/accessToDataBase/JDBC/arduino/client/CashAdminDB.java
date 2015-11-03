package accessToDataBase.JDBC.arduino.client;

import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.AdminDBCashDATA;
import accessToDataBase.JDBC_Impl.Arduino.CashAdminDBImpl;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class CashAdminDB extends JdbcDaoSupport implements CashAdminDBImpl {
    @Override
    public List<AdminDBCashDATA> read(DeviceDATA deviceDATA) {
        List<AdminDBCashDATA> adminDBCashDATAList;
        try {
            adminDBCashDATAList = (List<AdminDBCashDATA>) getJdbcTemplate().queryForObject(
                    "SELECT employees.id, employees.name, cards.number FROM employees JOIN employees_and_cards ON employees.id = employees_and_cards.employee_id JOIN cards ON cards.id = employees_and_cards.card_id JOIN employees_and_roles ON employees.id = employees_and_roles.employee_id JOIN system_roles ON employees_and_roles.system_role_id = system_roles.id JOIN access_rights ON system_roles.id = access_rights.system_role_id WHERE employee_status_id = 1 AND access_rights.system_role_id = 3 AND device_id = ?;",
                    new Object[]{
                            deviceDATA.getDevice_id()
                    },
                    new SearchRowMapper()
            );
            return adminDBCashDATAList;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        } catch (EmptyResultDataAccessException ex){
            adminDBCashDATAList = new LinkedList<>();
            adminDBCashDATAList.add(new AdminDBCashDATA());
            return adminDBCashDATAList;
        }
    }
    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<AdminDBCashDATA> list = new LinkedList<>();
            do {
                AdminDBCashDATA adminDBCashDATA = new AdminDBCashDATA();
                adminDBCashDATA.setSpecificParams(
                        resultSet.getInt(""),
                        resultSet.getString(""),
                        resultSet.getInt(""),
                        resultSet.getString(""),
                        resultSet.getInt("")
                );
                list.add(adminDBCashDATA);
            }while (resultSet.next());
            return list;
        }
    }
}
