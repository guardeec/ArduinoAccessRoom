package accessToDataBase.JDBC.admin.client.Security;

import Methods.POJO.AdminClient.Security.CardDATA;
import Methods.POJO.AdminClient.Security.CardDATA_withCardNumber;
import accessToDataBase.JDBC_Impl.admin.client.security.CardsImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public class Cards extends JdbcDaoSupport implements CardsImpl {
    @Override
    public CardDATA_withCardNumber create(CardDATA_withCardNumber cardDATA) {
        try {
            Integer id = getJdbcTemplate().queryForInt(
                    "INSERT INTO cards(number) VALUES (?) RETURNING id;",
                    new Object[]{
                            cardDATA.getCardNumber()
                    }
            );
            cardDATA.setCardNumber(null);
            cardDATA.setCard_id(id);
            return cardDATA;
        } catch (CannotGetJdbcConnectionException ex) {
            return null;
        }
    }

    @Override
    public List<CardDATA> read(CardDATA cardDATA) {
        List<CardDATA> list;
        try{
            list = (List<CardDATA>) getJdbcTemplate().queryForObject(
                    "?",
                    new Object[]{
                            cardDATA.getCard_id()
                    },
                    new CardsSearchRowMapper()
            );
            return list;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class CardsSearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<CardDATA> list = new LinkedList<>();
            do {
                CardDATA cardDATA = new CardDATA();
                cardDATA.setSpecificParams(
                        resultSet.getInt("id")
                );
                list.add(cardDATA);
            }while (resultSet.next());
            return list;
        }
    }

    @Override
    public CardDATA delete(CardDATA cardDATA) {
        try{
            getJdbcTemplate().update(
                    "DELETE FROM cards WHERE id = ?;",
                    new Object[]{
                            cardDATA.getCard_id()
                    }
            );
            return cardDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return  null;
        }
    }
}
