package accessToDataBase.JDBC.admin.client.HR;

import Methods.POJO.AdminClient.HR.EmployeeDATA;
import accessToDataBase.JDBC_Impl.admin.client.hr.EmployeeImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by root on 02.11.15.
 */
public class Employee extends JdbcDaoSupport implements EmployeeImpl {

    @Override
    public EmployeeDATA create(EmployeeDATA employeeDATA) {
        try {
            Integer id = getJdbcTemplate().queryForInt(
                    "INSERT INTO employees(name,employee_status_id) VALUES (?, 1) RETURNING id;",
                    new Object[]{
                            employeeDATA.getEmpl_name()
                    }
            );
            getJdbcTemplate().update(
                    "INSERT INTO employees_and_cards VALUES (?,?);",
                    new Object[]{
                            id,
                            employeeDATA.getCard_id()
                    }
            );
            getJdbcTemplate().update(
                    "INSERT INTO employees_and_roles VALUES (?,?);",
                    new Object[]{
                            id,
                            employeeDATA.getSys_r_id()
                    }
            );
            employeeDATA.setEmpl_id(id);
            return employeeDATA;

        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public List<EmployeeDATA> read(EmployeeDATA employeeDATA) {
        List<EmployeeDATA> listOfEmployers;
        try {
            listOfEmployers = (List<EmployeeDATA>) getJdbcTemplate().queryForObject(
                    "SELECT employee_id, name, system_role_id FROM employees JOIN employees_and_roles ON id = employee_id JOIN employee_status ON employee_status_id = employee_status.id WHERE employees.id = coalesce(?,employees.id) AND name = coalesce(?,name) AND system_role_id = coalesce(?, system_role_id) AND description = 'unblocked';",
                    new Object[]{
                            employeeDATA.getEmpl_id(),
                            employeeDATA.getEmpl_name(),
                            employeeDATA.getSys_r_id()
                    },
                    new SearchRowMapper()
            );
            return listOfEmployers;

        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class SearchRowMapper implements RowMapper {
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
    public EmployeeDATA update(EmployeeDATA employeeDATA) {
        try {
            if (employeeDATA.getEmpl_name()!=null){
                getJdbcTemplate().update(
                        "UPDATE employees SET name = ? WHERE id = ?;",
                        new Object[]{
                                employeeDATA.getEmpl_name(),
                                employeeDATA.getEmpl_id()
                        }
                );
            }
            if (employeeDATA.getSys_r_id()!=null){
                getJdbcTemplate().update(
                        "UPDATE employees_and_roles SET system_role_id = ? WHERE employee_id = ?;",
                        new Object[]{
                                employeeDATA.getSys_r_id(),
                                employeeDATA.getEmpl_id()
                        }
                );
            }
            return employeeDATA;

        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public EmployeeDATA delete(EmployeeDATA employeeDATA) {
        try{
            getJdbcTemplate().update(
                    "DELETE FROM employees_and_cards WHERE employee_id = ?;",
                    new Object[]{employeeDATA.getEmpl_id()}
            );
            getJdbcTemplate().update(
                    "DELETE FROM employees_and_roles WHERE employee_id = ?;",
                    new Object[]{employeeDATA.getEmpl_id()}
            );
            getJdbcTemplate().update(
                    "DELETE FROM employees WHERE id = ?;",
                    new Object[]{employeeDATA.getEmpl_id()}
            );
            return employeeDATA;

        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
}
