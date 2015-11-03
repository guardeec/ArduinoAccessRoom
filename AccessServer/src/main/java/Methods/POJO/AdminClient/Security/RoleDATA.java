package Methods.POJO.AdminClient.Security;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class RoleDATA extends GeneralParams {
    private Integer sys_r_id;
    private String sys_r_title;

    public void setSpecificParams(Integer sys_r_id, String sys_r_title) {
        this.sys_r_id = sys_r_id;
        this.sys_r_title = sys_r_title;
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
}
