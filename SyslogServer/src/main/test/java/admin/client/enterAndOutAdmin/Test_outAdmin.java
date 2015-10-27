package admin.client.enterAndOutAdmin;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_outAdmin {
    @Test
    public void test_outAdmin(){
         /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,
            String datetime,

            String event_type,

            String cl_acc_id,
            String cl_acc_l,

            String acc_r,
            String description
         */
        Map<String, String> answer = adminClientMethods.enterOrOutAdminLog(
                "",
                "",

                "L",
                "1",

                "true",
                Date.valueOf(LocalDate.now()).toString(),

                "logout",

                "1",
                "L",

                "hr",
                "This_is_test"
        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
