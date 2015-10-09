package arduino.client.unauthorizedAccess;

import arduino.client.arduinoClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_unauthorizedAccess {
    @Test
    public void test_unauthorizedAccess(){
        Map<String, String> answer = arduinoClientMethods.unautorizedAccessLog("test_id", "test_ip", "1", "1", new Date(System.currentTimeMillis()).toString());
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
