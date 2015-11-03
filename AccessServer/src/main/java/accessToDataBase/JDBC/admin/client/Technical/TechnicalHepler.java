package accessToDataBase.JDBC.admin.client.Technical;

import Methods.POJO.ArduinoClient.ConnectionStatusDATA;
import accessToDataBase.JDBC_Impl.admin.client.technical.TechnicalHelperImpl;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public class TechnicalHepler extends JdbcDaoSupport implements TechnicalHelperImpl {
    @Override
    public List<ConnectionStatusDATA> read() {
        return null;
    }
}
