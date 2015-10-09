package arduino.client.alarmStatus;

import arduino.client.arduinoClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_initialisationError {
    @Test
    public void test_initialisationError(){
        Map<String, String> answer = arduinoClientMethods.initialisationErrorLog("test_id", "test_ip", "2", new Date(System.currentTimeMillis()).toString());
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}