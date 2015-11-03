package accessToDataBase.JDBC.admin.client.HR;

import Methods.POJO.AdminClient.Security.CardDATA;
import Methods.POJO.AdminClient.Security.RoleDATA;
import accessToDataBase.JDBC_Impl.admin.client.hr.HRHelperImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by root on 02.11.15.
 */
public class HRHelper extends JdbcDaoSupport implements HRHelperImpl {
    @Override
    public List<RoleDATA> readRoles(RoleDATA roleDATA) {
        List<RoleDATA> list;
        try{
            list = (List<RoleDATA>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM system_roles WHERE id != 1;",
                    new Object[]{},
                    new RolesSearchRowMapper()
            );
            return list;

        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class RolesSearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<RoleDATA> list = new LinkedList<>();
            do {
                RoleDATA roleDATA = new RoleDATA();
                roleDATA.setSpecificParams(
                        resultSet.getInt("id"),
                        resultSet.getString("title")
                );
                list.add(roleDATA);
            }while (resultSet.next());
            return list;
        }
    }

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
