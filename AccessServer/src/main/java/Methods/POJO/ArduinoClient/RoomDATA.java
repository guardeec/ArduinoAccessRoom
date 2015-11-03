package Methods.POJO.ArduinoClient;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class RoomDATA extends GeneralParams {
    private Integer user_id;
    private String user_name;
    private Integer sys_r_id;
    private String sys_r_title;
    private Integer card_id;

    public void setSpecificParams(Integer user_id, String user_name, Integer sys_r_id, String sys_r_title, Integer card_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.sys_r_id = sys_r_id;
        this.sys_r_title = sys_r_title;
        this.card_id = card_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
