package accessToDataBase.JDBC.admin.client.Security;

import Methods.POJO.AdminClient.Security.AccountDATA;
import Methods.POJO.AdminClient.Security.AccountDATA_withPassword;
import accessToDataBase.JDBC_Impl.admin.client.security.AccountImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.11.15.
 */
public class Account extends JdbcDaoSupport implements AccountImpl{

    @Override
    public AccountDATA_withPassword create(AccountDATA_withPassword accountDATA) {
        try {
            Integer account_id = getJdbcTemplate().queryForInt(
                    "INSERT INTO accounts(login,password,employee_id) VALUES (?,?,?) RETURNING id;",
                    new Object[]{
                            accountDATA.getCl_acc_l(),
                            accountDATA.getPassword(),
                            accountDATA.getEmpl_id()
                    }
            );
            String departments = accountDATA.getAcc_r();
            if (departments.contains("HR")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,1);",
                        new Object[]{account_id}
                );
            }
            if (departments.contains("Security")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,3);",
                        new Object[]{account_id}
                );
            }
            if (departments.contains("Reception")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,2);",
                        new Object[]{account_id}
                );
            }
            if (departments.contains("Technical")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,4);",
                        new Object[]{account_id}
                );
            }
            accountDATA.setCl_acc_id(account_id);
            accountDATA.setPassword(null);
            return accountDATA;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public List<AccountDATA> read(AccountDATA accountDATA) {
        List<AccountDATA> accountsList;
        try {
            accountsList = (LinkedList<AccountDATA>) getJdbcTemplate().queryForObject(
                    "SELECT accounts.id, accounts.employee_id, accounts.login,db_roles.title FROM accounts JOIN accounts_and_roles ON accounts.id = accounts_and_roles.account_id JOIN db_roles ON accounts_and_roles.db_role_id = db_roles.id WHERE accounts.id =coalesce(?, accounts.id) AND   login = coalesce(?, login) AND  employee_id = coalesce(?, employee_id);",
                    new Object[]{
                            accountDATA.getCl_acc_l(),
                            accountDATA.getCl_acc_id(),
                            accountDATA.getEmpl_name(),
                            accountDATA.getEmpl_id(),
                            null
                    },
                    new SearchRowMapper()
            );
            return accountsList;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<AccountDATA> list = new LinkedList<>();
            do {
                AccountDATA accountDATA = new AccountDATA();
                accountDATA.setSpecificParams(
                        resultSet.getInt("employee_id"),
                        null,
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        null
                );
                list.add(accountDATA);
            } while (resultSet.next());
            return list;
        }
    }

    @Override
    public AccountDATA update(AccountDATA accountDATA) {
        try {
            getJdbcTemplate().update(
                    "UPDATE accounts SET login = ? WHERE id = coalesce(?, id);",
                    new Object[]{
                            accountDATA.getCl_acc_l(),
                            accountDATA.getCl_acc_id()
                    }
            );

            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 4 AND  account_id = ?;",
                    new Object[]{accountDATA.getCl_acc_id()}
            );
            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 2 AND account_id = ?;",
                    new Object[]{accountDATA.getCl_acc_id()}
            );
            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 1 AND account_id = ?;",
                    new Object[]{accountDATA.getCl_acc_id()}
            );
            getJdbcTemplate().update(
                    "DELETE FROM accounts_and_roles WHERE db_role_id = 3 AND  account_id = ?;",
                    new Object[]{accountDATA.getCl_acc_id()}
            );
            String departments = accountDATA.getAcc_r();
            Integer account_id = accountDATA.getCl_acc_id();
            if (departments.contains("HR")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,1);",
                        new Object[]{account_id}
                );
            }
            if (departments.contains("Security")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,3);",
                        new Object[]{account_id}
                );
            }
            if (departments.contains("Reception")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,2);",
                        new Object[]{account_id}
                );
            }
            if (departments.contains("Technical")){
                getJdbcTemplate().update(
                        "INSERT INTO accounts_and_roles VALUES (?,4);",
                        new Object[]{account_id}
                );
            }
            return accountDATA;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public AccountDATA delete(AccountDATA accountDATA) {
        try{
            getJdbcTemplate().update(
                    "DELETE FROM accounts WHERE id = ?;",
                    new Object[]{
                            accountDATA.getCl_acc_id()
                    }
            );
            return accountDATA;
        }catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

}
