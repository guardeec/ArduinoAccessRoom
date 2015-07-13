package POJO;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by root on 13.07.15.
 */
public class Device {
    private final SimpleStringProperty deviceId;
    private final SimpleStringProperty deviceSpecification;
    private final SimpleStringProperty deviceIp;

    public Device(String deviceId, String deviceSpecification, String deviceIp) {
        this.deviceId = new SimpleStringProperty(deviceId);
        this.deviceSpecification = new SimpleStringProperty(deviceSpecification);
        this.deviceIp = new SimpleStringProperty(deviceIp);
    }

    public String getDeviceId() {
        return deviceId.get();
    }

    public SimpleStringProperty deviceIdProperty() {
        return deviceId;
    }

    public String getDeviceSpecification() {
        return deviceSpecification.get();
    }

    public SimpleStringProperty deviceSpecificationProperty() {
        return deviceSpecification;
    }

    public String getDeviceIp() {
        return deviceIp.get();
    }

    public SimpleStringProperty deviceIpProperty() {
        return deviceIp;
    }
}
