package admin.client.eventsSecurityDepartment.roles;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_addRole {
    @Test
    public void test_addRole(){
        Map<String, String> answer = adminClientMethods.roleLog("test_ip", "test_mac", "test_login", "test_id", "1", "test_id", "test_title", new Date(System.currentTimeMillis()).toString(), "0");
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
