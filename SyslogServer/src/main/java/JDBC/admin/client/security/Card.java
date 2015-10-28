package JDBC.admin.client.security;

import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.admin.client.security.CardImpl;
import Methods.POJO.AdminClient.Security.CardDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Card extends JdbcDaoSupport implements CardImpl {

    @Override
    public String log(CardDATA card) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO sec_dep_cards_events (event_id, card_id) VALUES (?, ?);",
                    new Object[]{
                            card.getGeneral_event_type_id(),
                            card.getCard_id()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
