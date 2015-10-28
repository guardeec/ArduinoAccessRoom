package Methods.POJO.UserClient;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class UserClientSessionDATA extends GeneralParams {
    private Integer empl_id;
    private String empl_name;
    private Integer os_ac_id;
    private String os_acc_l;

    public void setSpecificParams(Integer empl_id, String empl_name, Integer os_ac_id, String os_acc_l) {
        this.empl_id = empl_id;
        this.empl_name = empl_name;
        this.os_ac_id = os_ac_id;
        this.os_acc_l = os_acc_l;
    }

    public Integer getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(Integer empl_id) {
        this.empl_id = empl_id;
    }

    public String getEmpl_name() {
        return empl_name;
    }

    public void setEmpl_name(String empl_name) {
        this.empl_name = empl_name;
    }

    public Integer getOs_ac_id() {
        return os_ac_id;
    }

    public void setOs_ac_id(Integer os_ac_id) {
        this.os_ac_id = os_ac_id;
    }

    public String getOs_acc_l() {
        return os_acc_l;
    }

    public void setOs_acc_l(String os_acc_l) {
        this.os_acc_l = os_acc_l;
    }
}
