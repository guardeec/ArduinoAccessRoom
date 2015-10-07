package accessToDataBase.JDBC.Admin.GuestCardAdmin;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 10.08.15.
 */
public class deleteExpiredCards extends JdbcDaoSupport implements deleteExpiredCardsImpl {
    @Override
    public void delete() {
        getJdbcTemplate().update("DELETE FROM guests_and_cards WHERE guest_id IN (SELECT id FROM guests JOIN guests_and_cards ON guests.id = guests_and_cards.guest_id WHERE date = current_date);");
    }
}
