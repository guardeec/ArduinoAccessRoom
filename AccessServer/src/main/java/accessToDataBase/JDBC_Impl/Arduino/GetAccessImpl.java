package accessToDataBase.JDBC_Impl.Arduino;

import Methods.POJO.ArduinoClient.RoomDATA;
import Methods.POJO.ArduinoClient.RoomDATA_withAccess;

/**
 * Created by root on 02.11.15.
 */
public interface GetAccessImpl {
    public RoomDATA_withAccess read(RoomDATA_withAccess roomDATA);
}
