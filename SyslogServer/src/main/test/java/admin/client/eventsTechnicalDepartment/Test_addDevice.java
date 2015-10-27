package admin.client.eventsTechnicalDepartment;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_addDevice {
    @Test
    public void test_addDevice(){
        /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String device_id,
            String device_ip,
            String device_specification,

            String datetime,
            String event_type,

            String device_mac,
            String description
         */

        Map<String, String> answer = adminClientMethods.deviceLog(
                "",
                "",

                "L",
                "1",

                "true",

                "1",
                "",
                "specification",

                Date.valueOf(LocalDate.now()).toString(),
                "create",

                "",
                "This_is_test"
        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
