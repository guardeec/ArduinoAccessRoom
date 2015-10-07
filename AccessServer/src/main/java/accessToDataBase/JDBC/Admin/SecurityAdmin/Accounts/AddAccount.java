package accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public class AddAccount extends JdbcDaoSupport implements AddAccountImpl {
    @Override
    public Map<String, String> add(String login, String password, Integer id, Boolean technical, Boolean reception, Boolean hr, Boolean security) {
        Map<String, String> message;
        try{

            Integer account_id = getJdbcTemplate().queryForInt(
                    "INSERT INTO accounts(login,password,employee_id) VALUES (?,?,?) RETURNING id;",
                    new Object[]{login, password, id}
            );

            if (technical){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,4);",
                        new Object[]{account_id}
                );
            }

            if (reception){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,2);",
                        new Object[]{account_id}
                );
            }

            if (hr){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,1);",
                        new Object[]{account_id}
                );
            }

            if (security){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,3);",
                        new Object[]{account_id}
                );
            }

            message = new HashMap<>();
            message.put("message", "Success when adding account");
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when adding account");
        }
        return message;
    }

    private class SearchRowMapper {
    }
}
