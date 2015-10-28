package JDBC.arduino.client;

import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import JDBC_Impl.arduino.client.UnauthorizedAccessImpl;
import Methods.POJO.ArduinoClient.UnuathorizedAccessDATA;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class UnauthorizedAccess extends JdbcDaoSupport implements UnauthorizedAccessImpl {

    @Override
    public String log(UnuathorizedAccessDATA unuathorizedAccess) {
        return "Success";
    }
}
