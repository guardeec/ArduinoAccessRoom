package JDBC.arduino.client;
import JDBC_Impl.arduino.client.LocalErrorImpl;
import Methods.POJO.ArduinoClient.LocalErrorDATA;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by root on 08.10.15.
 */
public class LocalError extends JdbcDaoSupport implements LocalErrorImpl {

    @Override
    public String log(LocalErrorDATA initialisationError) {
        return "Success";
    }
}
