package accessToDataBase.JDBC.arduino.client;

import Methods.POJO.ArduinoClient.ConnectionStatusDATA;
import accessToDataBase.JDBC_Impl.Arduino.ConnectionStatusImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by root on 02.11.15.
 */
public class ConnectionStatus extends JdbcDaoSupport implements ConnectionStatusImpl {
    @Override
    public void create(ConnectionStatusDATA connectionStatusDATA) {
        try {
            getJdbcTemplate().update(
                    "UPDATE accounts SET login = ? WHERE id = coalesce(?, id);",
                    new Object[]{
                            connectionStatusDATA.getDevice_id(),
                            connectionStatusDATA.getStatus()
                    }
            );
        } catch (CannotGetJdbcConnectionException ex){

        }
    }
}
