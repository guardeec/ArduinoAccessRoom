package admin.client.enterAndOutAdmin;

import admin.client.adminClientMethods;
import arduino.client.arduinoClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_enterAdmin {
    @Test
    public void test_enterAdmin(){
        Map<String, String> answer = adminClientMethods.enterOrOutAdminLog("test_ip", "test_mac", "test_login", "test_id", "1", "technical", new Date(System.currentTimeMillis()).toString(), "1");
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
