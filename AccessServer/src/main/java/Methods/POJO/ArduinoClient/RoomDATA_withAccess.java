package Methods.POJO.ArduinoClient;

import Methods.POJO.AdminClient.Security.RoleDATA;

/**
 * Created by root on 03.11.15.
 */
public class RoomDATA_withAccess extends RoomDATA {
    private Boolean access;

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    @Override
    public void setSpecificParams(Integer user_id, String user_name, Integer sys_r_id, String sys_r_title, Integer card_id) {
    }

    public void setSpecificParams(Integer user_id, String user_name, Integer sys_r_id, String sys_r_title, Integer card_id, Boolean access) {
        super.setSpecificParams(user_id, user_name, sys_r_id, sys_r_title, card_id);
        setAccess(access);
    }
}
