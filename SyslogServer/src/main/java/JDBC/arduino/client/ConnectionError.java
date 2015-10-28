package JDBC.arduino.client;
import JDBC_Impl.arduino.client.ConnectionErrorImpl;
import Methods.POJO.ArduinoClient.ConnectionErrorDATA;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by root on 08.10.15.
 */
public class ConnectionError extends JdbcDaoSupport implements ConnectionErrorImpl {

    @Override
    public String log(ConnectionErrorDATA workError) {
        return "Success";
    }
}
