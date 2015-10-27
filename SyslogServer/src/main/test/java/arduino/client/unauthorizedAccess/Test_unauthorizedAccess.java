package arduino.client.unauthorizedAccess;

import arduino.client.arduinoClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_unauthorizedAccess {
    @Test
    public void test_unauthorizedAccess(){
        /*
            String device_id,
            String device_ip,
            String device_mac,

            String description,
            String datetime
         */
        Map<String, String> answer = arduinoClientMethods.unautorizedAccessLog(
                "1",
                "",
                "",

                "This_is_Test",
                Date.valueOf(LocalDate.now()).toString()
        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
