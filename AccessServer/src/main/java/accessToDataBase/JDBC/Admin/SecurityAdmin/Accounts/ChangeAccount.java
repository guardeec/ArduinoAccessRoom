package accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public class ChangeAccount extends JdbcDaoSupport implements ChangeAccountImpl {
    @Override
    public Map<String, String> change(String login, String password, Integer id, Boolean technical, Boolean reception, Boolean hr, Boolean security) {
        Map<String, String> message;
        try{
            if (!login.isEmpty()){
                getJdbcTemplate().update(
                        "UPDATE accounts SET login = ? WHERE id = coalesce(?, id);",
                        new Object[]{login, id}
                );
            }

            if (!password.isEmpty()){
                getJdbcTemplate().update(
                        "UPDATE accounts SET password = ? WHERE id = coalesce(?, id);",
                        new Object[]{password, id}
                );
            }

            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 4 AND  account_id = ?;",
                    new Object[]{id}
            );
            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 2 AND account_id = ?;",
                    new Object[]{id}
            );
            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 1 AND account_id = ?;",
                    new Object[]{id}
            );
            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 3 AND  account_id = ?;",
                    new Object[]{id}
            );

            Boolean a=false,b=false,c=false,d=false;

            if (technical){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,4);",
                        new Object[]{id}
                );
                a=true;
            }

            if (reception){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,2);",
                        new Object[]{id}
                );
                b=true;
            }

            if (hr){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,1);",
                        new Object[]{id}
                );
                c=true;
            }

            if (security){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,3);",
                        new Object[]{id}
                );
                d=true;
            }

            message = new HashMap<>();
            message.put("message", "Success when changing account"+a+b+c+d);
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new HashMap<>();
            message.put("message", "Error when changing account");
        }
        return message;
    }
}
