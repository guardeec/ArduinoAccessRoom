package admin.client.eventsTechnicalDepartment;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_deleteDevice {
    @Test
    public void test_deleteDevice(){
        Map<String, String> answer = adminClientMethods.deviceLog("test_ip", "test_mac", "test_login", "test_id", "1", "", "", "test_id", new Date(System.currentTimeMillis()).toString(), "2");
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
