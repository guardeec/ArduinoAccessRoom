package Methods.POJO.AdminClient;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class AdminClientSessionDATA extends GeneralParams {
    private Integer cl_acc_id;
    private String cl_acc_l;
    private String acc_r;

    public void setSpecificParams(Integer cl_acc_id, String cl_acc_l, String acc_r) {
        this.cl_acc_id = cl_acc_id;
        this.cl_acc_l = cl_acc_l;
        this.acc_r = acc_r;
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
