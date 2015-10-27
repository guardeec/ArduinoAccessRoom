package admin.client.eventsSecurityDepartment.cards;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_addCard {
    @Test
    public void test_addCard(){
        /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String card_id,

            String datetime,

            String event_type,
            String description
         */
        Map<String, String> answer = adminClientMethods.cardLog(
                "",
                "",

                "L",
                "1",

                "true",

                "1",

                java.sql.Date.valueOf(LocalDate.now()).toString(),

                "create",
                "This_is_Test"
        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
