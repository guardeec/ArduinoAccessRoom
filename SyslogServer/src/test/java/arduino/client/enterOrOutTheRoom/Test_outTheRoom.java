package arduino.client.enterOrOutTheRoom;

import arduino.client.arduinoClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_outTheRoom {
    @Test
    public void test_enterTheRoom(){
        Map<String, String> answer = arduinoClientMethods.enterOrOutTheRoomLog("test_id", "test_ip", "test_id", "test_name", "test_id", "test_type", "1", new Date(System.currentTimeMillis()).toString(), "0");
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
