package accessToDataBase.JDBC.admin.client;

import Methods.POJO.AdminClient.AdminClientSessionDATA;
import accessToDataBase.JDBC_Impl.admin.client.AdminClientSessionImpl;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by root on 02.11.15.
 */
public class AdminClientSession extends JdbcDaoSupport implements AdminClientSessionImpl {
    @Override
    public AdminClientSessionDATA create(AdminClientSessionDATA adminClientSessionDATA) {
        return null;
    }
}
