package accessToDataBase.JDBC_Impl.Arduino;

import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.AdminDBCashDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface CashAdminDBImpl {
    /*
    кэширование БД администраторов
     */
    public List<AdminDBCashDATA> read(DeviceDATA deviceDATA);
}
