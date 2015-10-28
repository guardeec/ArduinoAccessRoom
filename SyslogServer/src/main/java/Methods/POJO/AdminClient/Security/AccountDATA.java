package Methods.POJO.AdminClient.Security;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class AccountDATA extends GeneralParams {
    private Integer empl_id;
    private String empl_name;
    private Integer cl_acc_id;
    private String cl_acc_l;
    private String acc_r;

    public void setSpecificParams(Integer empl_id, String empl_name, Integer cl_acc_id, String cl_acc_l, String acc_r) {
        this.empl_id = empl_id;
        this.empl_name = empl_name;
        this.cl_acc_id = cl_acc_id;
        this.cl_acc_l = cl_acc_l;
        this.acc_r = acc_r;
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

    public Integer getCl_acc_id() {
        return cl_acc_id;
    }

    public void setCl_acc_id(Integer cl_acc_id) {
        this.cl_acc_id = cl_acc_id;
    }

    public String getCl_acc_l() {
        return cl_acc_l;
    }

    public void setCl_acc_l(String cl_acc_l) {
        this.cl_acc_l = cl_acc_l;
    }

    public String getAcc_r() {
        return acc_r;
    }

    public void setAcc_r(String acc_r) {
        this.acc_r = acc_r;
    }
}
