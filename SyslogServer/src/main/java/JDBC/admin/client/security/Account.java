package JDBC.admin.client.security;

import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.admin.client.security.AccountImpl;
import Methods.POJO.AdminClient.Security.AccountDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Account extends JdbcDaoSupport implements AccountImpl{

    @Override
    public String log(AccountDATA account) {
        try{
            getJdbcTemplate().update(
                    "INSERT INTO sec_dep_acc_events (event_id, empl_id, empl_name, cl_acc_id, cl_acc_l, acc_r) VALUES (?,?,?,?,?,?);",
                    new Object[]{
                            account.getGeneral_event_type_id(),
                            account.getEmpl_id(),
                            account.getEmpl_name(),
                            account.getCl_acc_id(),
                            account.getCl_acc_l(),
                            account.getAcc_r()
                    }
            );
            return "Success";
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }
    }
}
