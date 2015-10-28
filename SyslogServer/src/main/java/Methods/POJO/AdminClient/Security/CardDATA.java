package Methods.POJO.AdminClient.Security;

import Methods.POJO.GeneralParams;

/**
 * Created by root on 27.10.15.
 */
public class CardDATA extends GeneralParams {
    private Integer card_id;

    public void setSpecificParams(Integer card_id){
        this.card_id = card_id;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }
}
