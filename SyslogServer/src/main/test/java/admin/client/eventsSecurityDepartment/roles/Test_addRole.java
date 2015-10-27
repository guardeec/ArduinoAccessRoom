package admin.client.eventsSecurityDepartment.roles;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_addRole {
    @Test
    public void test_addRole(){
        /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String role_id,
            String role_title,

            String datetime,

            String event_type,
            String description
         */

        Map<String, String> answer = adminClientMethods.roleLog(
                "",
                "",

                "L",
                "1",

                "true",

                "1",
                "employee",

                Date.valueOf(LocalDate.now()).toString(),

                "create",
                "Test_is_Test"
        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
