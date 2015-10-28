package Methods.POJO.AdminClient.Reception;

import Methods.POJO.GeneralParams;

import java.sql.Date;

/**
 * Created by root on 27.10.15.
 */
public class GuestDATA extends GeneralParams {
    private Integer guest_id;
    private String guest_name;
    private Integer card_id;
    private Date time_start;
    private Date time_end;

    public void setSpecificParams(Integer guest_id, String guest_name, Integer card_id, Date time_start, Date time_end) {
        this.guest_id = guest_id;
        this.guest_name = guest_name;
        this.card_id = card_id;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public Integer getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(Integer guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public Date getTime_start() {
        return time_start;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public Date getTime_end() {
        return time_end;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }
}
