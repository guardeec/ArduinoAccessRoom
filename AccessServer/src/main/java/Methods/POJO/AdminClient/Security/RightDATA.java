package Methods.POJO.AdminClient.Security;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class RightDATA extends GeneralParams {
    private Integer device_id;
    private String device_spec;
    private Integer sys_r_id;
    private String sys_r_title;
    private Boolean access;

    public void setSpecificParams(Integer device_id, String device_spec, Integer sys_r_id, String sys_r_title, Boolean access) {
        this.device_id = device_id;
        this.device_spec = device_spec;
        this.sys_r_id = sys_r_id;
        this.sys_r_title = sys_r_title;
        this.access = access;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    public String getDevice_spec() {
        return device_spec;
    }

    public void setDevice_spec(String device_spec) {
        this.device_spec = device_spec;
    }

    public Integer getSys_r_id() {
        return sys_r_id;
    }

    public void setSys_r_id(Integer sys_r_id) {
        this.sys_r_id = sys_r_id;
    }

    public String getSys_r_title() {
        return sys_r_title;
    }

    public void setSys_r_title(String sys_r_title) {
        this.sys_r_title = sys_r_title;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }
}
