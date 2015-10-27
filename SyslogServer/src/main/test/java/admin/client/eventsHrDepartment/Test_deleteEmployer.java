package admin.client.eventsHrDepartment;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_deleteEmployer {
    @Test
    public void test_deleteEmployee(){
        /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String employee_name,
            String employee_id,

            String employee_role_id,
            String card_id,

            String datetime,
            String event_type,

            String employee_st_id,
            String empl_st_descr,

            String sys_r_title,
            String description
         */
        Map<String, String> answer = adminClientMethods.hrLog(
                "",
                "",

                "L",
                "1",

                "true",

                "T",
                "1",

                "2",
                "1",

                Date.valueOf(LocalDate.now()).toString(),
                "delete",

                "1",
                "active",

                "employee",
                "This_is_Test"
        );
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
