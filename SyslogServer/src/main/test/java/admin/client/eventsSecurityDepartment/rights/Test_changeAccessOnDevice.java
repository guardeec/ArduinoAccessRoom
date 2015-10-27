package admin.client.eventsSecurityDepartment.rights;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_changeAccessOnDevice {
    @Test
    public void test_changeAccessOnDevice(){
        /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String device_id,
            String role_id,

            String access_type,

            String datetime,

            String device_spec,
            String role_title,
            String description
         */

        Map<String, String> answer = adminClientMethods.changeAccesOnDeviceLog(
                "",
                "",

                "L",
                "1",

                "true",

                "1",
                "1",

                "false",

                java.sql.Date.valueOf(LocalDate.now()).toString(),

                "spec",
                "a",
                "This_is_Test"

        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
