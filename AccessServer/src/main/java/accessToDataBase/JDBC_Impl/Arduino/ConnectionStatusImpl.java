package accessToDataBase.JDBC_Impl.Arduino;

import Methods.POJO.ArduinoClient.ConnectionStatusDATA;

/**
 * Created by root on 02.11.15.
 */
public interface ConnectionStatusImpl {
    /*
    получение статуса подключения
     */
    public void create(ConnectionStatusDATA connectionStatusDATA);

}
