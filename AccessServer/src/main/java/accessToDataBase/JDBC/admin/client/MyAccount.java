package accessToDataBase.JDBC.admin.client;

import Methods.POJO.AdminClient.Security.AccountDATA;
import Methods.POJO.AdminClient.Security.AccountDATA_withPassword;
import accessToDataBase.JDBC_Impl.admin.client.MyAccountImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 02.11.15.
 */
public class MyAccount extends JdbcDaoSupport implements MyAccountImpl {
    @Override
    public AccountDATA_withPassword read(AccountDATA_withPassword accountDATA) {
        try {
            accountDATA = (AccountDATA_withPassword) getJdbcTemplate().queryForObject(
                    "SELECT accounts.id, accounts.employee_id, accounts.login,db_roles.title FROM accounts JOIN accounts_and_roles ON accounts.id = accounts_and_roles.account_id JOIN db_roles ON accounts_and_roles.db_role_id = db_roles.id WHERE accounts.id =coalesce(?, accounts.id) AND   login = coalesce(?, login) AND  employee_id = coalesce(?, employee_id);",
                    new Object[]{
                            accountDATA.getCl_acc_l(),
                            accountDATA.getCl_acc_id(),
                            accountDATA.getEmpl_name(),
                            accountDATA.getEmpl_id(),
                            accountDATA.getPassword()
                    },
                    new SearchRowMapper()
            );
            return accountDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            AccountDATA accountDATA = new AccountDATA();
            accountDATA.setSpecificParams(
                    resultSet.getInt("employee_id"),
                    null,
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    null
            );
            return accountDATA;
        }
    }

    @Override
    public AccountDATA_withPassword update(AccountDATA_withPassword accountDATA) {
        try {
            getJdbcTemplate().update(
                    "UPDATE accounts SET login = ? WHERE id = coalesce(?, id);",
                    new Object[]{
                            accountDATA.getCl_acc_id(),
                            accountDATA.getPassword()
                    }
            );
            accountDATA.setPassword(null);
            return accountDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
}
