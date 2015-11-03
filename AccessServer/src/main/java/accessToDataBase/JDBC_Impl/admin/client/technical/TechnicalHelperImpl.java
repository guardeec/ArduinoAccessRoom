package accessToDataBase.JDBC_Impl.admin.client.technical;

import Methods.POJO.ArduinoClient.ConnectionStatusDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface TechnicalHelperImpl {
    /*
    получение устройств в онлайне
     */
    public List<ConnectionStatusDATA> read();
}
