package JDBC;

import java.util.Map;

/**
 * Created by guardeec on 20.05.15.
 */
public interface DAO_Arduino {

    /*
    **Set the CardNumber
    **Get the [0]UserId, [1]UserName, [2]TypeOfAcces
     */
    public Map<String, String> Get(String card, String deviceId);
}
