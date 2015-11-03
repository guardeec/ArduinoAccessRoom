package accessToDataBase.JDBC_Impl.admin.client.hr;

import Methods.POJO.AdminClient.HR.EmployeeDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface EmployeeImpl {
    public EmployeeDATA create(EmployeeDATA employeeDATA);

    public List<EmployeeDATA> read(EmployeeDATA employeeDATA);

    public EmployeeDATA update(EmployeeDATA employeeDATA);

    public EmployeeDATA delete(EmployeeDATA employeeDATA);
}
