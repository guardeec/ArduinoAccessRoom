package Methods.POJO.ArduinoClient;

import Methods.POJO.AdminClient.Technical.DeviceDATA;

/**
 * Created by root on 02.11.15.
 */
public class ConnectionStatusDATA extends DeviceDATA {
    Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
