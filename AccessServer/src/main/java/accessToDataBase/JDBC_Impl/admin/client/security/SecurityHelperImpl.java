package accessToDataBase.JDBC_Impl.admin.client.security;

import Methods.POJO.AdminClient.HR.EmployeeDATA;
import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.ConnectionStatusDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface SecurityHelperImpl {
    /*
    читает сотрудников для их добавления в аккаунты
     */
    public List<EmployeeDATA> readEmployers(EmployeeDATA employeeDATA);

    /*
    получение устройств в онлайне
     */
    public List<ConnectionStatusDATA> readConnectionStatus();

    /*
    получение списка устройств
     */
    public List<DeviceDATA> readDevice(DeviceDATA deviceDATA);
}
