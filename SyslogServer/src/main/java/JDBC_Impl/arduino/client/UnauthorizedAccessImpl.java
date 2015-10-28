package JDBC_Impl.arduino.client;

import Methods.POJO.AdminClient.AdminClientSessionDATA;
import Methods.POJO.ArduinoClient.UnuathorizedAccessDATA;

/**
 * Created by root on 28.10.15.
 */
public interface UnauthorizedAccessImpl {
    public String log(UnuathorizedAccessDATA unuathorizedAccess);
}
