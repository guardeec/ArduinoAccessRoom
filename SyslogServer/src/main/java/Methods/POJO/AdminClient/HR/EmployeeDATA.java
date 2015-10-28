package Methods.POJO.AdminClient.HR;

import JDBC.SyslogData;
import Methods.POJO.GeneralParams;
import Methods.POJO.PojoObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 27.10.15.
 */
public class EmployeeDATA extends GeneralParams {

    private Integer empl_id;
    private String empl_name;
    private Integer empl_st_id;
    private String empl_st_descr;
    private Integer sys_r_id;
    private String sys_r_title;
    private Integer card_id;

    public void setSpecificParams(Integer empl_id, String empl_name, Integer empl_st_id, String empl_st_descr, Integer sys_r_id, String sys_r_title, Integer card_id) {
        this.empl_id = empl_id;
        this.empl_name = empl_name;
        this.empl_st_id = empl_st_id;
        this.empl_st_descr = empl_st_descr;
        this.sys_r_id = sys_r_id;
        this.sys_r_title = sys_r_title;
        this.card_id = card_id;
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

    public Integer getEmpl_st_id() {
        return empl_st_id;
    }

    public void setEmpl_st_id(Integer empl_st_id) {
        this.empl_st_id = empl_st_id;
    }

    public String getEmpl_st_descr() {
        return empl_st_descr;
    }

    public void setEmpl_st_descr(String empl_st_descr) {
        this.empl_st_descr = empl_st_descr;
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

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }
}
