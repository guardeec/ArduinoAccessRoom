package arduino.client.enterOrOutTheRoom;

import arduino.client.arduinoClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_outTheRoom {
    @Test
    public void test_enterTheRoom(){
        /*
            String device_id,
            String device_ip,

            String user_id,
            String user_name,

            String card_id,
            String user_type ,

            String result_type,

            String datetime,
            String event_type,

            String device_mac,
            String user_type_id,
            String description
         */
        Map<String, String> answer = arduinoClientMethods.enterOrOutTheRoomLog(
                "1",
                "",

                "1",
                "T",

                "1",
                "admin",

                "true",

                Date.valueOf(LocalDate.now()).toString(),
                "logout",

                "",
                "1",
                "This_is_Test"
        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
