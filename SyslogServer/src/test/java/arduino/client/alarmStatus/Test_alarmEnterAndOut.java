package arduino.client.alarmStatus;

import arduino.client.arduinoClientMethods;
import org.junit.Assert;
import org.junit.Test;
import user.client.userClientMethods;

import java.io.File;
import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_alarmEnterAndOut {
    @Test
    public void test_alarmEnterAndOut(){
        Map<String, String> answer = arduinoClientMethods.alarmEneterAndOutLog(new File("aa"));
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
