package admin.client.eventsReceptionDepartment;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_deleteGuest {
    //@Test
    public void test_deleteGuest(){
        Map<String, String> answer = adminClientMethods.guestLog("test_ip", "test_mac", "test_login", "test_id", "1", "", "", "test_id", new Date(System.currentTimeMillis()).toString(), "2");
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
