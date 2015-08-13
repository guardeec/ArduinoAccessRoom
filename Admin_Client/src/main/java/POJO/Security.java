package POJO;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by root on 15.07.15.
 */
public class Security {

    private final SimpleStringProperty RoleId;
    private final SimpleStringProperty Role;
    private final SimpleStringProperty DeviceId;
    private final SimpleStringProperty Device;

    public Security(String roleId, String role, String deviceId, String device) {
        RoleId = new SimpleStringProperty(roleId);
        Role = new SimpleStringProperty(role);
        DeviceId = new SimpleStringProperty(deviceId);
        Device = new SimpleStringProperty(device);
    }

    public String getRoleId() {
        return RoleId.get();
    }

    public SimpleStringProperty roleIdProperty() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        this.RoleId.set(roleId);
    }

    public String getRole() {
        return Role.get();
    }

    public SimpleStringProperty roleProperty() {
        return Role;
    }

    public void setRole(String role) {
        this.Role.set(role);
    }

    public String getDeviceId() {
        return DeviceId.get();
    }

    public SimpleStringProperty deviceIdProperty() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        this.DeviceId.set(deviceId);
    }

    public String getDevice() {
        return Device.get();
    }

    public SimpleStringProperty deviceProperty() {
        return Device;
    }

    public void setDevice(String device) {
        this.Device.set(device);
    }
}
