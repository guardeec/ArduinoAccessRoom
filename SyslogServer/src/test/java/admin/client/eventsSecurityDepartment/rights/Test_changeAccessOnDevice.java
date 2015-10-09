package admin.client.eventsSecurityDepartment.rights;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_changeAccessOnDevice {
    @Test
    public void test_changeAccessOnDevice(){
        Map<String, String> answer = adminClientMethods.changeAccesOnDeviceLog("test_ip", "test_mac", "test_login", "test_id", "1", "test_id", "test_id", "test_id", new Date(System.currentTimeMillis()).toString());
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
