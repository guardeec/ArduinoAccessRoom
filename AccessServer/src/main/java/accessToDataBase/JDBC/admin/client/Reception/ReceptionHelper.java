package accessToDataBase.JDBC.admin.client.Reception;

import Methods.POJO.AdminClient.Security.CardDATA;
import accessToDataBase.JDBC_Impl.admin.client.reception.ReceptionHelperImpl;
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
public class ReceptionHelper extends JdbcDaoSupport implements ReceptionHelperImpl {
    @Override
    public List<CardDATA> readCards(CardDATA cardDATA) {
        List<CardDATA> list;
        try{
            list = (List<CardDATA>) getJdbcTemplate().queryForObject(
                    "SELECT id FROM cards WHERE id NOT IN (SELECT card_id FROM employees_and_cards) AND id NOT IN (SELECT card_id FROM guests_and_cards);",
                    new Object[]{},
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
}
