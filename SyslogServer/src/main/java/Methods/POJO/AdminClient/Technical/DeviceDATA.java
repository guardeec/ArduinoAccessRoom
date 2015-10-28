package Methods.POJO.AdminClient.Technical;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class DeviceDATA extends GeneralParams {
    private Integer device_id;
    private String device_ip;
    private String device_mac;
    private String device_spec;
    private Boolean device_condition;
    private String device_description;

    public void setSpecificParams(Integer device_id, String device_ip, String device_mac, String device_spec, Boolean device_condition, String device_description) {
        this.device_id = device_id;
        this.device_ip = device_ip;
        this.device_mac = device_mac;
        this.device_spec = device_spec;
        this.device_condition = device_condition;
        this.device_description = device_description;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    public String getDevice_ip() {
        return device_ip;
    }

    public void setDevice_ip(String device_ip) {
        this.device_ip = device_ip;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }

    public String getDevice_spec() {
        return device_spec;
    }

    public void setDevice_spec(String device_spec) {
        this.device_spec = device_spec;
    }

    public Boolean getDevice_condition() {
        return device_condition;
    }

    public void setDevice_condition(Boolean device_condition) {
        this.device_condition = device_condition;
    }

    public String getDevice_description() {
        return device_description;
    }

    public void setDevice_description(String device_description) {
        this.device_description = device_description;
    }
}
