package accessToDataBase.JDBC_Impl.admin.client.technical;

import Methods.POJO.AdminClient.Technical.DeviceDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface DeviceImpl {
    /*
    CRUD
     */
    public DeviceDATA create(DeviceDATA deviceDATA);

    public List<DeviceDATA> read(DeviceDATA deviceDATA);

    public DeviceDATA update(DeviceDATA deviceDATA);

    public DeviceDATA delete(DeviceDATA deviceDATA);
}
