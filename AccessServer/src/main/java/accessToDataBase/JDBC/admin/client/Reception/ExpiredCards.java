package accessToDataBase.JDBC.admin.client.Reception;

import accessToDataBase.JDBC_Impl.admin.client.reception.ExpiredCardsImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by root on 02.11.15.
 */
public class ExpiredCards extends JdbcDaoSupport implements ExpiredCardsImpl {
    @Override
    public void make() {
        try {
            getJdbcTemplate().update(
                    "?"
            );
        }catch (CannotGetJdbcConnectionException ex){

        }
    }
}
